package com.example.cybooks.controllers;

import com.example.cybooks.models.Loan;
import com.example.cybooks.services.Databaseconnexion;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;

import java.sql.Connection;
import java.sql.SQLException;

public class AddLoanController {
    @FXML
    private TextField userIdField;
    @FXML
    private JFXButton searchButton;
    @FXML
    private JFXButton cancelButton;

    private String title;
    private String author;
    private String publisher;

    @FXML
    public void initialize() {
        searchButton.setOnAction(event -> handleConfirm());
        cancelButton.setOnAction(event -> handleCancel());
    }

    public void setBookDetails(String title, String author, String publisher) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    @FXML
    private void handleConfirm() {
        try {
            int userId = Integer.parseInt(userIdField.getText());
            Loan newLoan = new Loan(userId, title, author, publisher);

            try (Connection conn = Databaseconnexion.getConnection()) {
                boolean success = Loan.addLoan(conn, newLoan);
                if (success) {
                    showSuccessMessage();
                } else {
                    showErrorMessage("Erreur", "Échec de l'ajout du prêt.");
                }
                handleClose();
            } catch (SQLException e) {
                e.printStackTrace();
                showErrorMessage("Erreur", "Une erreur SQL est survenue.");
            } catch (Exception e) {
                e.printStackTrace();
                showErrorMessage("Erreur", e.getMessage());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            showErrorMessage("Erreur", "ID utilisateur non valide.");
        }
    }

    private void showSuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Prêt ajouté avec succès !");
        alert.showAndWait();
    }

    private void showErrorMessage(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void handleCancel() {
        handleClose();
    }

    private void handleClose() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
