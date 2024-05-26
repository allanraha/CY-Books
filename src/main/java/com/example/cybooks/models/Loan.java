package com.example.cybooks.models;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Loan {
    private int idloan;
    private int userId;
    private String title;
    private String author;
    private String editor;
    private LocalDate loanDate;
    private LocalDate returnDate;

    // Limite de livres empruntés par utilisateur
    private static final int MAX_LOANS_PER_USER = 5;
    // Durée du prêt en mois
    private static final int LOAN_DURATION_MONTHS = 2;

    // Constructeur avec id
    public Loan(int idloan, int userId, String title, String author, String editor, LocalDate loanDate, LocalDate returnDate) {
        this.idloan = idloan;
        this.userId = userId;
        this.title = title;
        this.author = author;
        this.editor = editor;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    // Constructeur sans id
    public Loan(int userId, String title, String author, String editor) {
        this.userId = userId;
        this.title = title;
        this.author = author;
        this.editor = editor;
        this.loanDate = LocalDate.now();
        this.returnDate = loanDate.plusMonths(LOAN_DURATION_MONTHS);
    }

    // Getters et Setters
    public int getIdloan() {
        return idloan;
    }

    public void setIdloan(int idloan) {
        this.idloan = idloan;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    // Suppression d'un prêt
    public void deleteLoan(Connection conn) throws SQLException {
        String deleteLoanQuery = "DELETE FROM loan WHERE idloan = ?";
        try (PreparedStatement stmt = conn.prepareStatement(deleteLoanQuery)) {
            stmt.setInt(1, this.idloan);
            stmt.executeUpdate();
        }
    }

    // Méthode pour ajouter un prêt
    public static boolean addLoan(Connection conn, Loan newLoan) throws Exception {
        // Vérifier le nombre maximum de prêts par utilisateur
        int userLoanCount = getUserLoanCount(conn, newLoan.getUserId());
        if (userLoanCount >= MAX_LOANS_PER_USER) {
            throw new Exception("Le nombre maximum de prêts par utilisateur est atteint.");
        }

        // Ajouter l'emprunt dans la base de données
        String insertLoanQuery = "INSERT INTO loan (userId, title, author, editor, loanDate, returnDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertLoanQuery)) {
            stmt.setInt(1, newLoan.getUserId());
            stmt.setString(2, newLoan.getTitle());
            stmt.setString(3, newLoan.getAuthor());
            stmt.setString(4, newLoan.getEditor());
            stmt.setDate(5, Date.valueOf(newLoan.getLoanDate()));
            stmt.setDate(6, Date.valueOf(newLoan.getReturnDate()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour obtenir le nombre de prêts actuels d'un utilisateur
    private static int getUserLoanCount(Connection conn, int userId) throws SQLException {
        String countQuery = "SELECT COUNT(*) FROM loan WHERE userId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(countQuery)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        return 0;
    }

    // Méthode pour obtenir les livres les plus empruntés au cours des 30 derniers jours
    public static List<LoanStats> getTopLoansInLast30Days(Connection conn) throws SQLException {
        String query = "SELECT title, author, editor, COUNT(*) as loanCount " +
                "FROM loan " +
                "WHERE loanDate >= NOW() - INTERVAL '30' DAY " +
                "GROUP BY title, author, editor " +
                "ORDER BY loanCount DESC " +
                "LIMIT 5";
        List<LoanStats> topLoans = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String editor = rs.getString("editor");
                int loanCount = rs.getInt("loanCount");
                topLoans.add(new LoanStats(title, author, editor, loanCount));
            }
        }
        return topLoans;
    }
    public void updateReturnDate(Connection conn) throws SQLException {
        String updateQuery = "UPDATE loan SET returnDate = ? WHERE idloan = ?";
        try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            stmt.setDate(1, Date.valueOf(this.returnDate));
            stmt.setInt(2, this.idloan);
            stmt.executeUpdate();
        }
    }
    public static List<Loan> getDelayedLoans(Connection conn) throws SQLException {
        String query = "SELECT * FROM loan WHERE returnDate < ?";
        List<Loan> delayedLoans = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idloan = rs.getInt("idloan");
                int userId = rs.getInt("userId");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String editor = rs.getString("editor");
                LocalDate loanDate = rs.getDate("loanDate").toLocalDate();
                LocalDate returnDate = rs.getDate("returnDate").toLocalDate();
                delayedLoans.add(new Loan(idloan, userId, title, author, editor, loanDate, returnDate));
            }
        }
        return delayedLoans;


}}
