package com.example.Elearning.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterResponse {

    private int accountId;
    private String name;
    private String email;
    private String role;
}