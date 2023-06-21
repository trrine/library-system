package com.github.trrine.librarysystem.repository;

import com.github.trrine.librarysystem.database.DatabaseManager;
import com.github.trrine.librarysystem.model.Book;
import com.github.trrine.librarysystem.model.BookStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDao implements Dao<Book> {

    @Override
    public Optional<Book> get(Object... id) {
        String sql = "SELECT * FROM Book WHERE bookNo=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // set bookNo=id and execute prepared statement
            stmt.setInt(1, (int) id[0]);
            ResultSet rs = stmt.executeQuery();

            // if a match is found, create a Book object
            if (rs.next()) {
                Book book = createBookFromResultSet(rs);
                return Optional.of(book);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving book: " + e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Book";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // retrieve all books
            while (rs.next()) {
                Book book = createBookFromResultSet(rs);
                books.add(book);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving books: " + e.getMessage());
        }
        return books;
    }

    @Override
    public void save(Book book) {
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
            System.err.println("Error saving book: " + e.getMessage());
        }
    }

    @Override
    public void update(Book book, String[] params) {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE Book SET ");
        List<Object> values = new ArrayList<>();

        // build SET clause based on parameters
        for (String param : params) {
            switch (param) {
                case "isbn":
                    sqlBuilder.append("isbn=?, ");
                    values.add(book.getIsbn()); // assumes Book attributes have been updated
                    break;
                case "title":
                    sqlBuilder.append("title=?, ");
                    values.add(book.getTitle());
                    break;
                case "author":
                    sqlBuilder.append("author=?, ");
                    values.add(book.getAuthor());
                    break;
                case "publisher":
                    sqlBuilder.append("publisher=?, ");
                    values.add(book.getPublisher());
                    break;
                case "status":
                    sqlBuilder.append("status=?, ");
                    values.add(book.getStatus());
                    break;
                default:
                    break;
            }
        }

        // remove training comma and space
        sqlBuilder.delete(sqlBuilder.length() - 2, sqlBuilder.length());

        // add WHERE clause
        sqlBuilder.append(" WHERE bookNo=?");
        values.add(book.getBookNo());

        String sql = sqlBuilder.toString();

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // set parameter values
            for (int i = 0; i < values.size(); i++) {
                stmt.setObject(i + 1, values.get(i));
            }

            // execute statement
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error updating book: " + e.getMessage());
        }
    }

    @Override
    public void delete(Book book) {
        String sql = "DELETE FROM Book WHERE bookNo=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // set parameter value and execute prepared statement
            stmt.setInt(1, book.getBookNo());
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
