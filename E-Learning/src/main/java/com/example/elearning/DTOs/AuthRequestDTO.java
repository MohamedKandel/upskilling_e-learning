package com.example.elearning.DTOs;

import java.time.LocalDateTime;
import java.util.UUID;


//For registration: Designing the DTO without role, every newly registered user will
//have student role by default at the beginning.
public class AuthRequestDTO {


    private String Name;

    private String email;

    private String password;

    public AuthRequestDTO() {
    }

    public AuthRequestDTO(String name, String email, String password) {
        Name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
}
