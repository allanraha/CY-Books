package com.example.cybooks.models;

import com.example.cybooks.models.Library;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            // Recherche de livres avec un terme de recherche spécifique
            String queryString = "bib.title any Jack   "; // Modifiez ce terme selon vos besoins
            List<Map<String, String>> searchResults = Library.searchBooks(queryString);

            // Affichage des résultats de la recherche
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
