package controller;

import DBHelper.dbAppointments;
import DBHelper.dbCountry;
import DBHelper.dbCustomer;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.Contact;
import model.alertBot;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Month;
import java.util.Objects;
import java.util.ResourceBundle;

/** This class houses the methods used to work with the reportsForm screen*/
public class reportsFormController implements Initializable {
    public TextField psJan;
    public TextField psFeb;
    public TextField psAug;
    public TextField psSep;
    public TextField psOct;
    public TextField psNov;
    public TextField psDec;
    public TextField psJun;
    public TextField psJul;
    public TextField psApr;
    public TextField psMay;
    public TextField psMar;
    public TextField deJan;
    public TextField deJul;
    public TextField deJun;
    public TextField deMay;
    public TextField deApr;
    public TextField deMar;
    public TextField deFeb;
    public TextField deOct;
    public TextField deSep;
    public TextField deAug;
    public TextField deDec;
    public TextField deNov;
    public ComboBox contactBox;
    public TableView scheduleTable;
    public TableColumn appointmentID;
    public TableColumn title;
    public TableColumn type;
    public TableColumn description;
    public TableColumn startDate;
    public TableColumn endDate;
    public TableColumn customerID;
    public TableColumn countryCustomerID;
    public TableColumn name;
    public TableColumn address;
    public TableColumn postalCode;
    public TableColumn phoneNumber;
    public ComboBox countryCombo;
    public TableView countryTable;
    public TableColumn division;

