package controller;

import DBHelper.dbAppointments;
import DBHelper.dbCustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;
import model.alertBot;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/** This class houses the methods used to work with the mainForm scene */
public class mainFormController implements Initializable {
    public TableView appointmentsTable;
    public TableColumn appointmentID;
    public TableColumn title;
    public TableColumn description;
    public TableColumn loc;
    public TableColumn contact;
    public TableColumn type;
    public TableColumn startDate;
    public TableColumn endDate;
    public TableColumn appCustomerID;
    public TableColumn userID;
    public TableView customersTable;
    public TableColumn customerID;
    public TableColumn name;
    public TableColumn address;
    public TableColumn postalCode;
    public TableColumn phoneNumber;
    public TableColumn division;

    /** This sets the items of the customers and appointments tables. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customersTable.setItems(dbCustomer.getAllCustomers());
            customerID.setCellValueFactory(new PropertyValueFactory<>("id"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            address.setCellValueFactory(new PropertyValueFactory<>("address"));
            postalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
            phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            division.setCellValueFactory(new PropertyValueFactory<>("division"));

            appointmentsTable.setItems(dbAppointments.getAllAppointments());
            appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            title.setCellValueFactory(new PropertyValueFactory<>("title"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            loc.setCellValueFactory(new PropertyValueFactory<>("location"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            appCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            userID.setCellValueFactory(new PropertyValueFactory<>("userID"));
            contact.setCellValueFactory(new PropertyValueFactory<>("contactName"));


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /** This method returns the selected customer or calls selectCustomer() to alert the user to select a customer.
     *
     * @return null
     */
    public Customer getSelectedCustomer() {
        if (customersTable.getSelectionModel().getSelectedItem() != null) {
            return (Customer) customersTable.getSelectionModel().getSelectedItem();
        } else {
            alertBot.selectCustomer();
        }
        return null;
    }

    /** This method returns the selected appointment or calls selectAppointment() to alert the user to select an appointment.
     *
     * @return null
     */
    public Appointment getSelectedAppointment() {
        if (appointmentsTable.getSelectionModel().getSelectedItem() != null) {
            return (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();
        } else {
            alertBot.selectAppointment();
        }
        return null;
    }

    /** This method loads and displays the addAppointment form.
     *
     * @param actionEvent event that the user clicks the add appointment button.
     * @throws IOException from FXMLLoader.load
     */
    public void onAddAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(addAppointmentController.class.getResource("/view/addAppointment.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /** This method loads and displays the updateAppointment form. It takes the selected appointment and sends the data
     *  by calling the dataReceiver method.
     * @param actionEvent event that the user clicks the add appointment button.
     * @throws IOException from FXMLLoader.load
     */
    public void onUpdateAppointment(ActionEvent actionEvent) throws IOException {
        try {
            Appointment appointment = getSelectedAppointment();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/updateAppointment.fxml"));
            loader.load();

            updateAppointmentController uAController = loader.getController();
            uAController.dataReceiver(appointment);

            Parent scene = loader.getRoot();
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (NullPointerException e){} {

        }
    }

    /** This method confirms if the user wants to delete the selected appointment, removes it from the database by
     *  calling deleteAppointment() and alerts the user of the deleted appointment. It also sets the items of the appointments
     *  table with the updated list.
     * @param actionEvent event that the user clicks the delete appointment button.
     * @throws SQLException from getAllAppointments()
     */
    public void onDeleteAppointment(ActionEvent actionEvent) throws SQLException {
        if (getSelectedAppointment() != null) {
            if (alertBot.confirmDelete() == true) {
                Appointment appointment = getSelectedAppointment();
                dbAppointments.deleteAppointment(getSelectedAppointment());
                appointmentsTable.setItems(dbAppointments.getAllAppointments());
                alertBot.appointmentDeleted(appointment);
            }
        }
    }

    /** This method is used to load and display the main form. *
     *
     * @param actionEvent event that a button is pressed.
     * @throws IOException from FXMLLoader.load
     */
    public static void toMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(mainFormController.class.getResource("/view/mainForm.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** This method is used to load and display the addCustomer form.
     *
     * @param actionEvent event that the user clicks the add customer button.
     * @throws IOException from FXMLLoader.load
     */
    public void onAddCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(addCustomerController.class.getResource("/view/addCustomer.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** This method loads and displays the updateCustomer form. It takes the selected customer and sends the data
     *  by calling the dataReceiver method.
     * @param actionEvent event that the user clicks the add customer button.
     * @throws IOException from FXMLLoader.load
     */
    public void onUpdateCustomer(ActionEvent actionEvent) throws IOException{
        try {
            Customer customer = getSelectedCustomer();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/updateCustomer.fxml"));
            loader.load();

            updateCustomerController ucController = loader.getController();
            ucController.dataReceiver(customer);

            Parent scene = loader.getRoot();
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException e){}

    }

    /** This method confirms if the user wants to delete the selected customer, removes it from the database and any appointments
     *  with the customer by calling deleteCustomer() and alerts the user of the deleted customer. It also sets the items of the customer
     *  table with the updated list.
     * @param actionEvent event that the user clicks the delete customer button.
     * @throws SQLException from getAllAppointments() and getAllCustomers()
     */
    public void onDeleteCustomer(ActionEvent actionEvent) throws SQLException {
        if (getSelectedCustomer() != null) {
            if (alertBot.confirmDelete() == true) {
                dbCustomer.deleteCustomer(getSelectedCustomer());
                customersTable.setItems(dbCustomer.getAllCustomers());
                appointmentsTable.setItems(dbAppointments.getAllAppointments());
                alertBot.customerDeleted();
            }
        }
    }

    /** This displays all appointments
     *
     * @param actionEvent event that the user clicks the all radio button
     * @throws SQLException from getAllAppointments()
     */
    public void onAll(ActionEvent actionEvent) throws SQLException {
        appointmentsTable.setItems(dbAppointments.getAllAppointments());
    }

    /** This displays all appointments in the current week
     *
     * @param actionEvent event that the user clicks the week radio button.
     * @throws SQLException from currentWeekAppointments()
     */
    public void onWeek(ActionEvent actionEvent) throws SQLException {
        appointmentsTable.setItems(dbAppointments.currentWeekAppointments());
    }

    /** This displays all appointments in the current month
     *
     * @param actionEvent event that the user clicks the month radio button.
     * @throws SQLException from currentMonthAppointments()
     */
    public void onMonth(ActionEvent actionEvent) throws SQLException {
        appointmentsTable.setItems(dbAppointments.currentMonthAppointments());
    }

    /** This loads and displays the reportsForm scene
     *
     * @param actionEvent event that the user clicks the reports button.
     * @throws IOException from FXMLLoader.load
     */
    public void onReportsButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(addCustomerController.class.getResource("/view/reportsForm.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
