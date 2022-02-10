package controller;

import Main.LoginInterface;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.User;
import model.alertBot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/** This class houses the methods used to work with the loginForm scene */
public class loginFormController implements Initializable {
    public Label scheduleLabel;
    public Label signInLabel;
    public Label userLabel;
    public Label passwordLabel;
    public TextField usernameField;
    public TextField passwordField;
    public Button submitButton;
    public Label userLoc;

    /** This checks the location set on the user's computer and will change the text if the user's display language is french.
     *  LAMBDA Expression is used to return the string value of the user's system's Zone ID in string form allowing it to
     *  be displayed. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Locale.getDefault().getLanguage() == "fr") {
            scheduleLabel.setText("Assistant de planification");
            signInLabel.setText("S'identifier");
            userLabel.setText("Nom d'utilisateu");
            passwordLabel.setText("Mot de passe");
            submitButton.setText("Soumettre");
        }
        LoginInterface Loc = d -> ZoneId.systemDefault().toString();
        userLoc.setText(Loc.uLoc(ZoneId.systemDefault()));
    }

    /** This method checks if the username and password inputted by the user against the database. If the input is valid
     * it will call upcoming Appointment() to advise the user if there is an upcoming appointment within 15 minutes and
     * will take them to the main form. If the input is invalid it will call the invalidUser() method to inform the user
     * the username and password combination is invalid. It outputs login attempts to a text file.
     * @param actionEvent event that the user clicks the submit button.
     * @throws IOException from toMain() method.
     * @throws SQLException from upcomingAppointment() method.
     */
    public void onSubmitButton(ActionEvent actionEvent) throws IOException, SQLException {
        PrintWriter activity = new PrintWriter(new FileOutputStream(new File("login_activity.txt"), true ));
        if (User.validateUser(usernameField.getText(),passwordField.getText()) == true) {
            activity.append(("| User: "+ usernameField.getText()+" | Password: "+ passwordField.getText() +" | Time: "+ Timestamp.from(Instant.now()))+ " | Successful Login |" + "\n");
            activity.close();
            alertBot.upcomingAppointment();
            mainFormController.toMain(actionEvent);
        } else {
            activity.append(("| User: "+ usernameField.getText()+" | Password: "+ passwordField.getText() +"| Time: "+ Timestamp.from(Instant.now()))+ "| Invalid Login |" + "\n");
            activity.close();
            alertBot.invalidUser();
        }
    }


}
