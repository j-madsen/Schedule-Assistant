package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/** This creates the appointment class which houses methods for appointments */
public class Appointment {
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private String contactName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int customerID;
    private int userID;
    private int contactID;
    private ZoneId userzoneID = ZoneId.systemDefault();

    /** Constructor with attributes for an appointment */
    public Appointment(int appointmentID, String title, String description, String location, String type, LocalDateTime startDate, LocalDateTime endDate,int customerID, int userID, int contactID, String contactName) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
        this.contactName = contactName;
    }

    /**
     *  method returns appointment's appointment ID
     * @return appointment's appointment ID
     */
    public int getAppointmentID() {return appointmentID;}

    /**
     *  method returns appointment's title
     * @return appointment's title
     */
    public String getTitle() {return title;}

    /**
     *  method returns appointment's description
     * @return appointment's description
     */
    public String getDescription() {return description;}

    /**
     *  method returns appointment's location
     * @return appointment's location
     */
    public String getLocation() {return location;}

    /**
     *  method returns appointment's type
     * @return appointment's type
     */
    public String getType() {return type;}

    /**
     *  method returns appointment's contact name
     * @return appointment's contact name
     */
    public String getContactName() {return contactName;}

    /**
     *  method returns appointment's start date/time
     * @return appointment's start date/time */
    public LocalDateTime getStartDate() {return startDate;}

    /**
     *  method returns appointment's end date/time
     * @return appointment's end date/time
     */
    public LocalDateTime getEndDate() {return endDate;}

    /**
     *  method returns appointment's customer ID
     * @return appointment's customer ID
     */
    public int getCustomerID() {return customerID;}

    /**
     *  method returns appointment's user ID
     * @return appointment's user ID
     */
    public int getUserID() {return userID;}

    /**
     *  method returns appointment's contact ID
     * @return appointment's contact ID
     */
    public int getContactID() {return contactID;}

    /** This method returns a list of all possible hours
     *
     * @return list of hours
     */
    public static ObservableList<LocalTime> getBusinessHours(){
        ObservableList<LocalTime> HoursList = FXCollections.observableArrayList();
        DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("kk");
        for (int i = 1; i <= 24; i++) {
            if (i < 10) {
                String string = "0"+String.valueOf(i);
                HoursList.add(LocalTime.parse(string,formatter));
            } else {
                HoursList.add(LocalTime.parse(String.valueOf(i),formatter));
            }
        }
        return HoursList;
    }

    /** This method returns a list of all possible hours for an end time
     *
     * @return list of hours for an end time
     */
    public static ObservableList<LocalTime> getBusinessHoursEnd(){
        ObservableList<LocalTime> HoursList = FXCollections.observableArrayList();
        DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("kk");
        for (int i = 1; i <= 23; i++) {
            if (i < 10) {
                String string = "0"+String.valueOf(i);
                HoursList.add(LocalTime.parse(string,formatter));
            } else {
                HoursList.add(LocalTime.parse(String.valueOf(i),formatter));
            }
        }
        return HoursList;
    }

    /** This method returns a list of all possible minutes
     *
     * @return list of minutes
     */
    public static ObservableList<String> getBusinessMinutes(){
        ObservableList<String> MinutesList = FXCollections.observableArrayList();
        for (int i = 0; i <= 59; i++ ) {
            if (i < 10) {
                String string = "0"+String.valueOf(i);
                MinutesList.add(string);
            } else {
                MinutesList.add(String.valueOf(i));
            }
        }
        return MinutesList;
    }
}
