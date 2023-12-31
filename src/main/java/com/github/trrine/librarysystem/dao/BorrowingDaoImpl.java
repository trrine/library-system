package com.github.trrine.librarysystem.dao;

import com.github.trrine.librarysystem.database.DatabaseManager;
import com.github.trrine.librarysystem.model.Borrowing;
import com.github.trrine.librarysystem.model.BorrowingStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowingDaoImpl implements BorrowingDao {

    public void createBorrowing(Borrowing borrowing) {
        String sql = "INSERT INTO Borrowing (userID, bookNo, startDate, dueDate, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set parameter values and execute statement
            stmt.setString(1, borrowing.getUserID());
            stmt.setInt(2, borrowing.getBookNo());
            stmt.setString(3, borrowing.getStartDate().toString());
            stmt.setString(4, borrowing.getDueDate().toString());
            stmt.setString(5, borrowing.getStatus().name());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error saving borrowing: " + e.getMessage());
        }
    }

    public List<Borrowing> getBorrowingsByUserID(String userID) {
        List<Borrowing> borrowings = new ArrayList<>();
        String sql = "SELECT * FROM BORROWING WHERE userID=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set parameter value and execute statement
            stmt.setString(1, userID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                borrowings.add(createBorrowingFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving borrowings: " + e.getMessage());
        }
        return borrowings;
    }

    public List<Borrowing> getBorrowingsByStatus(String status) {
        List<Borrowing> borrowings = new ArrayList<>();
        String sql = "SELECT * FROM BORROWING WHERE status=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set parameter value and execute statement
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                borrowings.add(createBorrowingFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving borrowings: " + e.getMessage());
        }
        return borrowings;
    }

    public void updateBorrowing(Borrowing borrowing) {
        String sql = "UPDATE Borrowing SET dueDate=?, status=? WHERE userID=? AND bookNo=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set parameter values and execute statement
            stmt.setString(1, borrowing.getDueDate().toString());
            stmt.setString(2, borrowing.getStatus().name());
            stmt.setString(3, borrowing.getUserID());
            stmt.setInt(4, borrowing.getBookNo());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error updating borrowing: " + e.getMessage());
        }
    }

    public void deleteBorrowing(Borrowing borrowing) {
        String sql = "DELETE FROM Borrowing WHERE userID=? AND bookNo=? AND startDate=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set parameter values and execute statement
            stmt.setString(1, borrowing.getUserID());
            stmt.setInt(2, borrowing.getBookNo());
            stmt.setString(3, borrowing.getStartDate().toString());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error deleting loan: " + e.getMessage());
        }
    }

    private Borrowing createBorrowingFromResultSet(ResultSet rs) throws SQLException {
        String userID = rs.getString("userID");
        int bookNo = rs.getInt("bookNo");
        LocalDate startDate = rs.getDate("startDate").toLocalDate();
        LocalDate dueDate = rs.getDate("dueDate").toLocalDate();
        String statusStr = rs.getString("status");
        BorrowingStatus status = BorrowingStatus.valueOf(statusStr);
        return new Borrowing(userID, bookNo, startDate, dueDate, status);
    }
}
