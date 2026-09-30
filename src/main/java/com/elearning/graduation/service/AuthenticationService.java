package com.elearning.graduation.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elearning.graduation.domain.Instructor;
import com.elearning.graduation.domain.Role;
import com.elearning.graduation.domain.RoleName;
import com.elearning.graduation.domain.Student;
import com.elearning.graduation.domain.User;
import com.elearning.graduation.dtos.Auth_Response_DTO;
import com.elearning.graduation.dtos.Instructor_DTO;
import com.elearning.graduation.dtos.LoginRequest_DTO;
import com.elearning.graduation.dtos.Register_DTO;
import com.elearning.graduation.dtos.Student_DTO;
import com.elearning.graduation.dtos.User_DTO;
import com.elearning.graduation.mapper.UserMapper;
import com.elearning.graduation.model.EResponseStatus;
import com.elearning.graduation.model.GeneralResponse;
import com.elearning.graduation.repository.InstructorRepository;
import com.elearning.graduation.repository.RoleRepository;
import com.elearning.graduation.repository.StudentRepository;
import com.elearning.graduation.repository.UserRepository;
import com.elearning.graduation.security.JwtService;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    public AuthenticationService(UserRepository userRepository,
                                 StudentRepository studentRepository,
                                 InstructorRepository instructorRepository,
                                 RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder,
                                 UserMapper userMapper,
                                 JwtService jwtService) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
    }

    // ============================================
    // Register
    // ============================================
    @Transactional
    public GeneralResponse<User_DTO> register(Register_DTO request) {

        // Check if email exists
        if (userRepository.existsByEmail(request.getEmail())) {
            return GeneralResponse.error(
                    "Failed to register, email is already in use",
                    EResponseStatus.CONFLICT //409
            );
        }
        RoleName roleEnum;
        try {
             roleEnum = RoleName.valueOf(request.getRole().trim().toLowerCase());

        } catch (IllegalArgumentException ex) { //throw an error if the role is not in the enum (handled)
            return GeneralResponse.badRequest("Role is not valid");
        }
        //getting role whatever it's from the enum only
        Role role = roleRepository.findByRoleName(roleEnum)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user ;
        switch (roleEnum) {
            case student ->  {
                Student student = new Student();
                student.setPhone(request.getPhone());
                user = student;
            }

            case instructor -> {
                user = new Instructor();
            }

            case admin -> {
                user = new User();
            }

            default -> throw new IllegalArgumentException("Unsupported role");
        }


        // Build user based on role


        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        user.setActive(true);

        User saved = userRepository.save(user);

        User_DTO dto;
        dto = this.userMapper.toDTO(saved); //generic user mapper method


        return GeneralResponse.created(dto, "User registered successfully");
    }



    // ============================================
    // Login
    // ============================================
    public GeneralResponse<Auth_Response_DTO> login(LoginRequest_DTO request) {

        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isEmpty()) {
            return GeneralResponse.Unauthorized("Invalid email or password");
        }

        User user = userOpt.get();

        if (!Boolean.TRUE.equals(user.getActive())) {
            return GeneralResponse.Unauthorized("Account is deactivated");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return GeneralResponse.Unauthorized("Invalid email or password");
        }

        // Generate token
        String token = jwtService.GeneratedToken(user);

        // Build response
        User_DTO userDto = userMapper.toDTO(user);

        Auth_Response_DTO response = new Auth_Response_DTO(
                userDto,
                token,
                "Bearer",
                86400L       // 24 hours in seconds
        );

        return GeneralResponse.success(response, "Logged in successfully");
    }
}