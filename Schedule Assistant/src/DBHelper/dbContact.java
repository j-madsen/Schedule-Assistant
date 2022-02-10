package DBHelper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class houses a method to work with the contacts table in the database */
public class dbContact {

    /** This method creates contact objects by getting the contacts from the contacts database table
     *
     * @return a list of contact objects
     * @throws SQLException
     */
    public static ObservableList<Contact> getAllContacts() throws SQLException {
        ObservableList<Contact> contactList = FXCollections.observableArrayList();

        try {
            String query = "SELECT Contact_ID, Contact_Name, Email FROM contacts";
            PreparedStatement stmt = dbConnector.getConnection().prepareStatement(query);

            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                Contact contact = new Contact(results.getInt("Contact_ID"), results.getString("Contact_Name"), results.getString("Email"));
                contactList.add(contact);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return contactList;
        }
}
