package com.backend.models;

import com.backend.Auth.CsrfToken.CsrfGenerator;

public class User {

    private int id;

    private String firstName;
    private String surnamePrefix;
    private String surname;

    private String email;
    private String csrfToken;

    private String password;

    public User() {
    }
    public User(String firstName, String surnamePrefix, String surname, String email, String csrfToken) {

        this.id = id;
        this.firstName = firstName;
        this.surnamePrefix = surnamePrefix;
        this.surname = surname;
        this.email = email;
        this.csrfToken = csrfToken;
    }
    public User(int id, String firstName, String surnamePrefix, String surname, String email) {

        this.id = id;
        this.firstName = firstName;
        this.surnamePrefix = surnamePrefix;
        this.surname = surname;
        this.email = email;
        CsrfGenerator generator = new CsrfGenerator();
        this.csrfToken = generator.generate();
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getSurnamePrefix() {
        return surnamePrefix;
    }
    public void setSurnamePrefix(String surnamePrefix) {
        this.surnamePrefix = surnamePrefix;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCsrfToken() {
        return csrfToken;
    }
    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(this.getFirstName());
        if(this.getSurnamePrefix() != null) {
            string.append(" ");
            string.append(this.getSurnamePrefix());
        }
        string.append(" ");
        string.append(this.getSurname());
        string.append("(").append(this.getEmail()).append(")");
        return string.toString();
    }
}