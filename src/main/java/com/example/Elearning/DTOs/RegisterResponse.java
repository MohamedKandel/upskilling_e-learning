package com.example.Elearning.DTOs;

public class RegisterResponse {

    private int accountId;
    private String name;
    private String email;
    private String role;

    public RegisterResponse(int accountId, String name, String email, String role) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}