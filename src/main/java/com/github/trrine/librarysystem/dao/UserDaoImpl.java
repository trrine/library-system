package com.github.trrine.librarysystem.dao;

import com.github.trrine.librarysystem.database.DatabaseManager;
import com.github.trrine.librarysystem.model.User;
import com.github.trrine.librarysystem.model.UserType;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    public void createUser(User user, String password) {
        String sql = "INSERT INTO User (userID, password_hash, firstname, surname, phone, email, type)  VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // generate a salt for password hashing
            String salt = BCrypt.gensalt();

            // hash the password using the generated salt
            String hashedPassword = BCrypt.hashpw(password, salt);

            // set parameter values and execute prepared statement
            stmt.setString(1, user.getUserID());
            stmt.setString(2, hashedPassword);
            stmt.setString(3, user.getFirstname());
            stmt.setString(4, user.getSurname());
            stmt.setString(5, user.getPhone());
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getType().name());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error creating user: " + e.getMessage());
        }
    }

    @Override
    public User getUserByUserID(String userID) {
        String sql = "SELECT * FROM User WHERE userID=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set parameter value and execute prepared statement
            stmt.setString(1, userID);
            ResultSet rs = stmt.executeQuery();

            // if a match is found, return a User object
            if (rs.next()) {
                return createUserFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving user: " + e.getMessage());
        }

        return null;
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE User SET firstname=?, surname=?, phone=?, email=? WHERE userID=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set parameter values and execute statement
            stmt.setString(1, user.getFirstname());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getUserID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(String userID) {
        String sql = "DELETE FROM User WHERE userID=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set parameter value and execute statement
            stmt.setString(1, userID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
        }
    }

    @Override
    public boolean authenticateUser(String userID, String password) {
        String sql = "SELECT password_hash FROM users WHERE userID=?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password_hash");
                return BCrypt.checkpw(password, hashedPassword);
            }
        } catch (SQLException e) {
            System.err.println("Error authenticating user: " + e.getMessage());
        }
        return false;
    }

    private User createUserFromResultSet(ResultSet rs) throws SQLException {
        String userID = rs.getString("userID");
        String firstname = rs.getString("firstname");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String typeStr = rs.getString("type");
        UserType type = UserType.valueOf(typeStr);
        return new User(userID, firstname, surname, phone, email, type);
    }
}
