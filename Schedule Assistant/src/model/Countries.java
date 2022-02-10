package model;

/** This creates the country class which houses methods for countries */
public class Countries {
    private int countryID;
    private String country;


    public Countries(int CountryID, String country) {this.countryID = countryID; this.country = country;}

    /** This method allows countries to be displayed correctly in combo boxes
     *
     * @return country
     */
    public String toString() {
        return (country);
    }
}
