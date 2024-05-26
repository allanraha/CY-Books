package com.example.cybooks.controllers;

import com.example.cybooks.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EditUserController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    private User user;

    public void setUser(User user) {
        this.user = user;
        firstNameField.setText(user.getFirst_name());
        lastNameField.setText(user.getLast_name());
        emailField.setText(user.getEmail());
    }

    @FXML
    private void handleSaveAction() {
        user.setFirst_name(firstNameField.getText());
        user.setLast_name(lastNameField.getText());
        user.setEmail(emailField.getText());

        user.updateUser(user.getUserID());
        closeWindow();
    }

    @FXML
    private void handleCancelAction() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}