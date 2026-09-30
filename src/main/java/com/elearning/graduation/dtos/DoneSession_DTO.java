package com.elearning.graduation.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class DoneSession_DTO {

    private UUID studentId;
    private UUID sessionId;
    private String sessionName;
    private BigDecimal progress;
    private Boolean isCompleted;
    private LocalDateTime completedAt;
    private LocalDateTime lastUpdated;

    public DoneSession_DTO() {}

    public DoneSession_DTO(UUID studentId, UUID sessionId, String sessionName,
                           BigDecimal progress, Boolean isCompleted,
                           LocalDateTime completedAt, LocalDateTime lastUpdated) {
        this.studentId = studentId;
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.progress = progress;
        this.isCompleted = isCompleted;
        this.completedAt = completedAt;
        this.lastUpdated = lastUpdated;
    }

    public UUID getStudentId() { return studentId; }
    public void setStudentId(UUID studentId) { this.studentId = studentId; }

    public UUID getSessionId() { return sessionId; }
    public void setSessionId(UUID sessionId) { this.sessionId = sessionId; }

    public String getSessionName() { return sessionName; }
    public void setSessionName(String sessionName) { this.sessionName = sessionName; }

    public BigDecimal getProgress() { return progress; }
    public void setProgress(BigDecimal progress) { this.progress = progress; }

    public Boolean getIsCompleted() { return isCompleted; }
    public void setIsCompleted(Boolean isCompleted) { this.isCompleted = isCompleted; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}