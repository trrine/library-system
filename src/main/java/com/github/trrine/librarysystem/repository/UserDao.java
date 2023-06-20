package com.github.trrine.librarysystem.repository;

import com.github.trrine.librarysystem.model.User;

import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User> {

    public Optional<User> get(Object... id) {
        return Optional.empty();
    }

    public List<User> getAll() {
        return null;
    }

    public void save(User user) {

    }

    public void update(User user, String[] params) {

    }

    public void delete(User user) {

    }
}
