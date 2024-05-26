package com.example.cybooks.controllers;

import com.example.cybooks.models.Loan;
import com.example.cybooks.services.Databaseconnexion;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class EditLoanController {
    @FXML
    private TextField returnDateField;

    private Loan loan;

    public void setLoanDetails(Loan loan) {
        this.loan = loan;
        returnDateField.setText(loan.getReturnDate().toString());
    }

    @FXML
    private void handleSaveAction() {
        String newReturnDate = returnDateField.getText();
        if (newReturnDate != null && !newReturnDate.isEmpty()) {
            LocalDate returnDate = LocalDate.parse(newReturnDate);
            loan.setReturnDate(returnDate);
            try (Connection conn = Databaseconnexion.getConnection()) {
                loan.updateReturnDate(conn);
                closeWindow();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleCancelAction() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) returnDateField.getScene().getWindow();
        stage.close();
    }
}
