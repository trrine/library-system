package com.github.trrine.librarysystem.repository;

import com.github.trrine.librarysystem.model.Loan;

import java.util.List;
import java.util.Optional;

public class LoanDao implements Dao<Loan> {

    public Optional<Loan> get(Object... id) {
        return Optional.empty();
    }

    public List<Loan> getAll() {
        return null;
    }

    public void save(Loan loan) {

    }

    public void update(Loan loan, String[] params) {

    }

    public void delete(Loan loan) {

    }
}
