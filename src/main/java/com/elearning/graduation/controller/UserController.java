package com.elearning.graduation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.graduation.domain.Instructor;
import com.elearning.graduation.dtos.Instructor_DTO;
import com.elearning.graduation.model.GeneralResponse;
import com.elearning.graduation.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/experience-years")
    public ResponseEntity<GeneralResponse<Instructor_DTO>> updateExperienceYears(
            @AuthenticationPrincipal Authentication authenticatedUser,
            @RequestParam int experienceYears) {

        Instructor instructor = (Instructor) authenticatedUser.getPrincipal();

        GeneralResponse<Instructor_DTO> response =
                userService.updateExperienceYears(instructor, experienceYears);

        return ResponseEntity
                .status(response.getStatus().getHttpStatus())
                .body(response);
    }
}