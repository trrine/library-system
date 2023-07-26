package com.github.trrine.librarysystem.service;

import com.github.trrine.librarysystem.dao.BookDaoImpl;
import com.github.trrine.librarysystem.model.Book;
import com.github.trrine.librarysystem.model.BookStatus;

import java.util.List;

public class LibrarianServiceImpl implements LibrarianService {
    private BookDaoImpl bookDao;

    public LibrarianServiceImpl(BookDaoImpl bookDao) {
        this.bookDao = bookDao;
    }

    public BookInsertionStatus insertBook(Book book) {
        if (this.bookDao.getBookByBookIsbn(book.getIsbn()) != null) {
            return BookInsertionStatus.ISBN_EXISTS;
        }

        this.bookDao.createBook(book);
        return BookInsertionStatus.SUCCESS;
    }

    public List<Book> listAllBooks() {
        return this.bookDao.searchBooks("", "", "", "");
    }

    public List<Book> searchBooks(String isbn, String title, String author, String status) {
        return this.bookDao.searchBooks(isbn, title, author, status);
    }

    public List<Book> listAvailableBooks() {
        return this.bookDao.searchBooks("", "", "", BookStatus.AVAILABLE.name());
    }

    public BookChangeStatus changeBookStatus(int bookNo, String newStatus) {
        Book book = this.bookDao.getBookByBookNo(bookNo);

        if (book == null) {
            return BookChangeStatus.BOOK_NOT_FOUND;
        }

        if (book.getStatus().name().equals(BookStatus.AVAILABLE.name())) {
            book.setStatus(BookStatus.BORROWED);

        } else {
            book.setStatus(BookStatus.AVAILABLE);
        }

        this.bookDao.updateBook(book);
        return BookChangeStatus.SUCCESS;
    }

    // note: consider what happens to records in Borrowing table
    public BookChangeStatus removeBook(int bookNo) {
        if (this.bookDao.getBookByBookNo(bookNo) == null) {
            return BookChangeStatus.BOOK_NOT_FOUND;
        }

        this.bookDao.deleteBook(bookNo);
        return BookChangeStatus.SUCCESS;
    }
}
