package com.github.trrine.librarysystem.daoimpl;

import com.github.trrine.librarysystem.dao.BookDao;
import com.github.trrine.librarysystem.database.DatabaseManager;
import com.github.trrine.librarysystem.model.Book;
import com.github.trrine.librarysystem.model.BookStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookDaoImpl implements BookDao<Book> {

    public void createBook(Book book) {
        String sql = "INSERT INTO Book (isbn, title, author, publisher, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set parameters of prepared statement and execute
            stmt.setString(1, book.getIsbn());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setString(4, book.getPublisher());
            stmt.setString(5, book.getStatus().name());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error creating book: " + e.getMessage());
        }
    }

    public Book getBookByBookNo(int bookNo) {
        String sql = "SELECT * FROM Book WHERE bookNo=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set bookNo=id and execute prepared statement
            stmt.setInt(1, bookNo);
            ResultSet rs = stmt.executeQuery();

            // if a match is found, return a Book object
            if (rs.next()) {
                return createBookFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving book: " + e.getMessage());
        }

        return null;
    }

    public List<Book> getBooksByISBN(String isbn) {
        return null;
    }

    public List<Book> getBooksByTitle(String title) {
        return null;
    }

    public List<Book> getBooksByAuthor(String author) {
        return null;
    }

    public List<Book> getBooksByStatus(String status) {
        return null;
    }

    public void updateBook(Book book) {
        String sql = "UPDATE Book SET isbn=?, title=?, author=?, publisher=?, status=? WHERE bookNo=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set parameter values and execute statement
            stmt.setString(1, book.getIsbn());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setString(4, book.getPublisher());
            stmt.setString(5, book.getStatus().name());
            stmt.setInt(6, book.getBookNo());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error updating book: " + e.getMessage());
        }

    }

    public void deleteBook(int bookNo) {
        String sql = "DELETE FROM Book WHERE bookNo=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set parameter value and execute prepared statement
            stmt.setInt(1, bookNo);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error deleting book: " + e.getMessage());
        }
    }

    private Book createBookFromResultSet(ResultSet rs) throws SQLException {
        int bookNo = rs.getInt("bookNo");
        String isbn = rs.getString("isbn");
        String title = rs.getString("title");
        String author = rs.getString("author");
        String publisher = rs.getString("publisher");
        String statusStr = rs.getString("status");
        BookStatus status = BookStatus.valueOf(statusStr);
        return new Book(bookNo, isbn, title, author, publisher, status);
    }
}
