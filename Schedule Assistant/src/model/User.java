package model;

import DBHelper.dbUsers;

import java.sql.SQLException;
import java.util.Objects;

/** This creates the user class which houses methods for users */
public class User {
    private int userID;
    private String userName;
    private String password;

    /** Constructor with attributes for a contact */
    public User(int userID, String userName, String password) {this.userID = userID; this.userName = userName; this.password = password;}

    /** returns user's ID
     *
     * @return user's ID
     */
    public int getUserID() {return userID;}

    /** returns user's password
     *
     * @return user's password
     */
    public String getPassword() {return password;}

    /** returns user's username
     *
     * @return user's username
     */
    public String getUserName() {return userName;}

    /** This method takes a username and password string and compares it to username and password of all users in the database
     *
     * @param user
     * @param pass
     * @return true if valid user, otherwise false
     * @throws SQLException
     */
    public static boolean validateUser(String user, String pass) throws SQLException {
        for (User users : dbUsers.getAllUsers()) {
            if (Objects.equals(users.getUserName(),user) && Objects.equals(users.getPassword(), pass)) {
                return true;
            }
        }
        return false;
    }
}
