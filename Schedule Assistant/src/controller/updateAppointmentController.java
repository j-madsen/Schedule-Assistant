package controller;

import DBHelper.dbAppointments;
import DBHelper.dbContact;
import DBHelper.dbCustomer;
import DBHelper.dbUsers;
import Main.TimeAdder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Appointment;
import model.Contact;
import model.alertBot;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.ResourceBundle;

/** This class houses the methods used to work with the updateAppointment form */
public class updateAppointmentController implements Initializable {
    public TextField appointmentID;
    public TextField title;
    public TextField description;
    public ComboBox contactBox;
    public TextField type;
    public ComboBox customerIDBox;
    public ComboBox userIDBox;
    public TextField locationField;
    public DatePicker startDate;
    public ComboBox startHour;
    public ComboBox startMinute;
    public DatePicker endDate;
    public ComboBox endHour;
    public ComboBox endMinute;
    public ComboBox typeCombo;

    /** This sets the items of the comboboxes */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<String> type = FXCollections.observableArrayList();
            type.add("De-Briefing");
            type.add("Planning Session");
            typeCombo.setItems(type);
            contactBox.setItems(Contact.contactNameList());
            customerIDBox.setItems(dbCustomer.getAllIDs());
            userIDBox.setItems(dbUsers.getAllIDs());
            startHour.setItems(Appointment.getBusinessHours());
            startMinute.setItems(Appointment.getBusinessMinutes());
            endHour.setItems(Appointment.getBusinessHoursEnd());
            endMinute.setItems(Appointment.getBusinessMinutes());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** This method receives the data from the appointment selected on the main form and sets the values on the form
     * accordingly.
     * @param appointment selected appointment.
     */
    public void dataReceiver(Appointment appointment) {
        appointmentID.setText(String.valueOf(appointment.getAppointmentID()));
        title.setText(appointment.getTitle());
        description.setText(appointment.getDescription());
        locationField.setText(appointment.getLocation());
        contactBox.setValue(appointment.getContactName());
        customerIDBox.setValue(appointment.getCustomerID());
        typeCombo.setValue(appointment.getType());
        startDate.setValue(appointment.getStartDate().toLocalDate());
        endDate.setValue(appointment.getEndDate().toLocalDate());
        userIDBox.setValue(appointment.getUserID());

        for (LocalTime hour : Appointment.getBusinessHours()) {
            if (appointment.getStartDate().toLocalTime().getHour() == hour.getHour()) {
                startHour.setValue(hour);
            }
        }
        for (String minute: Appointment.getBusinessMinutes()) {
            if (appointment.getStartDate().toLocalTime().getMinute() == Integer.valueOf(minute)) {
                startMinute.setValue(minute);
            }
        }
        for (LocalTime hour : Appointment.getBusinessHours()) {
            if (appointment.getEndDate().toLocalTime().getHour() == hour.getHour()) {
                endHour.setValue(hour);
            }
        }
        for (String minute: Appointment.getBusinessMinutes()) {
            if (appointment.getEndDate().toLocalTime().getMinute() == Integer.valueOf(minute)) {
                endMinute.setValue(minute);
            }
        }
    }

    /** This method creates a new appointment if the user input is valid and updates the appointment in the database by the appointment ID.
     *  LAMBDA expression is used to add the values from the hour and minute combo boxes allowing a completed LocalTime object to be created.
     * @param actionEvent event that user clicks the save button.
     * @throws IOException from toMain() method.
     */
    public void onSaveButton(ActionEvent actionEvent) throws IOException {
        try {
            TimeAdder combined = (hour, minute) -> hour.plusMinutes(minute);
            LocalDateTime start = LocalDateTime.of((LocalDate) startDate.getValue(), combined.addMinutes(((LocalTime) startHour.getValue()),(Integer.parseInt((String) startMinute.getValue()))));
            LocalDateTime end = LocalDateTime.of((LocalDate) endDate.getValue(), combined.addMinutes((LocalTime) endHour.getValue(),Integer.parseInt((String) endMinute.getValue())));
            int contactID = 0;
            for (Contact contact : dbContact.getAllContacts()) {
                if (Objects.equals(contactBox.getValue().toString(), contact.getContactName())) {
                    contactID = contact.getContactID();
                }
            }
            Appointment newAppointment = new Appointment(Integer.parseInt(appointmentID.getText()), title.getText(), description.getText(), locationField.getText(), typeCombo.getValue().toString(), start, end, Integer.parseInt(customerIDBox.getValue().toString()), Integer.parseInt(userIDBox.getValue().toString()), contactID, contactBox.getValue().toString());
                if (!alertBot.validateHours(start, end)) {
                    alertBot.invalidHours();
                } else if (end.toLocalTime().isBefore(start.toLocalTime()) || end.toLocalTime().equals(start.toLocalTime())) {
                    alertBot.invalidTime();
                } else if (alertBot.checkAppointmentOverlap(newAppointment)) {
                    alertBot.appointmentsOverlap();
                } else {
                    if (alertBot.confirmSave() == true) {
                        dbAppointments.modifyAppointment(newAppointment);
                        mainFormController.toMain(actionEvent);
                    }
                }
            } catch (SQLException ex) {
            ex.printStackTrace();

    } catch (NumberFormatException | NullPointerException e) {
            alertBot.invalidFields();
        }

    }

    /** This method takes the user back to the main form of the user clicks the cancel button and confirms
     *
     * @param actionEvent event that user clicks the cancel button.
     * @throws IOException from toMain() method.
     */
        public void onCancelButton(ActionEvent actionEvent) throws IOException {
            if (alertBot.confirmCancel() == true) {
                mainFormController.toMain(actionEvent);
            }
        }

    /** This method sets the value of the endDate datepicker to whichever date is selected in the start date datepicker.*/
    public void onStartDate(ActionEvent actionEvent) {
        endDate.setValue(startDate.getValue());
    }
}