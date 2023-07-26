package com.github.trrine.librarysystem.service;

import com.github.trrine.librarysystem.model.Book;

import java.util.List;

public interface LibrarianService {
    BookInsertionStatus insertBook(Book book);

    List<Book> listAllBooks();

    List<Book> searchBooks(String isbn, String title, String author, String status);

    List<Book> listAvailableBooks();

    BookChangeStatus changeBookStatus(int bookNo, String newStatus);

    BookChangeStatus removeBook(int bookNo);
}
