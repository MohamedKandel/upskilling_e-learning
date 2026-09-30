package com.elearning.graduation.controller;

import com.elearning.graduation.dtos.User_DTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.graduation.dtos.Auth_Response_DTO;
import com.elearning.graduation.dtos.LoginRequest_DTO;
import com.elearning.graduation.dtos.Register_DTO;
import com.elearning.graduation.model.EResponseStatus;
import com.elearning.graduation.model.GeneralResponse;
import com.elearning.graduation.service.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<GeneralResponse<?>> registerUser(
            @RequestBody Register_DTO request) {

        GeneralResponse<?> response = this.authenticationService.register(request);

        return  ResponseEntity
                .status(response.getStatus().getHttpStatus())
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<GeneralResponse<Auth_Response_DTO>> login(
             @RequestBody LoginRequest_DTO request) {

        GeneralResponse<Auth_Response_DTO> response = authenticationService.login(request);

        return ResponseEntity
                .status(response.getStatus().getHttpStatus())
                .body(response);
    }
}