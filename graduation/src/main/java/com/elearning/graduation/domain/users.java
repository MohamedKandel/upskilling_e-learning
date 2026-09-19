package com.elearning.graduation.domain;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id",
            nullable = false,
            unique = true,
            updatable = false,
            columnDefinition = "BINARY(16)")
    private UUID accountId;

    @Column(name = "name",
            nullable = false,
            length = 250)
    private String name;

    @Column(name = "email",
            nullable = false,
            length = 300,
            unique = true)
    private String email;

    @Column(name = "password",
            nullable = false,
            length = 250)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role",
            nullable = false,
            length = 50)
    private String role;

    public UUID getAccountId() {
        return accountId;
    }
    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

}
