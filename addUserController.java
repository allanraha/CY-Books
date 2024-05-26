
package com.example.cybooks.controllers;
import javafx.scene.control.Alert.AlertType;

import com.example.cybooks.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class addUserController {

        @FXML
        private TextField firstNameField;

        @FXML
        private TextField lastNameField;

        @FXML
        private TextField emailField;

        @FXML
        private Button saveButton;

        @FXML
        private Button cancelButton;
        private Stage stage;

        @FXML
        private void initialize() {
            saveButton.setOnAction(event -> addUser()); //Le bouton enregistrer va activer la méthode addUser
            cancelButton.setOnAction(event -> clearFields()); //Le bouton annuler va effacer le texte
        }

        public void setStage(Stage stage) {
            this.stage = stage;
        }

        private void addUser() {
            String firstName = firstNameField.getText(); //Recupere le texte ecrit dans le lable prenom
            String lastName = lastNameField.getText(); //Recupere le texte ecrit dans le lable nom
            String email = emailField.getText(); //Recupere le texte ecrit dans le lable email

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) { //Verifie si les champs sont vide
                showAlert(Alert.AlertType.ERROR, "Champs manquants", "Veuillez remplir tous les champs.");
                return;
            }
            User user = new User(0, firstName, lastName, email); //intancie un utilisateur avec un userid =0 et les autres infos remplies
            try {
                user.addUser();
                showAlert(AlertType.INFORMATION, "Succès", "Utilisateur ajouté avec succès !");
                clearFields(); // Efface les champs après un ajout réussi
            } catch (SQLException e) {
                showAlert(AlertType.ERROR, "Erreur", "Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
            }
        }



        private void clearFields() {
            firstNameField.clear();
            lastNameField.clear();
            emailField.clear();
        }

        private void showAlert(Alert.AlertType alertType, String title, String content) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setContentText(content);
            alert.showAndWait();
        }
    }
