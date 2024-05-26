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
    public void CallAddMember(ActionEvent event){
        loadWindows("views/add_user.fxml","Ajout d'un utilisateur ");
    }

    /**
     * Opens the "Search Member" window when the corresponding action is triggered.
     *
     * @param event the action event triggered by the user
     */
    @FXML
    public void CallSearchMember(ActionEvent event){
        loadWindows("views/search_user.fxml","Recherche d'un membre ");


    }

    /**
     * Opens the "Search Book" window when the corresponding action is triggered.
     *
     * @param event the action event triggered by the user
     */
    @FXML
    public void CallSearchBook(ActionEvent event){
        loadWindows("views/search_books.fxml","Recherche livre ");


    }

    /**
     * Opens the "Issued List" window when the corresponding action is triggered.
     *
     * @param event the action event triggered by the user
     */
    @FXML
    public void Call_Issuedlist(ActionEvent event){
        loadWindows("views/delayed_list.fxml","Liste des retard");


    }

    /**
     * Opens the "Last 30 Days Issued List" window when the corresponding action is triggered.
     *
     * @param event the action event triggered by the user
     */
    @FXML
    public void CallLast30d(ActionEvent event){
        loadWindows("views/last30d_issued_list.fxml","Livres les plus empruntés ces 30 derniers jours");


    }

    /**
     * Opens the "Issued Books List" window when the corresponding action is triggered.
     *
     * @param event the action event triggered by the user
     */
    @FXML
    public void CallListBook(ActionEvent event){
        loadWindows("views/issued_list.fxml","Liste livres empruntés");


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
            Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
