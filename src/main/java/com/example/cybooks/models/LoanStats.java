package com.example.cybooks.models;

/**
 * The LoanStats class represents the statistics of a book loan in the CyBooks application.
 * It contains details about the book such as the title, author, editor, and the number of times it has been loaned.
 */
public class LoanStats {
    private String title;
    private String author;
    private String editor;
    private int loanCount;

    /**
     * Constructs a new LoanStats with the specified details.
     *
     * @param title the title of the book
     * @param author the author of the book
     * @param editor the editor of the book
     * @param loanCount the number of times the book has been loaned
     */
    public LoanStats(String title, String author, String editor, int loanCount) {
        this.title = title;
        this.author = author;
        this.editor = editor;
        this.loanCount = loanCount;
    }

    // Getters
    /**
     * Gets the title of the book.
     *
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the author of the book.
     *
     * @return the author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the editor of the book.
     *
     * @return the editor of the book
     */
    public String getEditor() {
        return editor;
    }

    /**
     * Gets the number of times the book has been loaned.
     *
     * @return the number of times the book has been loaned
     */
    public int getLoanCount() {
        return loanCount;
    }
}
