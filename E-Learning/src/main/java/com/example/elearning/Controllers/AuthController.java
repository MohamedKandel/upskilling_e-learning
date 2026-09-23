package com.example.elearning.Controllers;

import com.example.elearning.DTOs.AuthRequestDTO;
import com.example.elearning.Responses.GeneralResponse;
import com.example.elearning.Services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService _authenticationService;

    public AuthController(AuthenticationService _authenticationService) {
        this._authenticationService = _authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<GeneralResponse<?>> RegisterUser(
            @RequestBody AuthRequestDTO request) {

        GeneralResponse<?> response =
                this._authenticationService.RegisterUser(request);

        switch (response.getResponse()) {

            case CREATED:
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(response);

            case CONFLICT:
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(response);

            case BAD_REQUEST:
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(response);

            default:
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<GeneralResponse<?>> Login(
            @RequestBody AuthRequestDTO LoginRequest) {

        GeneralResponse<?> response =
                this._authenticationService.Login(LoginRequest);

        switch (response.getResponse()) {

            case OK:
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(response);

            case UNAUTHORIZED:
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(response);

            case BAD_REQUEST:
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(response);

            default:
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(response);
        }
    }
}

