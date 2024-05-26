package com.example.cybooks.models;

import com.example.cybooks.services.Databaseconnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in the CyBooks application.
 */
public class User {
    private int userID;
    private String first_name;
    private String last_name;
    private String email;

    // Constructor
    public User(int userID, String first_name, String last_name, String email) {
        this.userID = userID;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    // Getters and setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Adds a new user to the database.
     * 
     * @throws SQLException if a database access error occurs.
     */
    public void addUser() throws SQLException {
        String query = "INSERT INTO users (first_name, last_name, email) VALUES (?, ?, ?)";
        try (Connection conn = Databaseconnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.first_name);
            stmt.setString(2, this.last_name);
            stmt.setString(3, this.email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Duplicate email error code
                throw new SQLException("This email is already registered.");
            } else if (e.getMessage().contains("NOT NULL constraint failed")) { // Null field error
                throw new SQLException("Fields cannot be null.");
            } else {
                throw new SQLException("Database error: " + e.getMessage());
            }
        }
    }

    /**
     * Deletes a user and their associated loans from the database.
     * 
     * @param id the ID of the user to delete.
     * @throws SQLException if a database access error occurs.
     */
    public void deleteUser(int id) throws SQLException {
        String deleteLoansQuery = "DELETE FROM loan WHERE userId = ?";
        String deleteUserQuery = "DELETE FROM users WHERE userId = ?";

        try (Connection conn = Databaseconnexion.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement deleteLoansStmt = conn.prepareStatement(deleteLoansQuery);
                 PreparedStatement deleteUserStmt = conn.prepareStatement(deleteUserQuery)) {

                // Delete associated loans
                deleteLoansStmt.setInt(1, id);
                deleteLoansStmt.executeUpdate();

                // Delete the user
                deleteUserStmt.setInt(1, id);
                deleteUserStmt.executeUpdate();

                // Commit transaction
                conn.commit();
            } catch (SQLException e) {
                // Rollback in case of error
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    /**
     * Updates the user information in the database.
     * 
     * @param id the ID of the user to update.
     */
    public void updateUser(int id) {
        String query = "UPDATE users SET first_name = ?, last_name = ?, email = ? WHERE userID = ?";
        try (Connection conn = Databaseconnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.first_name);
            stmt.setString(2, this.last_name);
            stmt.setString(3, this.email);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Displays all users from the database.
     */
    public static void displayAllUsers() {
        String query = "SELECT * FROM users";
        try (Connection conn = Databaseconnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("userID");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                System.out.println("UserID: " + id + ", First Name: " + firstName + ", Last Name: " + lastName + ", Email: " + email);
            }
        } catch (SQLException e) {
            System.out.println("Error displaying users: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Searches for users based on a search term.
     * 
     * @param searchTerm the term to search for.
     * @return a list of users matching the search term.
     */
    public static List<User> findUser(String searchTerm) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ? OR CAST(userID AS CHAR) LIKE ?";
        try (Connection conn = Databaseconnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            String searchPattern = "%" + searchTerm + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            stmt.setString(3, searchPattern);
            stmt.setString(4, searchPattern);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("userID");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String email = rs.getString("email");
                    users.add(new User(id, firstName, lastName, email));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
