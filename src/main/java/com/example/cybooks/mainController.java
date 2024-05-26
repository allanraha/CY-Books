package com.example.cybooks;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MainController handles the user interactions from the main view of the CyBooks application.
 * It contains methods to load different views based on the user's actions.
 */
public class mainController {

    /**
     * Opens the "Add Member" window when the corresponding action is triggered.
     *
     * @param event the action event triggered by the user
     */
    @FXML
    public void callAddMember(ActionEvent event) {
        loadWindows("views/add_user.fxml", "Add a User");
    }

    /**
     * Opens the "Search Member" window when the corresponding action is triggered.
     *
     * @param event the action event triggered by the user
     */
    @FXML
    public void callSearchMember(ActionEvent event) {
        loadWindows("views/search_user.fxml", "Search for a Member");
    }

    /**
     * Opens the "Search Book" window when the corresponding action is triggered.
     *
     * @param event the action event triggered by the user
     */
    @FXML
    public void callSearchBook(ActionEvent event) {
        loadWindows("views/search_books.fxml", "Search for a Book");
    }

    /**
     * Opens the "Issued List" window when the corresponding action is triggered.
     *
     * @param event the action event triggered by the user
     */
    @FXML
    public void callIssuedList(ActionEvent event) {
        loadWindows("views/delayed_list.fxml", "Delayed List");
    }

    /**
     * Opens the "Last 30 Days Issued List" window when the corresponding action is triggered.
     *
     * @param event the action event triggered by the user
     */
    @FXML
    public void callLast30d(ActionEvent event) {
        loadWindows("views/last30d_issued_list.fxml", "Books Issued in the Last 30 Days");
    }

    /**
     * Opens the "Issued Books List" window when the corresponding action is triggered.
     *
     * @param event the action event triggered by the user
     */
    @FXML
    public void callListBook(ActionEvent event) {
        loadWindows("views/issued_list.fxml", "Issued Books List");
    }

    /**
     * Loads a new window with the specified FXML file and title.
     *
     * @param loc   the location of the FXML file
     * @param title the title of the new window
     */
    void loadWindows(String loc, String title) {
        try {
            // Load the FXML file for the specified view
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            // Create a new stage and set its properties
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            // Log any IO exceptions that occur
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
