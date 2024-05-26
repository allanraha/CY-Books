package com.example.cybooks.controllers;

import com.example.cybooks.models.Loan;
import com.example.cybooks.models.LoanStats;
import com.example.cybooks.services.Databaseconnexion;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class last30d_issued_listController {
    @FXML
    private TableView<LoanStats> tableView;
    @FXML
    private TableColumn<LoanStats, String> titleCol;
    @FXML
    private TableColumn<LoanStats, String> authorCol;
    @FXML
    private TableColumn<LoanStats, String> publisherCol;
    @FXML
    private TableColumn<LoanStats, Integer> availabilityCol;

    @FXML
    public void initialize() {
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("editor"));
        availabilityCol.setCellValueFactory(new PropertyValueFactory<>("loanCount"));

        try {
            Connection conn = Databaseconnexion.getConnection();
            List<LoanStats> topLoans = Loan.getTopLoansInLast30Days(conn);
            tableView.getItems().setAll(topLoans);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
