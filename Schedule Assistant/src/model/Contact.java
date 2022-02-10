package model;

import DBHelper.dbContact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/** This creates the contact class which houses methods for contacts */
public class Contact {
    private int contactID;
    private String contactName;
    private String contactEmail;

    /** Constructor with attributes for a contact */
    public Contact(int contactID, String contactName, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /** returns a contact's ID
     *
     * @return contact's ID
     */
    public int getContactID() {return contactID;}

    /** returns a contact's name
     *
     * @return contact's name
     */
    public String getContactName() {return contactName;}

    /** returns a contact's email
     *
     * @return contact's email
     */
    public String getContactEmail() {return contactEmail;}

    /** This method loops through all contact objects and returns a list of all contact names
     *
     * @return list of contact names
     * @throws SQLException
     */
    public static ObservableList<String> contactNameList() throws SQLException {
        ObservableList<String> contactNames = FXCollections.observableArrayList();
        for (Contact contact : dbContact.getAllContacts()) {
            contactNames.add(contact.getContactName());
        }
        return contactNames;
    }

}
