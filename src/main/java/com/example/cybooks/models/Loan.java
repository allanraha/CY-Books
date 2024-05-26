package com.example.cybooks.models;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The Loan class represents a book loan in the CyBooks application.
 * It contains details about the loan such as the loan ID, user ID, book title, author, editor, loan date, and return date.
 */
public class Loan {
    private int idloan;
    private int userId;
    private String title;
    private String author;
    private String editor;
    private LocalDate loanDate;
    private LocalDate returnDate;

     // Maximum number of books a user can borrow
    private static final int MAX_LOANS_PER_USER = 5;
    // Loan duration in months
    private static final int LOAN_DURATION_MONTHS = 2;

    /**
     * Constructs a new Loan with the specified details including loan ID.
     *
     * @param idloan the ID of the loan
     * @param userId the ID of the user
     * @param title the title of the book
     * @param author the author of the book
     * @param editor the editor of the book
     * @param loanDate the date when the loan was made
     * @param returnDate the date when the loan should be returned
     */
    public Loan(int idloan, int userId, String title, String author, String editor, LocalDate loanDate, LocalDate returnDate) {
        this.idloan = idloan;
        this.userId = userId;
        this.title = title;
        this.author = author;
        this.editor = editor;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    /**
     * Constructs a new Loan with the specified details without loan ID.
     * The loan date is set to the current date and the return date is calculated based on the loan duration.
     *
     * @param userId the ID of the user
     * @param title the title of the book
     * @param author the author of the book
     * @param editor the editor of the book
     */
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

    /**
     * Deletes this loan from the database.
     *
     * @param conn the database connection
     * @throws SQLException if a database access error occurs
     */
    public void deleteLoan(Connection conn) throws SQLException {
        String deleteLoanQuery = "DELETE FROM loan WHERE idloan = ?";
        try (PreparedStatement stmt = conn.prepareStatement(deleteLoanQuery)) {
            stmt.setInt(1, this.idloan);
            stmt.executeUpdate();
        }
    }

    /**
     * Adds a new loan to the database.
     *
     * @param conn the database connection
     * @param newLoan the new loan to be added
     * @return true if the loan was added successfully, false otherwise
     * @throws Exception if the maximum number of loans per user is exceeded
     */
    public static boolean addLoan(Connection conn, Loan newLoan) throws Exception {
        // Check the maximum number of loans per user
        int userLoanCount = getUserLoanCount(conn, newLoan.getUserId());
        if (userLoanCount >= MAX_LOANS_PER_USER) {
            throw new Exception("Le nombre maximum de prêts par utilisateur est atteint.");
        }

        // Add the loan to the database
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

    /**
     * Gets the current number of loans for a user.
     *
     * @param conn the database connection
     * @param userId the ID of the user
     * @return the number of loans for the user
     * @throws SQLException if a database access error occurs
     */
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

    /**
     * Gets the top 5 most borrowed books in the last 30 days.
     *
     * @param conn the database connection
     * @return a list of LoanStats objects representing the top borrowed books
     * @throws SQLException if a database access error occurs
     */
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

    /**
     * Updates the return date for this loan in the database.
     *
     * @param conn the database connection
     * @throws SQLException if a database access error occurs
     */
    public void updateReturnDate(Connection conn) throws SQLException {
        String updateQuery = "UPDATE loan SET returnDate = ? WHERE idloan = ?";
        try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            stmt.setDate(1, Date.valueOf(this.returnDate));
            stmt.setInt(2, this.idloan);
            stmt.executeUpdate();
        }
    }

    /**
     * Gets a list of loans that are delayed.
     *
     * @param conn the database connection
     * @return a list of delayed loans
     * @throws SQLException if a database access error occurs
     */
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
