package com.github.trrine.librarysystem.model;

import java.time.LocalDate;

public class Borrowing {
    private String userID;
    private int bookNo;
    private LocalDate startDate;
    private LocalDate dueDate;
    private BorrowingStatus status;

    public Borrowing(String userID, int bookNo, LocalDate startDate, LocalDate dueDate, BorrowingStatus status) {
        this.userID = userID;
        this.bookNo = bookNo;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public String getUserID() {
        return this.userID;
    }

    public int getBookNo() {
        return this.bookNo;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public BorrowingStatus getStatus() {
        return this.status;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setBookNo(int bookNo) {
        this.bookNo = bookNo;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(BorrowingStatus status) {
        this.status = status;
    }

}
