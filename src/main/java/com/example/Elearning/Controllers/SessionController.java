
package com.example.Elearning.Controllers;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.Elearning.DTOs.ApiResponse;
import com.example.Elearning.DTOs.SessionRequest;
import com.example.Elearning.Models.Instructor;
import com.example.Elearning.Models.Session;
import com.example.Elearning.Models.User;
import com.example.Elearning.Services.SessionService;

@RestController
@RequestMapping("/api/instructor/courses")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping(
            value = "/sessions",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ApiResponse<Session>> createSession(

            @RequestParam String courseId,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam MultipartFile video,

            Authentication authentication)

            throws IOException {

        User user = (User) authentication.getPrincipal();

        Instructor instructor = (Instructor) user;

        SessionRequest request = new SessionRequest();

        request.setCourseId(java.util.UUID.fromString(courseId));
        request.setTitle(title);
        request.setDescription(description);

        Session data = sessionService.createSession(
                request,
                video.getBytes(),
                instructor
        );

        ApiResponse<Session> response =
                new ApiResponse<>(
                        200,
                        true,
                        "Session created successfully",
                        data
                );

        return ResponseEntity.ok(response);
    }
}

