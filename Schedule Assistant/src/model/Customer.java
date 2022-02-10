package model;



public class Customer {
    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String division;
    private String country;

    public Customer(int id, String name, String address, String postalCode, String phoneNumber, String division, String country) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.division = division;
        this.country = country;
    };

    public int getId() {return id;}

    public String getName() {return name;}

    public String getAddress() {return address;}

    public String getPostalCode() {return postalCode;}

    public String getPhoneNumber() {return phoneNumber;}

    public String getCountry() {return country;}

    public String getDivision() {return division;}
}
