package DBHelper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Divisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class houses a method to work with the first_level_divisions table in the database */
public class dbDivision {

    /** This method creates division objects by getting the divisions from the first_level_divisions database table
     *
     * @return a list of division objects
     */
    public static ObservableList<Divisions> getAllDivisions() {
        ObservableList<Divisions> divisionList = FXCollections.observableArrayList();
        try {
            String query = "SELECT Division_ID, Division, COUNTRY_ID FROM first_level_divisions";
            PreparedStatement stmt = dbConnector.getConnection().prepareStatement(query);

            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                Divisions division = new Divisions(results.getInt("Division_ID"), results.getString("Division"),results.getInt("COUNTRY_ID"));
                divisionList.add(division);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return divisionList;
    }

    /** This method loops through the entire list of division objects and creates a new list of division IDs.
     *
     * @param division
     * @return list of division IDs.
     */
    public static int getDivisionID(Divisions division) {
        int id = 0;
        for (Divisions div : getAllDivisions()) {
            if (division == div) {
                id = div.getDivisionID();
            }
        }
        return id;
    }
}
