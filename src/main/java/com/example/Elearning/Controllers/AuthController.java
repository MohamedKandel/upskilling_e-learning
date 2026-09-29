
package com.example.Elearning.Controllers;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.Elearning.DTOs.ApiResponse;
import com.example.Elearning.DTOs.RegisterRequest;
import com.example.Elearning.DTOs.RegisterResponse;
import com.example.Elearning.DTOs.LoginRequest;
import com.example.Elearning.DTOs.LoginResponse;

import com.example.Elearning.Services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(
        value = "/register",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ApiResponse<RegisterResponse>> register(

            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam int roleId,
            @RequestParam(required = false) MultipartFile cv)

            throws IOException {

        RegisterRequest request = new RegisterRequest();

        request.setName(name);
        request.setEmail(email);
        request.setPassword(password);
        request.setRoleId(roleId);

        byte[] cvBytes =
                cv == null || cv.isEmpty()
                        ? null
                        : cv.getBytes();

        RegisterResponse data =
                authService.register(request, cvBytes);

        ApiResponse<RegisterResponse> response =
                new ApiResponse<>(
                        200,
                        true,
                        "Registration successful",
                        data
                );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @RequestBody LoginRequest request) {

        LoginResponse data = authService.login(request);

        ApiResponse<LoginResponse> response =
                new ApiResponse<>(
                        200,
                        true,
                        "Login successful",
                        data
                );

        return ResponseEntity.ok(response);
    }
}

