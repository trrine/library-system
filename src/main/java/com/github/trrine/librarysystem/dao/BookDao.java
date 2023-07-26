package com.github.trrine.librarysystem.dao;

import com.github.trrine.librarysystem.model.Book;

import java.util.List;

public interface BookDao {
    void createBook(Book book);

    Book getBookByBookNo(int bookNo);

    List<Book> searchBooks(String isbn, String title, String author, String status);

    void updateBook(Book book);

    void deleteBook(int bookNo);
}
