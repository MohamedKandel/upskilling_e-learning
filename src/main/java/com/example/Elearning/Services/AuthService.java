package com.example.Elearning.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Elearning.DTOs.RegisterRequest;
import com.example.Elearning.DTOs.RegisterResponse;
import com.example.Elearning.DTOs.LoginRequest;
import com.example.Elearning.DTOs.LoginResponse;

import com.example.Elearning.Models.Instructor;
import com.example.Elearning.Models.Role;
import com.example.Elearning.Models.User;

import com.example.Elearning.Repositories.RoleRepository;
import com.example.Elearning.Repositories.UserRepository;

import com.example.Elearning.Security.JwtService;
import org.springframework.transaction.annotation.Transactional;
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // ================= REGISTER =================
    @Transactional 
    public RegisterResponse register(RegisterRequest request) {
        return register(request, null);
    }

    public RegisterResponse register(RegisterRequest request, byte[] cv) {

        // Check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // Find role
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() ->
                        new RuntimeException("Role not found"));

        boolean instructorRole =
                "INSTRUCTOR".equalsIgnoreCase(role.getName().trim());

        // CV is required for instructors
        if (instructorRole && (cv == null || cv.length == 0)) {
            throw new RuntimeException(
                    "CV is required for instructor registration");
        }

        // CV is only allowed for instructors
        if (!instructorRole && cv != null && cv.length > 0) {
            throw new RuntimeException(
                    "CV upload is only supported for instructor registrations");
        }

        // Create user based on role
        User user;

        if (instructorRole) {
            Instructor instructor = new Instructor();
            instructor.setCv(cv);
            user = instructor;
        } else {
            user = new User();
        }

        // Set common fields
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );
        user.setRole(role);

        // Save user
        User savedUser = userRepository.save(user);

        return new RegisterResponse(
                savedUser.getAccountId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole().getName()
        );
    }

    // ================= LOGIN =================

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(
                user.getAccountId(),
                user.getName(),
                user.getEmail(),
                user.getRole().getName(),
                token
        );
    }
}