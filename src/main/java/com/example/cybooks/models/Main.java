package com.example.cybooks.models;

import com.example.cybooks.models.Library;

import java.util.List;
import java.util.Map;
/**
 * The Main class is the entry point for the CyBooks application.
 * It performs a book search using the Library class and displays the search results.
 */
public class Main {
    public static void main(String[] args) {
        try {
            // Search for books with a specific search term
            String queryString = "bib.title any Jack   "; // Modify this term as needed
            List<Map<String, String>> searchResults = Library.searchBooks(queryString);

            // Display the search results
            System.out.println("Résultats de la recherche pour : " + queryString);
            for (Map<String, String> bookInfo : searchResults) {
                System.out.println("Titre : " + bookInfo.get("Title"));
                System.out.println("Auteur : " + bookInfo.get("Author"));
                System.out.println("Type de document : " + bookInfo.get("Document Type"));
                System.out.println("Éditeur : " + bookInfo.get("Publisher"));
                System.out.println("Date : " + bookInfo.get("Date"));
                System.out.println("--------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
