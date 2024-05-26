package com.example.cybooks.controllers;

import com.example.cybooks.models.Loan;
import com.example.cybooks.services.Databaseconnexion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class IssuedListController {
    @FXML
    private TableView<Loan> tableView;
    @FXML
    private TableColumn<Loan, Integer> bookIDCol1;
    @FXML
    private TableColumn<Loan, String> bookNameCol1;
    @FXML
    private TableColumn<Loan, String> holderNameCol1;
    @FXML
    private TableColumn<Loan, LocalDate> issueCol1;
    @FXML
    private TableColumn<Loan, LocalDate> fineCol1;

    @FXML
    private MenuItem deleteLoanMenuItem;
    @FXML
    private MenuItem editLoanMenuItem;

    @FXML
    public void initialize() {
        bookIDCol1.setCellValueFactory(new PropertyValueFactory<>("idloan"));
        bookNameCol1.setCellValueFactory(new PropertyValueFactory<>("title"));
        holderNameCol1.setCellValueFactory(new PropertyValueFactory<>("userId"));
        issueCol1.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
        fineCol1.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        // Load issued books
        loadIssuedBooks();

        // Set action handlers for menu items
        deleteLoanMenuItem.setOnAction(event -> handleDeleteLoanAction());
        editLoanMenuItem.setOnAction(event -> handleEditLoanAction());
    }

    private void loadIssuedBooks() {
        try (Connection conn = Databaseconnexion.getConnection()) {
            String selectQuery = "SELECT * FROM loan";
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idloan");
                int userId = rs.getInt("userId");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String editor = rs.getString("editor");
                LocalDate loanDate = rs.getDate("loanDate").toLocalDate();
                LocalDate returnDate = rs.getDate("returnDate").toLocalDate();
                Loan loan = new Loan(id, userId, title, author, editor, loanDate, returnDate);
                tableView.getItems().add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteLoanAction() {
        Loan selectedLoan = tableView.getSelectionModel().getSelectedItem();
        if (selectedLoan != null) {
            try (Connection conn = Databaseconnexion.getConnection()) {
                selectedLoan.deleteLoan(conn);
                tableView.getItems().remove(selectedLoan);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleEditLoanAction() {
        Loan selectedLoan = tableView.getSelectionModel().getSelectedItem();
        if (selectedLoan != null) {
            showEditLoanDialog(selectedLoan);
        }
    }

    private void showEditLoanDialog(Loan loan) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cybooks/views/edit_loan.fxml"));
            Parent parent = loader.load();

            EditLoanController controller = loader.getController();
            controller.setLoanDetails(loan);

            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.setTitle("Modifier la Date de Retour");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
