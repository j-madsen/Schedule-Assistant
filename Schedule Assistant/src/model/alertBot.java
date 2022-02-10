package model;

import DBHelper.dbAppointments;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.*;
import java.util.Locale;
import java.util.Optional;

/** This class houses alerts/validation check methods */
public class alertBot {

    /** This method asks a user to confirm if they want to save.
     *
     * @return true if confirmation received, otherwise false
     */
    public static boolean confirmSave(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setContentText("Are you sure you want to save?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
                return true;
        } else {
            return false;
        }
    }

    /** This method asks a user to confirm if they want to cancel.
     *
     * @return true if confirmation received, otherwise false
     */
    public static boolean confirmCancel(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    /** This method alerts a user that a customer needs to be selected */
    public static void selectCustomer() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Please select a customer!");
        Optional<ButtonType> result = alert.showAndWait();
        }

    /** This method alerts a user that an appointment needs to be selected */
    public static void selectAppointment() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Please select an appointment!");
        Optional<ButtonType> result = alert.showAndWait();
    }

    /** This method asks a user to confirm if they want to delete.
     *
     * @return true if confirmation received, otherwise false
     */
    public static boolean confirmDelete(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setContentText("Are you sure you want to delete?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    /** This method alerts the user that there is an invalid username or password.*/
    public static void invalidUser() throws FileNotFoundException {
        Locale zoneID = Locale.getDefault();
        if (zoneID.getLanguage() == "fr") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alerte");
            alert.setContentText("Nom d'utilisateur ou mot de passe invalide.");
            alert.showAndWait();

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Invalid username or password!");
            Optional<ButtonType> result = alert.showAndWait();

        }
    }

    /** This method alerts the user to make a selection.*/
    public static void makeSelection() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Please make a selection!");
        Optional<ButtonType> result = alert.showAndWait();
    }

    /** This method alerts the user whether there is an appointment coming up in 15 minutes or not.*/
    public static void upcomingAppointment() throws SQLException {
        Locale zoneID = Locale.getDefault();
        LocalDateTime time = LocalDateTime.now(ZoneId.systemDefault());
        LocalTime curTime = LocalTime.of(time.getHour(),time.getMinute());
        LocalTime plus15 = curTime.plusMinutes(15);
        boolean upcoming = false;
        for (Appointment appointment : dbAppointments.getAllAppointments()) {
            if (appointment.getStartDate().toLocalDate().isEqual(time.toLocalDate())) {
                if ((appointment.getStartDate().toLocalTime().isBefore(plus15) || appointment.getStartDate().toLocalTime().equals(plus15)) && (appointment.getStartDate().toLocalTime().equals(curTime) || appointment.getStartDate().toLocalTime().isAfter(curTime))) {
                    if (zoneID.getLanguage() == "fr") {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Alerte");
                        alert.setContentText("Un rendez-vous est prévu dans 15 minutes. Identifiant de rendez-vous: " + String.valueOf(appointment.getAppointmentID()) + ", Date et l'heure: " + appointment.getStartDate().toString());
                        alert.showAndWait();
                        upcoming = true;
                        break;
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Alert");
                        alert.setContentText("There is a scheduled appointment upcoming within 15 minutes. Appointment ID: " + String.valueOf(appointment.getAppointmentID()) + ", Date and Time: " + appointment.getStartDate().toString());
                        alert.showAndWait();
                        upcoming = true;
                        break;
                    }
                }
            }
        }
                if (!upcoming) {
                    if (zoneID.getLanguage() == "fr") {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Alerte");
                        alert.setContentText("Il n'y a pas de rendez-vous à venir.");
                        alert.showAndWait();

                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Alert");
                        alert.setContentText("There are no upcoming appointments.");
                        alert.showAndWait();

                    }
                }
    }





    /** This method alerts the user whether there is invalid input.*/
    public static void invalidFields() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Please enter valid values for each field!");
        Optional<ButtonType> result = alert.showAndWait();
    }

    /** This method takes in two LocalDateTimes and validates if they are within business hours.
     *
     * @param start start date/time
     * @param end end date/time
     * @return true if within business hours, otherwise false
     */
    public static boolean validateHours(LocalDateTime start, LocalDateTime end) {
        LocalTime openloc = LocalTime.of(8, 00);
        LocalTime closeloc = LocalTime.of(22, 00);
        ZonedDateTime open = ZonedDateTime.of(start.toLocalDate(), openloc, ZoneId.of("America/New_York"));
        ZonedDateTime close = ZonedDateTime.of(end.toLocalDate(), closeloc, ZoneId.of("America/New_York"));
        ZonedDateTime locOpen = ZonedDateTime.of(start, ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York"));
        ZonedDateTime locClose = ZonedDateTime.of(end, ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York"));
        if (locOpen.isBefore(open) || locClose.isAfter(close) || locClose.isBefore(open) || locOpen.isAfter(close)) {
            return false;
        } else {
            return true;
        }
    }

    /** This method alerts the user of invalid hours inputted. */
    public static void invalidHours() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Start time cannot be before 8:00 EST and end time cannot be after 22:00 EST.");
        Optional<ButtonType> result = alert.showAndWait();
    }

    /** This method alerts the user of an invalid time inputted. */
    public static void invalidTime() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("End time must be after start time. ");
        Optional<ButtonType> result = alert.showAndWait();
    }

    /** This method alerts the user that a customer has been deleted */
    public static void customerDeleted() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setContentText("The customer has been deleted.");
        alert.showAndWait();
    }

    /** This method alerts the user that an appointment has been deleted and provided the ID and type. */
    public static void appointmentDeleted(Appointment appointment) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setContentText("Appointment ID: " + String.valueOf(appointment.getAppointmentID()) + " Type: "+ appointment.getType() + " has been cancelled." );
        alert.showAndWait();
    }

    /** This method takes in an appointment and compares the appointment datetime with all other appointments with the same
     * customer ID. It excludes appointments with the same appointment ID. It will return true if the appointments overlap,
     * otherwise it will return false.
     * @param appointment
     * @return true if appointments overlap, otherwise false
     * @throws SQLException from getAllAppointments()
     */
    public static boolean checkAppointmentOverlap(Appointment appointment) throws SQLException {
        for (Appointment currentApt : dbAppointments.getAllAppointments()) {
            if ((currentApt.getCustomerID() == appointment.getCustomerID()) && currentApt.getAppointmentID() != appointment.getAppointmentID()) {
                LocalDateTime newAptStart = appointment.getStartDate();
                LocalDateTime curAptStart = currentApt.getStartDate();
                LocalDateTime curAptEnd = currentApt.getEndDate();
                LocalDateTime newAptEnd = appointment.getEndDate();
                if (newAptStart.equals(curAptStart) || (newAptStart.isAfter(curAptStart) && newAptStart.isBefore(curAptEnd))) {
                    return true;
                }
                if (newAptEnd.isAfter(curAptStart) && (newAptEnd.isBefore(curAptEnd) || newAptEnd.equals(curAptEnd))) {
                    return true;
                }
                if ((newAptStart.isBefore(curAptStart) || (newAptStart.equals(curAptStart))) && (newAptEnd.equals(curAptEnd) || newAptEnd.isAfter(curAptEnd))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void appointmentsOverlap() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("The appointment times overlap with an existing appointment.");
        Optional<ButtonType> result = alert.showAndWait();
    }


}
