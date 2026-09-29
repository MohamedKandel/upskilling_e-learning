package com.example.Elearning.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.Elearning.DTOs.ApiResponse;
import com.example.Elearning.DTOs.CourseResponse;
import com.example.Elearning.DTOs.CourseRequest;
import com.example.Elearning.Models.Instructor;
import com.example.Elearning.Models.User;
import com.example.Elearning.Services.CourseService;

@RestController
@RequestMapping("/api/instructor/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponse>> createCourse(
            @RequestBody CourseRequest request,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        Instructor instructor = (Instructor) user;

        CourseResponse data =
                courseService.createCourse(request, instructor);

        ApiResponse<CourseResponse> response =
                new ApiResponse<>(
                        200,
                        true,
                        "Course created successfully",
                        data
                );

        return ResponseEntity.ok(response);
    }
}