package com.example.cybooks.models;

public class LoanStats {
    private String title;
    private String author;
    private String editor;
    private int loanCount;

    public LoanStats(String title, String author, String editor, int loanCount) {
        this.title = title;
        this.author = author;
        this.editor = editor;
        this.loanCount = loanCount;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getEditor() {
        return editor;
    }

    public int getLoanCount() {
        return loanCount;
    }
}
