package controller;

import DBHelper.dbCountry;
import DBHelper.dbCustomer;
import DBHelper.dbDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Customer;
import model.Divisions;
import model.alertBot;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/** This class houses the methods used to work with the updateCustomer form */
public class updateCustomerController implements Initializable {
    public TextField customerID;
    public TextField customerName;
    public TextField address;
    public TextField postalCode;
    public TextField phoneNumber;
    public ComboBox firstDivision;
    public ComboBox country;

    /** This sets the items in the country combo box. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        country.setItems(dbCountry.getAllCountries());
    }

    /** This method receives the data from the customer selected on the main form and sets the values on the form
     * accordingly.
     * @param customer selected appointment.
     */
    public void dataReceiver(Customer customer) {
        customerID.setText(String.valueOf(customer.getId()));
        customerName.setText(customer.getName());
        address.setText(customer.getAddress());
        postalCode.setText(customer.getPostalCode());
        phoneNumber.setText(customer.getPhoneNumber());
        firstDivision.setValue(customer.getDivision());
        country.setValue(customer.getCountry());
        ObservableList<Divisions> divisionList = FXCollections.observableArrayList();
        try {
            for (Divisions division : dbDivision.getAllDivisions()) {
                if (Objects.equals((country.getSelectionModel().getSelectedItem().toString()), "U.S")) {
                    if (division.getCountryID() == 1) {
                        divisionList.add(division);
                    }
                }
                if (Objects.equals((country.getSelectionModel().getSelectedItem().toString()), "UK")) {
                    if (division.getCountryID() == 2) {
                        divisionList.add(division);
                    }
                }
                if (Objects.equals((country.getSelectionModel().getSelectedItem().toString()), "Canada")) {
                    if (division.getCountryID() == 3) {
                        divisionList.add(division);
                    }
                }
            }

        } catch (NullPointerException e) {
        }
        firstDivision.setItems(divisionList);
    }

    /** This method creates a new customer if the user input is valid and updates the customer in the database by the customer ID.
     *
     * @param actionEvent event that user clicks the save button.
     * @throws IOException from toMain() method.
     */
    public void onSaveButton(ActionEvent actionEvent) throws IOException {
            try {
                Customer newCustomer = new Customer(Integer.parseInt(customerID.getText()), customerName.getText(), address.getText(), postalCode.getText(), phoneNumber.getText(), firstDivision.getSelectionModel().getSelectedItem().toString(), country.getSelectionModel().getSelectedItem().toString());
                if (alertBot.confirmSave() == true) {
                    dbCustomer.modifyCustomer(newCustomer);
                    mainFormController.toMain(actionEvent);
                }
            } catch (NumberFormatException | NullPointerException e){
                    alertBot.invalidFields();
                }

    }

    /** This method takes the user back to the main form of the user clicks the cancel button and confirms
     *
     * @param actionEvent event that user clicks the cancel button.
     * @throws IOException from toMain() method.
     */
    public void onCancelButton(ActionEvent actionEvent) throws IOException {
        if (alertBot.confirmCancel() == true) {
            mainFormController.toMain(actionEvent);
        }
    }


    /** This method takes the selected value in the country combobox and sets the items of the division combobox based on the
     *  selection.
     * @param actionEvent event that the user makes a selection
     */
    public void onCountryBox(ActionEvent actionEvent) {
        ObservableList<Divisions> divisionList = FXCollections.observableArrayList();
        try {
            for (Divisions division : dbDivision.getAllDivisions()) {
                if (Objects.equals((country.getSelectionModel().getSelectedItem().toString()), "U.S")) {
                    if (division.getCountryID() == 1) {
                        divisionList.add(division);
                    }
                }
                if (Objects.equals((country.getSelectionModel().getSelectedItem().toString()), "UK")) {
                    if (division.getCountryID() == 2) {
                        divisionList.add(division);

                    }
                }
                if (Objects.equals((country.getSelectionModel().getSelectedItem().toString()), "Canada")) {
                    if (division.getCountryID() == 3) {
                        divisionList.add(division);
                    }
                }
            }

        } catch (NullPointerException e) {
        }
        firstDivision.setValue(null);
        firstDivision.setItems(divisionList);
    }

}
