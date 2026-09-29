package com.example.Elearning.DTOs;

import java.time.LocalDateTime;
import java.util.UUID;

public class CourseResponse {

    private final UUID courseId;
    private final String title;
    private final String description;
    private final String thumbnail;
    private final LocalDateTime createdAt;
    private final String status;
    private final int instructorId;

    public CourseResponse(
            UUID courseId,
            String title,
            String description,
            String thumbnail,
            LocalDateTime createdAt,
            String status,
            int instructorId) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.createdAt = createdAt;
        this.status = status;
        this.instructorId = instructorId;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public int getInstructorId() {
        return instructorId;
    }
}