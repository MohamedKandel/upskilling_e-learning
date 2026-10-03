package com.example.Elearning.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private int accountId;
    private String name;
    private String email;
    private String role;
    private String token;
}