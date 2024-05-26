package com.example.cybooks.controllers;

import com.example.cybooks.models.Loan;
import com.example.cybooks.services.Databaseconnexion;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class delayed_listController {

    @FXML
    private TableView<Loan> tableView;

    @FXML
    private TableColumn<Loan, Integer> idCol;

    @FXML
    private TableColumn<Loan, Integer> userIdCol;

    @FXML
    private TableColumn<Loan, String> titleCol;





    @FXML
    private TableColumn<Loan, LocalDate> returnDateCol;

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("idloan"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        returnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        loadDelayedLoans();
    }

    private void loadDelayedLoans() {
        try (Connection connection = Databaseconnexion.getConnection()) {
            List<Loan> delayedLoans = Loan.getDelayedLoans(connection);
            tableView.getItems().setAll(delayedLoans);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
