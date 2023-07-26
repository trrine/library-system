package com.github.trrine.librarysystem.service;

import com.github.trrine.librarysystem.model.User;

public interface AuthService {
    User authenticateUser(String username, String password);
    
}
