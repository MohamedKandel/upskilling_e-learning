package com.elearning.graduation.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class Session_DTO {

    private UUID sessionId;
    private String sessionName;
    private UUID crsId;
    private String crsName;
    private Integer durationMinutes;
    private Integer sessionOrder;
    private String description;
    private String videoUrl;
    private String thumbnailUrl;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session_DTO() {}

    public Session_DTO(UUID sessionId, String sessionName, UUID crsId, String crsName,
                       Integer durationMinutes, Integer sessionOrder,
                       String description, String videoUrl, String thumbnailUrl,
                       String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.crsId = crsId;
        this.crsName = crsName;
        this.durationMinutes = durationMinutes;
        this.sessionOrder = sessionOrder;
        this.description = description;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getSessionId() { return sessionId; }
    public void setSessionId(UUID sessionId) { this.sessionId = sessionId; }

    public String getSessionName() { return sessionName; }
    public void setSessionName(String sessionName) { this.sessionName = sessionName; }

    public UUID getCrsId() { return crsId; }
    public void setCrsId(UUID crsId) { this.crsId = crsId; }

    public String getCrsName() { return crsName; }
    public void setCrsName(String crsName) { this.crsName = crsName; }

    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }

    public Integer getSessionOrder() { return sessionOrder; }
    public void setSessionOrder(Integer sessionOrder) { this.sessionOrder = sessionOrder; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }

    public String getThumbnailUrl() { return thumbnailUrl; }
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}