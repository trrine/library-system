package com.github.trrine.librarysystem.model;

public class Book {
    private int bookNo;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private BookStatus status;

    public Book(int bookNo, String isbn, String title, String author, String publisher, BookStatus status) {
        this.bookNo = bookNo;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.status = status;
    }

    public int getBookNo() {
        return this.bookNo;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public BookStatus getStatus() {
        return this.status;
    }

    public void setBookNo(int bookNo) {
        this.bookNo = bookNo;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}
