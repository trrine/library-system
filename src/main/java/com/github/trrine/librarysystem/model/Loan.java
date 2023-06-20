package com.github.trrine.librarysystem.model;

import java.time.LocalDate;

public class Loan {
    private User borrower;
    private Book book;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LoanStatus status;

    public Loan(User borrower, Book book, LocalDate startDate, LocalDate dueDate, LoanStatus status) {
        this.borrower = borrower;
        this.book = book;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public User getBorrower() {
        return this.borrower;
    }

    public Book getBook() {
        return this.book;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public LoanStatus getStatus() {
        return this.status;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

}
