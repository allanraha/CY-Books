package com.example.cybooks.models;

/**
 * The Book class represents a book in the CyBooks application.
 * It contains details about the book such as title, author, and publisher.
 */
public class Book {
    private String title;
    private String author;
    private String publisher;

    /**
     * Constructs a new Book with the specified title, author, and publisher.
     *
     * @param title     the title of the book
     * @param author    the author of the book
     * @param publisher the publisher of the book
     */
    public Book(String title, String author, String publisher) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    /**
     * Gets the title of the book.
     *
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title the new title of the book
     */
    public void setTitle(String title) {
        this.title = title;
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
     * Sets the author of the book.
     *
     * @param author the new author of the book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the publisher of the book.
     *
     * @return the publisher of the book
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets the publisher of the book.
     *
     * @param publisher the new publisher of the book
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
