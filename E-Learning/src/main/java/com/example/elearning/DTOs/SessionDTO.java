package com.example.elearning.DTOs;

import java.time.LocalDateTime;
import java.util.UUID;

public class SessionDTO {

    private UUID sessionId;

    private String statusName; //status name instead of session status

    private UUID courseId;

    private String courseName; //in case needed

    private String sessionTitle;

    private String description;

    private String videoURL;

    private LocalDateTime createdAT;


    public SessionDTO() {
    }

    //Inserting New Session:
    public SessionDTO(String statusName, String courseName, String sessionTitle, String description, String videoURL) {
        this.statusName = statusName;
        this.courseName = courseName;
        this.sessionTitle = sessionTitle;
        this.description = description;
        this.videoURL = videoURL;
    }

    //Displaying data/session info if needed


    public SessionDTO(UUID sessionId, String statusName, String courseName, UUID courseId, String sessionTitle, String description, String videoURL, LocalDateTime createdAT) {
        this.sessionId = sessionId;
        this.statusName = statusName;
        this.courseName = courseName;
        this.courseId = courseId;
        this.sessionTitle = sessionTitle;
        this.description = description;
        this.videoURL = videoURL;
        this.createdAT = createdAT;
    }


    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public LocalDateTime getCreatedAT() {
        return createdAT;
    }

    public void setCreatedAT(LocalDateTime createdAT) {
        this.createdAT = createdAT;
    }
}
