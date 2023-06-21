package com.github.trrine.librarysystem.repository;

import com.github.trrine.librarysystem.database.DatabaseManager;
import com.github.trrine.librarysystem.model.Loan;
import com.github.trrine.librarysystem.model.LoanStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoanDao implements Dao<Loan> {

    public Optional<Loan> get(Object... id) {
        // extract the composite primary key elements
        int userId = (int) id[0];
        int bookNo = (int) id[1];
        LocalDate startDate = (LocalDate) id[2];

        String sql = "SELECT * FROM Loan WHERE userID=? AND bookNo=? AND startDate=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set parameter values and execute statement
            stmt.setInt(1, userId);
            stmt.setInt(2, bookNo);
            stmt.setString(3, startDate.toString());
            ResultSet rs = stmt.executeQuery();

            // if a match is found, return a Loan object
            if (rs.next()) {
                Loan loan = createLoanFromResultSet(rs);
                return Optional.of(loan);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving loan: " + e.getMessage());
        }

        return Optional.empty();
    }

    public List<Loan> getAll() {
        List<Loan> loans = new ArrayList<>();
        String sql = "SELECT * FROM Loan";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // retrieve all loans
            while (rs.next()) {
                Loan loan = createLoanFromResultSet(rs);
                loans.add(loan);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving loans: " + e.getMessage());
        }

        return loans;
    }

    public void save(Loan loan) {
        String sql = "INSERT INTO Loan (userID, bookNo, startDate, dueDate, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set parameter values and execute statement
            stmt.setInt(1, loan.getUserID());
            stmt.setInt(2, loan.getBookNo());
            stmt.setString(3, loan.getStartDate().toString());
            stmt.setString(4, loan.getDueDate().toString());
            stmt.setString(5, loan.getStatus().name());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error saving loan: " + e.getMessage());
        }
    }

    public void update(Loan loan, String[] params) {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE Loan SET ");
        List<Object> values = new ArrayList<>();

        // build SET clause based on parameters
        for (String param : params) {
            switch (param) {
                case "userID":
                    sqlBuilder.append("userID=?, ");
                    values.add(loan.getUserID()); // assumes Loan attributes have been updated
                    break;
                case "bookNo":
                    sqlBuilder.append("bookNo=?, ");
                    values.add(loan.getBookNo());
                    break;
                case "startDate":
                    sqlBuilder.append("startDate=?, ");
                    values.add(loan.getStartDate().toString());
                    break;
                case "dueDate":
                    sqlBuilder.append("dueDate=?, ");
                    values.add(loan.getDueDate().toString());
                    break;
                case "status":
                    sqlBuilder.append("status=?, ");
                    values.add(loan.getStatus().name());
                    break;
                default:
                    break;
            }
        }

        // remove trailing comma and space
        sqlBuilder.delete(sqlBuilder.length() - 2, sqlBuilder.length());

        // add WHERE clause
        sqlBuilder.append(" WHERE userID=? AND bookNo=? AND startDate=?");
        values.add(loan.getUserID());
        values.add(loan.getBookNo());
        values.add(loan.getStartDate().toString());

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
            System.err.println("Error updating loan: " + e.getMessage());
        }
    }

    public void delete(Loan loan) {
        String sql = "DELETE FROM Loan WHERE userID=? AND bookNo=? AND startDate=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set parameter values and execute statement
            stmt.setInt(1, loan.getUserID());
            stmt.setInt(2, loan.getBookNo());
            stmt.setString(3, loan.getStartDate().toString());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error deleting loan: " + e.getMessage());
        }
    }

    private Loan createLoanFromResultSet(ResultSet rs) throws SQLException {
        int userID = rs.getInt("userID");
        int bookNo = rs.getInt("bookNo");
        LocalDate startDate = rs.getDate("startDate").toLocalDate();
        LocalDate dueDate = rs.getDate("dueDate").toLocalDate();
        String statusStr = rs.getString("status");
        LoanStatus status = LoanStatus.valueOf(statusStr);
        return new Loan(userID, bookNo, startDate, dueDate, status);
    }
}
