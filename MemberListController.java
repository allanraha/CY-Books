package com.example.cybooks.controllers;

import com.example.cybooks.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MemberListController {

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, Integer> idCol;

    @FXML
    private TableColumn<User, String> emailCol;

    @FXML
    private TableColumn<User, String> firstNameCol;

    @FXML
    private TableColumn<User, String> lastNameCol;

    public void setUsers(List<User> users) {
        tableView.getItems().clear();
        tableView.getItems().addAll(users);
    }

    @FXML
    public void initialize() {
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    @FXML
    private void handleDeleteAction() throws SQLException {
        User selectedUser = tableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            selectedUser.deleteUser(selectedUser.getUserID());
            tableView.getItems().remove(selectedUser);
        } else {
            showAlert("Aucun utilisateur sélectionné", "Veuillez sélectionner un utilisateur à supprimer.");
        }
    }

    @FXML
    private void handleEditAction() {
        User selectedUser = tableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cybooks/views/edit_user.fxml"));
                Parent root = loader.load();

                EditUserController controller = loader.getController();
                controller.setUser(selectedUser);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                showAlert("Erreur", "Erreur lors du chargement de la page de modification : " + e.getMessage());
            }
        } else {
            showAlert("Aucun utilisateur sélectionné", "Veuillez sélectionner un utilisateur à modifier.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}