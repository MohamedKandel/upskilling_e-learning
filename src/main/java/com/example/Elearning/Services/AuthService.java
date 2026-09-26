package com.example.Elearning.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Elearning.DTOs.RegisterRequest;
import com.example.Elearning.DTOs.RegisterResponse;
import com.example.Elearning.Models.Role;
import com.example.Elearning.Models.User;
import com.example.Elearning.Repositories.RoleRepository;
import com.example.Elearning.Repositories.UserRepository;
import com.example.Elearning.Security.JwtService;import com.example.Elearning.DTOs.LoginRequest;
import com.example.Elearning.DTOs.LoginResponse;

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

    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() ->
                        new RuntimeException("Role not found"));

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );
        user.setRole(role);

        User savedUser = userRepository.save(user);

        return new RegisterResponse(
                savedUser.getAccountId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole().getName()
        );
    }


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