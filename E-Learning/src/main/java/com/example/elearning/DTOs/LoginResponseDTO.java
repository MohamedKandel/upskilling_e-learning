package com.example.elearning.DTOs;


//we need to display user data/ user token
public class LoginResponseDTO {

    UserDTO user;
    String token;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(UserDTO user, String token) {
        this.user = user;
        this.token = token;
    }

    public UserDTO getUser() {
        return this.user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
