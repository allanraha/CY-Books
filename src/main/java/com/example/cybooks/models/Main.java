package com.example.cybooks.models;

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
            String queryString = "bib.title any Jack"; // Modify this term as needed
            List<Map<String, String>> searchResults = Library.searchBooks(queryString);

            // Display the search results
            System.out.println("Search results for: " + queryString);
            for (Map<String, String> bookInfo : searchResults) {
                System.out.println("Title: " + bookInfo.get("Title"));
                System.out.println("Author: " + bookInfo.get("Author"));
                System.out.println("Document Type: " + bookInfo.get("Document Type"));
                System.out.println("Publisher: " + bookInfo.get("Publisher"));
                System.out.println("Date: " + bookInfo.get("Date"));
                System.out.println("--------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
