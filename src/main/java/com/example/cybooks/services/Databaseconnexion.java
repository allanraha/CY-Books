package com.example.cybooks.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnection handles the database connection for the CyBooks application.
 * It provides a method to establish a connection to the MySQL database.
 */
public class DatabaseConnection {

    // Database URL, username, and password
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/cybooksbdd";
    private static final String USER = "root";
    private static final String PASSWORD = "cytech0001";

    /**
     * Establishes a connection to the database using the provided URL, user, and password.
     *
     * @return a Connection object if the connection is successful
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Main method for testing the database connection.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Attempt to establish a connection
            Connection connection = getConnection();
            if (connection != null) {
                // Connection successful
                System.out.println("Connection to the database was successful!");
                connection.close(); // Close the connection after the test
            } else {
                // Connection failed
                System.out.println("Connection to the database failed.");
            }
        } catch (SQLException e) {
            // Print an error message if the connection fails
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }
}
