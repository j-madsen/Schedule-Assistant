package DBHelper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.IsoFields;
import java.util.Objects;

/** This class houses methods used to work with the appointments database table */
public class dbAppointments {

    /** This method creates appointment objects by getting the appointments from the appointments database table
     *
     * @return a list of appointment objects
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

        try {
            String query = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, contacts.Contact_Name, appointments.Contact_ID, contacts.Contact_ID  FROM appointments, contacts WHERE contacts.Contact_ID = appointments.Contact_ID";
            PreparedStatement stmt = dbConnector.getConnection().prepareStatement(query);

            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                Timestamp ts1 = results.getTimestamp("Start");
                Timestamp ts2 = results.getTimestamp("End");
                LocalDateTime startldt = ts1.toLocalDateTime();
                LocalDateTime endldt = ts2.toLocalDateTime();
                Appointment appointment = new Appointment(results.getInt("Appointment_ID"), results.getString("Title"), results.getString("Description"), results.getString("Location"), results.getString("Type"), startldt, endldt,results.getInt("Customer_ID"),results.getInt("User_ID"), results.getInt("Contact_ID"), results.getString("Contact_Name"));
                appointmentList.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentList;
    }

    /** This method loops through the entire list of appointment objects and makes a new list with appointments that occur
     *  in the current month
     * @return list of current month appointments
     * @throws SQLException
     */
    public static ObservableList<Appointment> currentMonthAppointments() throws SQLException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try {
        for (Appointment appointment : getAllAppointments()) {
            if (appointment.getStartDate().getMonth() == LocalDate.now().getMonth()) {
                appointmentList.add(appointment);
            }
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentList;
    }

    /** This method loops through the entire list of appointment objects and makes a new list with appointments that occur
     *  in the current week
     * @return list of current week appointments
     * @throws SQLException
     */
    public static ObservableList<Appointment> currentWeekAppointments() throws SQLException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        int currentWeek = today.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        try {
            for (Appointment appointment : getAllAppointments()) {
                if (appointment.getStartDate().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) == currentWeek) {
                    appointmentList.add(appointment);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentList;
    }

    /** This method loops through the entire list of appointment objects and makes a new list of appointments with the
     *  provided contact name
     * @return list of appointments with contact
     * @param contactName name of the contact
     * @throws SQLException
     */
    public static ObservableList<Appointment> appointmentByContact(String contactName) throws SQLException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

        try {
            for (Appointment appointment: getAllAppointments()) {
                if (Objects.equals(appointment.getContactName(),contactName)) {
                    appointmentList.add(appointment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentList;
    }

    /** This method takes an appointment object and inserts it into the appointments table in the database.
     *
     * @param appointment
     */
    public static void addAppointment(Appointment appointment) {
        try {
            String title = appointment.getTitle();
            String description = appointment.getDescription();
            String location = appointment.getLocation();
            int contact = appointment.getContactID();
            String type = appointment.getType();
            Timestamp startTS = Timestamp.from(toUTC(appointment.getStartDate()).toInstant());
            Timestamp endTS = Timestamp.from(toUTC(appointment.getEndDate()).toInstant());
            int customerID = appointment.getCustomerID();
            int userID = appointment.getUserID();
            String query = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = dbConnector.getConnection().prepareStatement(query);;
            ps.setString(1,title);
            ps.setString(2,description);
            ps.setString(3,location);
            ps.setString(4,type);
            ps.setTimestamp(5, startTS);
            ps.setTimestamp(6, endTS);
            ps.setInt(7,customerID);
            ps.setInt(8,userID);
            ps.setInt(9,contact);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** This method takes an appointment object and updates the appointment in the database by the appointment ID.
     *
     * @param appointment
     */
    public static void modifyAppointment(Appointment appointment) {
        try {
            int id = appointment.getAppointmentID();
            String title = appointment.getTitle();
            String description = appointment.getDescription();
            String location = appointment.getLocation();
            int contact = appointment.getContactID();
            String type = appointment.getType();
            Timestamp startTS = Timestamp.from(toUTC(appointment.getStartDate()).toInstant());
            Timestamp endTS = Timestamp.from(toUTC(appointment.getEndDate()).toInstant());
            int customerID = appointment.getCustomerID();
            int userID = appointment.getUserID();
            String query = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
            PreparedStatement ps = dbConnector.getConnection().prepareStatement(query);



            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, startTS);
            ps.setTimestamp(6, endTS);
            ps.setInt(7, customerID);
            ps.setInt(8, userID);
            ps.setInt(9, contact);
            ps.setInt(10, id);

            ps.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** This method takes an appointment object and deletes the appointment in the database by the appointment ID.
     *
     * @param appointment
     */
    public static void deleteAppointment(Appointment appointment) {
        try {
            int id = appointment.getAppointmentID();
            String query = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement ps = dbConnector.getConnection().prepareStatement(query);

            ps.setInt(1, id);

            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** This method takes a LocalDateTime object and converts the time to UTC.
     *
     * @param ldt
     * @return a ZonedDateTime object with UTC time
     */
    public static ZonedDateTime toUTC(LocalDateTime ldt) {
        ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        ZonedDateTime utc = zdt.withZoneSameInstant(ZoneOffset.UTC);
        return utc;
    }
}
