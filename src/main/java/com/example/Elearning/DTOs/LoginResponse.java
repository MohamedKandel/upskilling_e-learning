package com.example.Elearning.DTOs;

public class LoginResponse {

    private int accountId;
    private String name;
    private String email;
    private String role;
    private String token;

    public LoginResponse(
            int accountId,
            String name,
            String email,
            String role,
            String token
    ) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.token = token;
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

    public String getToken() {
        return token;
    }
}