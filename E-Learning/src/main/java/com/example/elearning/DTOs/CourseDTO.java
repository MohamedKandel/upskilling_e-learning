package com.example.elearning.DTOs;

import com.example.elearning.Enum.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public class CourseDTO {

    private UUID courseId;
    private String courseName;
    private String description;
    private String thumbnailUrl;

    private UUID instructorId;
    private String instructorName;

    private Status statusName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private int sessionCount; //count of session is better so far


    public CourseDTO() {
    }

    public CourseDTO(String courseName, String description, String thumbnailUrl, String instructorName, Status statusName) {
        this.courseName = courseName;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.instructorName = instructorName;
        this.statusName = statusName;
    }


}