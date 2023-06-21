package com.github.trrine.librarysystem.dao;

import com.github.trrine.librarysystem.model.User;

public interface UserDao<T> {
    void createUser(User user, String password);

    User getUserById(int id);

    void updateUser(User user);

    void deleteUser(int id);

    boolean authenticateUser(String username, String password);
}
