package com.github.trrine.librarysystem.model;

public class User {
    private String userID;
    private String firstname;
    private String surname;
    private String phone;
    private String email;
    private UserType type;

    public User(String userID, String firstname, String surname, String phone, String email, UserType type) {
        this.userID = userID;
        this.firstname = firstname;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.type = type;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getEmail() {
        return this.email;
    }

    public UserType getType() {
        return this.type;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
