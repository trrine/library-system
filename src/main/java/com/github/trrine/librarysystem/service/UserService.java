package com.github.trrine.librarysystem.service;

import com.github.trrine.librarysystem.model.Book;
import com.github.trrine.librarysystem.model.User;

import java.util.List;

public interface UserService {
    void registerUser(User user);

    User loginUser(String username, String password);

    void logoutUser(User user);

    void updateUser(User user);

    // Methods specific to librarians
    List<Book> getAllBorrowedBooks();

    void changeBookStatus(Book book, String status);
    // Other librarian-specific methods...

    // Methods specific to borrowers
    List<Book> getBorrowedBooks(User borrower);

    void borrowBook(User borrower, Book book, int duration);
    // Other borrower-specific methods...

}