    /** This sets the totals of the first report by calling monthlyTotals(), and sets the items of the contact and country
      combo boxes */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            monthlyTotals();
            contactBox.setItems(Contact.contactNameList());
            countryCombo.setItems(dbCountry.getAllCountries());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /** This method calculates the totals by month of each type of appointment and sets the text fields accordingly.
     *
     * @throws SQLException from getAllAppointments()
     */
    public void monthlyTotals() throws SQLException {
        int pssJan = 0;
        int pssFeb = 0;
        int pssMar = 0;
        int pssApr = 0;
        int pssMay = 0;
        int pssJun = 0;
        int pssJul = 0;
        int pssAug = 0;
        int pssSep = 0;
        int pssOct = 0;
        int pssNov = 0;
        int pssDec = 0;
        int debJan = 0;
        int debFeb = 0;
        int debMar = 0;
        int debApr = 0;
        int debMay = 0;
        int debJun = 0;
        int debJul = 0;
        int debAug = 0;
        int debSep = 0;
        int debOct = 0;
        int debNov = 0;
        int debDec = 0;
        for (Appointment appointment : dbAppointments.getAllAppointments()) {

                if (appointment.getStartDate().getMonth() == Month.JANUARY && Objects.equals(appointment.getType(), "Planning Session")) {
                    pssJan += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.FEBRUARY && Objects.equals(appointment.getType(), "Planning Session")) {
                    pssFeb += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.MARCH && Objects.equals(appointment.getType(), "Planning Session")) {
                    pssMar += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.APRIL && Objects.equals(appointment.getType(), "Planning Session")) {
                    pssApr += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.MAY && Objects.equals(appointment.getType(), "Planning Session")) {
                    pssMay += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.JUNE && Objects.equals(appointment.getType(), "Planning Session")) {
                    pssJun += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.JULY && Objects.equals(appointment.getType(), "Planning Session")) {
                    pssJul += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.AUGUST && Objects.equals(appointment.getType(), "Planning Session")) {
                    pssAug += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.SEPTEMBER && Objects.equals(appointment.getType(), "Planning Session")) {
                    pssSep += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.OCTOBER && Objects.equals(appointment.getType(), "Planning Session")) {
                    pssOct += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.NOVEMBER && Objects.equals(appointment.getType(), "Planning Session")) {
                    pssNov += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.DECEMBER && Objects.equals(appointment.getType(), "Planning Session")) {
                    pssDec += 1;
                }

                if (appointment.getStartDate().getMonth() == Month.JANUARY && Objects.equals(appointment.getType(), "De-Briefing")) {
                    debJan += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.FEBRUARY && Objects.equals(appointment.getType(), "De-Briefing")) {
                    debFeb += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.MARCH && Objects.equals(appointment.getType(), "De-Briefing")) {
                    debMar += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.APRIL && Objects.equals(appointment.getType(), "De-Briefing")) {
                    debApr += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.MAY && Objects.equals(appointment.getType(), "De-Briefing")) {
                    debMay += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.JUNE && Objects.equals(appointment.getType(), "De-Briefing")) {
                    debJun += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.JULY && Objects.equals(appointment.getType(), "De-Briefing")) {
                    debJul += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.AUGUST && Objects.equals(appointment.getType(), "De-Briefing")) {
                    debAug += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.SEPTEMBER && Objects.equals(appointment.getType(), "De-Briefing")) {
                    debSep += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.OCTOBER && Objects.equals(appointment.getType(), "De-Briefing")) {
                    debOct += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.NOVEMBER && Objects.equals(appointment.getType(), "De-Briefing")) {
                    debNov += 1;
                }
                if (appointment.getStartDate().getMonth() == Month.DECEMBER && Objects.equals(appointment.getType(), "De-Briefing")) {
                    debDec += 1;
                }
            }
        psJan.setText(String.valueOf(pssJan));
        psFeb.setText(String.valueOf(pssFeb));
        psMar.setText(String.valueOf(pssMar));
        psApr.setText(String.valueOf(pssApr));
        psMay.setText(String.valueOf(pssMay));
        psJun.setText(String.valueOf(pssJun));
        psJul.setText(String.valueOf(pssJul));
        psAug.setText(String.valueOf(pssAug));
        psSep.setText(String.valueOf(pssSep));
        psOct.setText(String.valueOf(pssOct));
        psNov.setText(String.valueOf(pssNov));
        psDec.setText(String.valueOf(pssDec));
        deJan.setText(String.valueOf(debJan));
        deFeb.setText(String.valueOf(debFeb));
        deMar.setText(String.valueOf(debMar));
        deApr.setText(String.valueOf(debApr));
        deMay.setText(String.valueOf(debMay));
        deJun.setText(String.valueOf(debJun));
        deJul.setText(String.valueOf(debJul));
        deAug.setText(String.valueOf(debAug));
        deSep.setText(String.valueOf(debSep));
        deOct.setText(String.valueOf(debOct));
        deNov.setText(String.valueOf(debNov));
        deDec.setText(String.valueOf(debDec));

        }

    /** This returns the user to the mainForm scene
     *
     * @param actionEvent event that the user clicks the return button
     * @throws IOException from toMain()
     */
    public void onReturnButton(ActionEvent actionEvent) throws IOException {
        mainFormController.toMain(actionEvent);
    }

    /** This filters and sets the table with appointments with the selected contact.
     *
     * @param actionEvent event that the user selects a contact.
     * @throws SQLException from appointmentByContact()
     */
    public void onContactBox(ActionEvent actionEvent) throws SQLException {
        if (contactBox.getValue() != null) {
            String contactName = contactBox.getValue().toString();
            scheduleTable.setItems(dbAppointments.appointmentByContact(contactName));
            appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            title.setCellValueFactory(new PropertyValueFactory<>("title"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        } else {
            alertBot.makeSelection();
        }
    }

    /** This filters and sets the table with customers who reside in the selected country.
     *
     * @param actionEvent event that the user selects a country.
     * @throws SQLException from customerByCountry()
     */
    public void onCountryBox(ActionEvent actionEvent) throws SQLException {
        if (countryCombo.getValue() != null) {
            countryTable.setItems(dbCustomer.customerByCountry(countryCombo.getValue().toString()));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            address.setCellValueFactory(new PropertyValueFactory<>("address"));
            postalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
            phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            division.setCellValueFactory(new PropertyValueFactory<>("division"));
        } else {
            alertBot.makeSelection();
        }
    }
}
