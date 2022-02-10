package DBHelper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.Divisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/** This class houses methods used to work with the customers database table */
public class dbCustomer {

    /** This method creates customer objects by getting the customers from the customers database table
     *
     * @return a list of customer objects
     * @throws SQLException
     */
    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        try {
            String query = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division, Country, first_level_divisions.COUNTRY_ID, countries.Country_ID, customers.Division_ID  FROM customers, first_level_divisions, countries WHERE customers.Division_ID = first_level_divisions.Division_ID AND first_level_divisions.COUNTRY_ID = countries.Country_ID";
            PreparedStatement stmt = dbConnector.getConnection().prepareStatement(query);

            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                Customer customer = new Customer(results.getInt("Customer_ID"), results.getString("Customer_Name"), results.getString("Address"), results.getString("Postal_Code"), results.getString("Phone"), results.getString("Division"),results.getString("Country"));
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }
    /** This method takes a country and loops through the entire list of customer objects and makes a new list with customers that reside
     *  in the selected country
     * @return list of customers that reside in selected country
     * @throws SQLException
     */
    public static ObservableList<Customer> customerByCountry(String country) throws SQLException {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        try {
           for (Customer customer: getAllCustomers()) {
               if (Objects.equals(customer.getCountry(),country)) {
                   customerList.add(customer);
               }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }

    /** This method loops through the entire list of customer objects and makes a new list with customer IDs.
     * @return list of customer IDs.
     * @throws SQLException
     */
    public static ObservableList<Integer> getAllIDs() throws SQLException {
        ObservableList<Integer> customerIDs = FXCollections.observableArrayList();
        for (Customer customer : getAllCustomers()) {
            customerIDs.add(customer.getId());
        }
        return customerIDs;
    }

    /** This method takes a customer object and inserts it into the customers table in the database.
     *
     * @param customer
     */
    public static void addCustomer(Customer customer) {
        try {
            String divisionID = String.valueOf(customer.getId());
            String name = customer.getName();
            String address = customer.getAddress();
            String postalCode = customer.getPostalCode();
            String phone = customer.getPhoneNumber();
            String division = customer.getDivision();
            String query = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?,?)";
            PreparedStatement ps = dbConnector.getConnection().prepareStatement(query);
            ps.setString(1,name);
            ps.setString(2,address);
            ps.setString(3,postalCode);
            ps.setString(4,phone);
            ps.setString(5,divisionID);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** This method takes a customer object and updates the customer in the database by the customer ID.
     *
     * @param customer
     */
    public static void modifyCustomer(Customer customer) {
        try {
            String divisionName = customer.getDivision();
            int divisionID = 0;
            for (Divisions division : dbDivision.getAllDivisions()) {
                if (Objects.equals(division.getDivisionName(), divisionName)) {
                    divisionID = division.getDivisionID();
                }
            }
            int customerID = customer.getId();
            String name = customer.getName();
            String address = customer.getAddress();
            String postalCode = customer.getPostalCode();
            String phone = customer.getPhoneNumber();
            String query = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement ps = dbConnector.getConnection().prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionID);
            ps.setInt(6,customerID);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** This method takes a customer object and deletes the customer in the database by the customer ID and all associated appointments.
     *
     * @param customer
     */
    public static void deleteCustomer(Customer customer) {
        try {
            int id = customer.getId();
            String query = "DELETE FROM customers WHERE Customer_ID = ?";
            String query2 = "DELETE FROM appointments WHERE Customer_ID = ?";
            PreparedStatement ps = dbConnector.getConnection().prepareStatement(query);
            PreparedStatement ps2 = dbConnector.getConnection().prepareStatement(query2);

            ps.setInt(1, id);
            ps2.setInt(1, id);

            ps2.executeUpdate();
            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
