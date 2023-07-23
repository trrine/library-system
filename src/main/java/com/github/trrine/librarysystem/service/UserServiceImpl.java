package com.github.trrine.librarysystem.service;

import com.github.trrine.librarysystem.model.Book;
import com.github.trrine.librarysystem.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    public void registerUser(User user) {
        
    }

    public User loginUser(String username, String password) {
        return null;
    }

    public void logoutUser(User user) {

    }

    public void updateUser(User user) {

    }

    public List<Book> getAllBorrowedBooks() {
        return null;
    }

    public void changeBookStatus(Book book, String status) {

    }

    public List<Book> getBorrowedBooks(User borrower) {
        return null;
    }

    public void borrowBook(User borrower, Book book, int duration) {

    }
}
