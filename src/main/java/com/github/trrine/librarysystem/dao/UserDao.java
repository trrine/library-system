package com.github.trrine.librarysystem.dao;

import com.github.trrine.librarysystem.model.User;

public interface UserDao {
    void createUser(User user, String password);

    User getUserByUserID(String userID);

    void updateUser(User user);

    void deleteUser(String userID);

    User authenticateUser(String userID, String password);
}
