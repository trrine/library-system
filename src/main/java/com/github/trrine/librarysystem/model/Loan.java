package com.github.trrine.librarysystem.model;

import java.time.LocalDate;

public class Loan {
    private int userID;
    private int bookNo;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LoanStatus status;

    public Loan(int userID, int bookNo, LocalDate startDate, LocalDate dueDate, LoanStatus status) {
        this.userID = userID;
        this.bookNo = bookNo;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public int getUserID() {
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

    public LoanStatus getStatus() {
        return this.status;
    }

    public void setUserID(int userID) {
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

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

}
