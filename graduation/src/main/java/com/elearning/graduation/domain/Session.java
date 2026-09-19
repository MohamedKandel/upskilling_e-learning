package com.elearning.graduation.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "session_id",
            nullable = false,
            updatable = false,
            columnDefinition = "BINARY(16)")
    private UUID sessionId;

    @Column(name = "session_name",
            nullable = false,
            length = 300)
    private String sessionName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crs_id", nullable = false)
    private courses course;

    @Column(name = "duration_minutes", nullable = false)
    private Integer durationMinutes;

    @Column(name = "session_order")
    private Integer sessionOrder;

    @Lob
    @Column(name = "video_file", columnDefinition = "LONGBLOB")
    private byte[] videoFile;

    @Column(name = "video_file_name", length = 255)
    private String videoFileName;

    @Column(name = "video_file_size")
    private Integer videoFileSize;

    @Column(name = "video_uploaded_at")
    private LocalDateTime videoUploadedAt;


    public UUID getSessionId() {
        return sessionId;
    }
    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }
    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public courses getCourse() {
        return course;
    }
    public void setCourse(courses course) {
        this.course = course;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }
    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Integer getSessionOrder() {
        return sessionOrder;
    }
    public void setSessionOrder(Integer sessionOrder) {
        this.sessionOrder = sessionOrder;
    }

    public byte[] getVideoFile() {
        return videoFile;
    }
    public void setVideoFile(byte[] videoFile) {
        this.videoFile = videoFile;
    }

    public String getVideoFileName() {
        return videoFileName;
    }
    public void setVideoFileName(String videoFileName) {
        this.videoFileName = videoFileName;
    }

    public Integer getVideoFileSize() {
        return videoFileSize;
    }
    public void setVideoFileSize(Integer videoFileSize) {
        this.videoFileSize = videoFileSize;
    }

    public LocalDateTime getVideoUploadedAt() {
        return videoUploadedAt;
    }
    public void setVideoUploadedAt(LocalDateTime videoUploadedAt) {
        this.videoUploadedAt = videoUploadedAt;
    }
}