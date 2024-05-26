package com.example.cybooks.controllers;

import com.example.cybooks.models.Library;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BookSearchController {
    @FXML
    private TextField titlefield;


    @FXML
    private TextField authorfield;

    @FXML
    private TextField publisherfield;

    @FXML
    private void handleSearch(ActionEvent event) throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        String title = titlefield.getText();
        String author = authorfield.getText();
        String publisher = publisherfield.getText();

        // Construire la requête en fonction du titre saisi
        String queryString = buildQueryString(title, author, publisher);

        // Appeler la méthode pour rechercher les livres avec l'API de la BnF
        List<Map<String, String>> searchResults = Library.searchBooks(queryString);

        // Charger et afficher les résultats dans l'interface graphique
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cybooks/views/book_list.fxml"));
        Parent root = loader.load();
        BookResultsController controller = loader.getController();
        controller.displaySearchResults(searchResults);

        // Afficher la scène
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private String buildQueryString(String title, String author, String publisher) {
        String query = "";
        if (!title.isEmpty()) {
            query += "bib.title all \"" + title + "\"";
        }
        if (!author.isEmpty()) {
            if (query != "") {
                query += " and ";
            }
            query += "bib.author all \"" + author + "\"";
        }
        if (!publisher.isEmpty()) {
            if (query != "") {
                query += " and ";
            }
            query += "bib.publisher all \"" + publisher + "\"";
        }
        return query;
    }
}