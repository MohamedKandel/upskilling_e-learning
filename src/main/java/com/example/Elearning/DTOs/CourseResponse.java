package com.example.Elearning.DTOs;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CourseResponse {

    private final UUID courseId;
    private final String title;
    private final String description;
    private final String thumbnail;
    private final LocalDateTime createdAt;
    private final String status;
    private final int instructorId;
}