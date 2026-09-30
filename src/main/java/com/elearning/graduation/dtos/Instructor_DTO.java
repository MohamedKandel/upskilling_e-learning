package com.elearning.graduation.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class Instructor_DTO {

    private UUID accountId;
    private String name;
    private String email;
    private String bio;
    private Integer experienceYears;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Instructor_DTO() {}

    public Instructor_DTO(UUID accountId, String name, String email,
                          String bio, Integer experienceYears, Boolean active,
                          LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.experienceYears = experienceYears;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getAccountId() { return accountId; }
    public void setAccountId(UUID accountId) { this.accountId = accountId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public Integer getExperienceYears() { return experienceYears; }
    public void setExperienceYears(Integer experienceYears) { this.experienceYears = experienceYears; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}