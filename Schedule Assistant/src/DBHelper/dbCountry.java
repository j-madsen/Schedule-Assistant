package DBHelper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class houses a method to work with the countries table in the database */
public class dbCountry {

    /** This method creates country objects by getting the countries from the countries database table
     *
     * @return a list of country objects
     */
    public static ObservableList<Countries> getAllCountries()  {
        ObservableList<Countries> countryList = FXCollections.observableArrayList();
        try {
            String query = "SELECT Country_ID, Country FROM countries";
            PreparedStatement stmt = dbConnector.getConnection().prepareStatement(query);

            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                Countries country = new Countries(results.getInt("Country_ID"), results.getString("Country"));
                countryList.add(country);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return countryList;
    }


}
