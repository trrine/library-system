package com.github.trrine.librarysystem.service;

import com.github.trrine.librarysystem.model.Book;

import java.time.LocalDate;
import java.util.List;

public interface BorrowerService {
    void borrowBook(String userID, int bookNo, LocalDate startDate, LocalDate endDate);

    List<Book> listAvailableBooks();

    List<Book> listBorrowedBooks(String userID);

    List<Book> listCurrentlyBorrowedBooks(String userID);

    List<Book> searchBooks(String isbn, String title, String author, String status);
}
