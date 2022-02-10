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

/** This class houses the methods used to work with the addCustomer scene. */
public class addCustomerController implements Initializable {
    public TextField customerID;
    public TextField customerName;
    public TextField address;
    public TextField postalCode;
    public TextField phoneNumber;
    public ComboBox firstDivision;
    public ComboBox country;

    /** This sets the items of the country combobox.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        country.setItems(dbCountry.getAllCountries());

    }

    /** This method creates a new customer object (replacing the customer ID with division ID since customer ID will be generated
     *  by the database) and adds it to the database if the user input is valid.
     * @param actionEvent event that user clicks the save button.
     * @throws IOException from toMain() method.
     */
    public void onSaveButton(ActionEvent actionEvent) throws IOException {
        try {
            Divisions division = (Divisions) firstDivision.getSelectionModel().getSelectedItem();
            Customer newCustomer = new Customer(division.getDivisionID(), customerName.getText(), address.getText(), postalCode.getText(), phoneNumber.getText(), firstDivision.getSelectionModel().getSelectedItem().toString(), country.getSelectionModel().getSelectedItem().toString());
            if (alertBot.confirmSave() == true) {
                dbCustomer.addCustomer(newCustomer);
                mainFormController.toMain(actionEvent);
            }
        } catch (NumberFormatException | NullPointerException e) {
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


