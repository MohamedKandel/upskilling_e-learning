package com.example.Elearning.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Elearning.DTOs.ApiResponse;
import com.example.Elearning.DTOs.RegisterRequest;
import com.example.Elearning.DTOs.RegisterResponse;
import com.example.Elearning.Services.AuthService;
import com.example.Elearning.DTOs.LoginRequest;
import com.example.Elearning.DTOs.LoginResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(
            @RequestBody RegisterRequest request) {

        RegisterResponse data = authService.register(request);

        ApiResponse<RegisterResponse> response = new ApiResponse<>(
                200,
                true,
                "Registration successful",
                data);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @RequestBody LoginRequest request) {

        LoginResponse data = authService.login(request);

        ApiResponse<LoginResponse> response = new ApiResponse<>(
                200,
                true,
                "Login successful",
                data);

        return ResponseEntity.ok(response);
    }
}