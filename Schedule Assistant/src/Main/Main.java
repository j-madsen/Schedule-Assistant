package Main;

import DBHelper.dbConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        primaryStage.setTitle("Schedule Assistant");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();



    }

    /** This method launches the application*/
    public static void main(String[] args) {
        dbConnector.openConnection();
        launch(args);

        dbConnector.closeConnection();
    }

}

