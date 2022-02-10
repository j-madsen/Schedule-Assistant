package DBHelper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class houses a method to work with the users table in the database */
public class dbUsers {

    /** This method creates user objects by getting the users from the users database table
     * @return a list of user objects
     * @throws SQLException
     */
    public static ObservableList<User> getAllUsers() throws SQLException {
        ObservableList<User> userList = FXCollections.observableArrayList();

        try {
            String query = "SELECT User_ID, User_Name, Password FROM users";
            PreparedStatement stmt = dbConnector.getConnection().prepareStatement(query);

            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                User user = new User(results.getInt("User_ID"), results.getString("User_Name"), results.getString("Password"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    /** This method loops through the entire list of user objects and creates a new list of user IDs.
     *
     * @throws SQLException
     * @return list of user IDs.
     */
    public static ObservableList<Integer> getAllIDs() throws SQLException {
        ObservableList<Integer> userIDs = FXCollections.observableArrayList();
        for (User user : getAllUsers()) {
            userIDs.add(user.getUserID());
        }
        return userIDs;
    }

}
