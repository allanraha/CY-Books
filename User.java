package com.example.cybooks.models;

import com.example.cybooks.services.Databaseconnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int userID;
    private String first_name;
    private String last_name;
    private String email;

    // Constructeur
    public User(int userID, String first_name, String last_name, String email) {
        this.userID = userID;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    // Getters et setters
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

    // Méthode pour ajouter un utilisateur
    public void addUser() {
        String query = "INSERT INTO users (first_name, last_name, email) VALUES (?, ?, ?)";
        try (Connection conn = Databaseconnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.first_name);
            stmt.setString(2, this.last_name);
            stmt.setString(3, this.email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Code d'erreur pour duplication de meme mail
                System.out.println("Cet email est déjà enregistré.");
            } else if (e.getMessage().contains("NOT NULL constraint failed")) {//Champs remplis null
                System.out.println("Les champs ne doivent pas être nuls.");
            } else {
                System.out.println("Erreur de la base de données : " + e.getMessage());
            }
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un utilisateur
    public void deleteUser(int id) {
        String query = "DELETE FROM users WHERE userId = ?";
        try (Connection conn = Databaseconnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Méthode pour mettre à jour un utilisateur
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
            System.out.println("Erreur lors de la mise à jour de l'utilisateur : " + e.getMessage());
            e.printStackTrace();
        }
    }
    // Méthode pour afficher tous les utilisateurs
    public static void displayAllUsers() {
        String query = "SELECT * FROM users";
        try (Connection conn = Databaseconnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {//excute et recupere le resultat sous forme de resultat set
             while (rs.next()) { //Parcour chq ligne du rs
                int id = rs.getInt("userID");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                System.out.println("UserID: " + id + ", First Name: " + firstName + ", Last Name: " + lastName + ", Email: " + email);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des utilisateurs : " + e.getMessage());
            e.printStackTrace();
        }
    }
    // Méthode pour rechercher des utilisateurs par différents critères
    public static void findUser(String searchTerm) {
        String query = "SELECT * FROM users WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ?";
        try (Connection conn = Databaseconnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            String searchPattern = "%" + searchTerm + "%"; //initialise un partern ou il peut avoir n'importe quel debut et fin entre le mot inscrit
            stmt.setString(1, searchPattern);//on cherche dans fistname
            stmt.setString(2, searchPattern);//on cherche dans last_name
            stmt.setString(3, searchPattern);//on cherche dans email
            try (ResultSet rs = stmt.executeQuery()) {
                boolean hasResults = false; // initialise un boleen de resultat de recherche
                while (rs.next()) {
                    hasResults = true; //si vrai on affiche l'user
                    int id = rs.getInt("userID");// recup les infos
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String email = rs.getString("email");
                    System.out.println("UserID: " + id + ", First Name: " + firstName + ", Last Name: " + lastName + ", Email: " + email);
                }
                if (!hasResults) {
                    System.out.println("Aucun utilisateur trouvé avec le terme de recherche : " + searchTerm);// si resu faux = aucun user trouvé
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de l'utilisateur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
