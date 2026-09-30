package com.elearning.graduation.dtos;

public class Auth_Response_DTO {

    private User_DTO user;
    private String token;
    private String tokenType;
    private Long expiresIn;

    public Auth_Response_DTO() {}

    public Auth_Response_DTO(User_DTO user, String token, String tokenType, Long expiresIn) {
        this.user = user;
        this.token = token;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
    }

    public User_DTO getUser() { return user; }
    public void setUser(User_DTO user) { this.user = user; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getTokenType() { return tokenType; }
    public void setTokenType(String tokenType) { this.tokenType = tokenType; }

    public Long getExpiresIn() { return expiresIn; }
    public void setExpiresIn(Long expiresIn) { this.expiresIn = expiresIn; }
}