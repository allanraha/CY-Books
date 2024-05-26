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

public class mainController {
    @FXML
    public void CallAddMember(ActionEvent event){
        loadWindows("views/add_user.fxml","Ajout d'un utilisateur ");
    }
    @FXML
    public void CallSearchMember(ActionEvent event){
        loadWindows("views/search_user.fxml","Recherche d'un membre ");


    }
    @FXML
    public void CallSearchBook(ActionEvent event){
        loadWindows("views/search_books.fxml","Recherche livre ");


    }
    @FXML
    public void Call_Issuedlist(ActionEvent event){
        loadWindows("views/delayed_list.fxml","Liste des retard");


    }
    @FXML
    public void CallLast30d(ActionEvent event){
        loadWindows("views/last30d_issued_list.fxml","Livres les plus empruntés ces 30 derniers jours");


    }
    @FXML
    public void CallListBook(ActionEvent event){
        loadWindows("views/issued_list.fxml","Liste livres empruntés");


    }
    void loadWindows(String loc, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
