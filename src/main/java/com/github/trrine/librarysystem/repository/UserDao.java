package com.github.trrine.librarysystem.repository;

import com.github.trrine.librarysystem.database.DatabaseManager;
import com.github.trrine.librarysystem.model.User;
import com.github.trrine.librarysystem.model.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User> {

    @Override
    public Optional<User> get(Object... id) {
        String sql = "SELECT * FROM User WHERE id=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set parameter value and execute prepared statement
            stmt.setInt(1, (int) id[0]);
            ResultSet rs = stmt.executeQuery();

            // if a match is found, return a User object
            if (rs.next()) {
                User user = createUserFromResultSet(rs);
                return Optional.of(user);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving user: " + e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // retrieve all users
            while (rs.next()) {
                User user = createUserFromResultSet(rs);
                users.add(user);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving users: " + e.getMessage());
        }

        return users;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO User (firstname, surname, phone, email, type)  VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set parameter values and execute prepared statement
            stmt.setString(1, user.getFirstname());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getType().name());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

    @Override
    public void update(User user, String[] params) {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE User SET ");
        List<Object> values = new ArrayList<>();

        // build SET clause based on parameters
        for (String param : params) {
            switch (param) {
                case "firstname":
                    sqlBuilder.append("firstname=?, ");
                    values.add(user.getFirstname()); // assumes User attributes have been updated
                    break;
                case "surname":
                    sqlBuilder.append("surname=?, ");
                    values.add(user.getSurname());
                    break;
                case "phone":
                    sqlBuilder.append("phone=?, ");
                    values.add(user.getPhone());
                    break;
                case "email":
                    sqlBuilder.append("email=?, ");
                    values.add(user.getEmail());
                    break;
                case "type":
                    sqlBuilder.append("type=?, ");
                    values.add(user.getType());
                    break;
                default:
                    break;
            }
        }

        // remove trailing comma and space
        sqlBuilder.delete(sqlBuilder.length() - 2, sqlBuilder.length());

        // add WHERE clause
        sqlBuilder.append(" WHERE id=?");
        values.add(user.getID());

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
            System.err.println("Error updating user: " + e.getMessage());
        }

    }

    @Override
    public void delete(User user) {
        String sql = "DELETE FROM User WHERE=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set parameter value and execute statement
            stmt.setInt(1, user.getID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
        }
    }

    private User createUserFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String firstname = rs.getString("firstname");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String typeStr = rs.getString("type");
        UserType type = UserType.valueOf(typeStr);
        return new User(id, firstname, surname, phone, email, type);
    }
}
