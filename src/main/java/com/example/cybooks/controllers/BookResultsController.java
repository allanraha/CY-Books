package com.example.cybooks.controllers;

import com.example.cybooks.models.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookResultsController {
    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book, String> titleCol;
    @FXML
    private TableColumn<Book, String> authorCol;
    @FXML
    private TableColumn<Book, String> publisherCol;

    @FXML
    private MenuItem addLoanMenuItem;

    @FXML
    public void initialize() {
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("Author"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("Publisher"));

        addLoanMenuItem.setOnAction(event -> handleAddLoanAction());
    }

    public void displaySearchResults(List<Map<String, String>> searchResults) {
        List<Book> books = searchResults.stream()
                .map(result -> new Book(result.get("Title"), result.get("Author"), result.get("Publisher")))
                .collect(Collectors.toList());

        ObservableList<Book> data = FXCollections.observableArrayList(books);
        tableView.setItems(data);
    }

    @FXML
    private void handleAddLoanAction() {
        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            showAddUserDialog(selectedBook);
        }
    }

    private void showAddUserDialog(Book book) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cybooks/views/add_loan.fxml"));
            Parent parent = loader.load();

            AddLoanController controller = loader.getController();
            controller.setBookDetails(book.getTitle(), book.getAuthor(), book.getPublisher());

            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.setTitle("Ajouter un Prêt");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}