package com.example.elearning.DTOs;

import com.example.elearning.Enum.Role;

import java.time.LocalDateTime;
import java.util.UUID;


//For registration: Designing the DTO without role, every newly registered user will
//have student role by default at the beginning.
public class AuthRequestDTO {


    private String fullName;

    private String email;

    private String password;

    private Role role;

    public AuthRequestDTO() {
    }

    public AuthRequestDTO(String fullName, String email, String password, Role role) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public AuthRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return this.fullName;
    }

    public void setName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
