package com.github.trrine.librarysystem.service;

import com.github.trrine.librarysystem.model.Book;

import java.util.List;

public interface LibrarianService<T> {
    void insertBook(Book book);

    List<Book> listAllBooks();

    List<Book> searchBooks(String isbn, String title, String author, String status);

    List<Book> listAvailableBooks();

    void changeBookStatus(int bookNo, String newStatus);

    void removeBook(int bookNo);
}
