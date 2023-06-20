package com.github.trrine.librarysystem.repository;

import com.github.trrine.librarysystem.database.DatabaseManager;
import com.github.trrine.librarysystem.model.Book;
import com.github.trrine.librarysystem.model.BookStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    public void save(Book book) {

    }

    @Override
    public void update(Book book, String[] params) {

    }

    @Override
    public void delete(Book book) {

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
