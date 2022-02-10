package DBHelper;

import java.sql.Connection;
import java.sql.DriverManager;

/** This class houses methods to work with the database connection*/
public abstract class dbConnector {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone=SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver";  // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    private static Connection connection; // Connection Interface

    /** This method opens a connection to the database */
    public static void openConnection() {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** This method gets the database connection
     *
     * @return Connection
     */
    public static Connection getConnection() {
        return connection;
    }

    /** This method closes the database connection.
     *
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
