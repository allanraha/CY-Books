package com.example.cybooks.controllers;

import com.example.cybooks.models.User;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.List;

public class SearchUserController {

    @FXML
    private TextField searchField;

    @FXML
    private JFXButton searchButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    void handleSearchAction(ActionEvent event) {
        String searchTerm = searchField.getText();
        List<User> users = User.findUser(searchTerm);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cybooks/views/member_list.fxml"));
            Parent root = loader.load();

            MemberListController controller = loader.getController();
            controller.setUsers(users);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) searchButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleCancelAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
