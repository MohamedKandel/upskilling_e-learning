package com.elearning.graduation.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class Course_DTO {

    private UUID crsId;
    private String crsName;
    private String description;
    private UUID instructorId;
    private String instructorName;
    private String status;
    private String thumbnailUrl;
    private Integer sessionCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Course_DTO() {}

    public Course_DTO(UUID crsId, String crsName, String description,
                      UUID instructorId, String instructorName,
                      String status, String thumbnailUrl, Integer sessionCount,
                      LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.crsId = crsId;
        this.crsName = crsName;
        this.description = description;
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.status = status;
        this.thumbnailUrl = thumbnailUrl;
        this.sessionCount = sessionCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getCrsId() { return crsId; }
    public void setCrsId(UUID crsId) { this.crsId = crsId; }

    public String getCrsName() { return crsName; }
    public void setCrsName(String crsName) { this.crsName = crsName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public UUID getInstructorId(UUID accountId) { return instructorId; }
    public void setInstructorId(UUID instructorId) { this.instructorId = instructorId; }

    public String getInstructorName() { return instructorName; }
    public void setInstructorName(String instructorName) { this.instructorName = instructorName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getThumbnailUrl() { return thumbnailUrl; }
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }

    public Integer getSessionCount() { return sessionCount; }
    public void setSessionCount(Integer sessionCount) { this.sessionCount = sessionCount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}