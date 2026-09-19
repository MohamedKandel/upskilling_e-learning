package com.example.elearning.Models;


import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="SYS_SESSION")
public class Session {
    @Id
    @Column(name="SESSION_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID sessionId;

//    private String

    @ManyToOne
    @JoinColumn(name="Status_ID")
    private Status sessionStatus;

    @ManyToOne
    @JoinColumn(name="COURSE_ID")
    private Course course;

    @Column(name="SESSION_TITLE")
    private String sessionTitle;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="VIDEO_URL")
    private String videoURL;

    @Column(name="CREATED_AT")
    LocalDateTime createdAT;

    public Session() {
    }

    public Session(UUID sessionId, Status sessionStatus, Course course, String sessionTitle, String videoURL, String description, LocalDateTime createdAT) {
        this.sessionId = sessionId;
        this.sessionStatus = sessionStatus;
        this.course = course;
        this.sessionTitle = sessionTitle;
        this.videoURL = videoURL;
        this.description = description;
        this.createdAT = createdAT;
    }

    @PrePersist
    public void OnCreate()
    {
        this.createdAT= LocalDateTime.now();
    }

    public LocalDateTime getCreatedAT() {
        return createdAT;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public Status getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(Status sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSessionTitle() {
        return sessionTitle;
    }

    public void setSessionTitle(String sessionTitle) {
        this.sessionTitle = sessionTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
}
