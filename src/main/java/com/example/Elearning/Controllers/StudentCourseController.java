
package com.example.Elearning.Controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Elearning.DTOs.ApiResponse;
import com.example.Elearning.DTOs.CourseResponse;
import com.example.Elearning.Services.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/student/courses")
@RequiredArgsConstructor
public class StudentCourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CourseResponse>>> getAllCourses(
             @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

    Pageable pageable = PageRequest.of(page, size);

    Page<CourseResponse> data =
            courseService.getAllCourses(pageable);

        ApiResponse<Page<CourseResponse>> response =
                new ApiResponse<>(
                        200,
                        true,
                        "Courses retrieved successfully",
                        data
                );

        return ResponseEntity.ok(response);
    }
}

