package jgaul;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jgaul.DAO.JDBC;
import jgaul.utility.Helper;

import java.util.Locale;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        stage.setScene(new Scene(root, 1000, 600));
        stage.show();
    }

    public static void main(String[] args) {
        // Locale.setDefault(new Locale("fr"));
        JDBC.makeConnection();
        Helper.initializeConstantFields();
        launch(args);
        JDBC.closeConnection();
    }
}
