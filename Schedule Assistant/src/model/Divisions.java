package model;

/** This creates the divisions class which houses methods for divisions */
public class Divisions {
    private int divisionID;
    private String division;
    private int countryID;

    /** Constructor with attributes for a contact */
    public Divisions(int divisionID, String division, int countryID) {this.divisionID = divisionID; this.division = division; this.countryID = countryID;}

    /** returns a division's ID
     *
     * @return division's ID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /** returns a division's country ID
     *
     * @return division's country ID
     */
    public int getCountryID() {
        return countryID;
    }

    /** returns a division's division name
     *
     * @return division's division name
     */
    public String getDivisionName() {return division;}

    /** This method allows divisions to be displayed correctly in combo boxes
     *
     * @return division
     */
    public String toString() {
        return (division);
    }
}
