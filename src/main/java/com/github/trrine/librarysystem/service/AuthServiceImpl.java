package com.github.trrine.librarysystem.service;

import com.github.trrine.librarysystem.dao.UserDaoImpl;
import com.github.trrine.librarysystem.model.User;

public class AuthServiceImpl implements AuthService {
    private UserDaoImpl userDao;

    public AuthServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public User authenticateUser(String username, String password) {
        return this.userDao.authenticateUser(username, password);
    }

    public RegistrationStatus registerUser(User user, String password) {
        return null; // TO DO
    }
}
