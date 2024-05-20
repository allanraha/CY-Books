package com.example.cybooks.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Databaseconnexion {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/cybooksbdd";
    private static final String USER = "root";
    private static final String PASSWORD = "cytech0001";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                System.out.println("Connexion à la base de données réussie !");
                connection.close(); // Fermer la connexion une fois le test réussi
            } else {
                System.out.println("La connexion à la base de données a échoué.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }
}
