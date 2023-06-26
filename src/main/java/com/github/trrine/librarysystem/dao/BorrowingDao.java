package com.github.trrine.librarysystem.dao;

import com.github.trrine.librarysystem.model.Borrowing;

import java.util.List;

public interface BorrowingDao<T> {
    void createBorrowing(Borrowing borrowing);

    List<Borrowing> getBorrowingsByUserId(int userId);

    List<Borrowing> getBorrowingsByStatus(String status);

    void updateBorrowing(Borrowing borrowing);

    void deleteBorrowing(Borrowing borrowing);
}
