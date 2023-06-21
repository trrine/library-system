package com.github.trrine.librarysystem.dao;

import com.github.trrine.librarysystem.model.Book;

import java.util.List;

public interface BookDao<T> {
    void createBook(Book book);

    Book getBookByBookNo(int bookNo);

    List<Book> getBooksByISBN(String isbn);

    List<Book> getBooksByTitle(String title);

    List<Book> getBooksByAuthor(String author);

    List<Book> getBooksByStatus(String status);

    void updateBook(Book book);

    void deleteBook(int bookNo);
}
