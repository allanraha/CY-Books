package com.example.cybooks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * MainApp is the entry point for the CyBooks JavaFX application.
 * It extends the Application class and overrides the start method to set up the primary stage.
 */
public class MainApp extends Application {

     /**
     * The start method is called when the application is launched.
     * It loads the FXML file and sets up the primary stage with the loaded scene.
     *
     * @param primaryStage the primary stage for this application, onto which 
     *                     the application scene can be set.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file for the main view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cybooks/views/main.fxml"));
            Parent root = loader.load();
            
            // Set the scene for the primary stage
            primaryStage.setScene(new Scene(root));

            // Show the primary stage
            primaryStage.show();
        } catch (IOException e) {
            // Print stack trace in case of an IOException
            e.printStackTrace();
        }
    }
    
    /**
     * The main method is the entry point of the application.
     * It launches the JavaFX application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
