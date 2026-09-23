package com.example.elearning.Services;


import com.example.elearning.DTOs.*;
import com.example.elearning.Enum.ResponseStatus;
import com.example.elearning.Enum.Role;
import com.example.elearning.Mappers.UserMapper;
import com.example.elearning.Models.Instructor;
import com.example.elearning.Models.Student;
import com.example.elearning.Models.User;
import com.example.elearning.Repos.InstructorRepo;
import com.example.elearning.Repos.StudentRepo;
import com.example.elearning.Repos.UserRepo;
import com.example.elearning.Responses.GeneralResponse;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


//For registration: Designing the DTO without role, every newly registered user will
//have student role by default at the beginning.
@Service
public class AuthenticationService {


    UserRepo _userRepo;
    JavaAuthService _JwtService;
    PasswordEncoder _passwordEncoder;
    UserMapper _UserMapper;
    StudentRepo _StudentRepo;
    InstructorRepo _instructorRepo;

    public AuthenticationService(UserRepo _userRepo, JavaAuthService _JwtService, PasswordEncoder _passwordEncoder, UserMapper _UserMapper, StudentRepo _StudentRepo, InstructorRepo _instructorRepo) {
        this._userRepo = _userRepo;
        this._JwtService = _JwtService;
        this._passwordEncoder = _passwordEncoder;
        this._UserMapper = _UserMapper;
        this._StudentRepo = _StudentRepo;
        this._instructorRepo = _instructorRepo;
    }
//Created this method intentionally so it does not generate a JWT.
    // JWT generation belongs in your login/authentication flow, not in registration.

        @Transactional
        //Registration Service:
        public GeneralResponse<?> RegisterUser(AuthRequestDTO request) {
            try {
                if (request.getRole() == Role.STUDENT) {
                    Optional<Student> existingStudent = this._StudentRepo.findByEmail(request.getEmail());

                    if (existingStudent.isPresent()) {
                        //conflict 409
                        return new GeneralResponse<>(ResponseStatus.CONFLICT, "Failed to register, there's a current user attached to your email", null);
                    } else {
                        //Saving the user to the database
                        Student student = new Student();

                        student.setFullName(request.getName());
                        student.setEmail(request.getEmail());
                        student.setPassword(this._passwordEncoder.encode(request.getPassword()));
                        student.setRole(request.getRole());
                        student.setActive(1);

                        Student savedStudent = this._StudentRepo.save(student);

                        //Displaying new user info using User DTO, I'll convert between User <> UserDTO using the mapper
                        //instead of using set() methods for each attribute
                        StudentDTO studentInfo = this._UserMapper.studentToDTO(savedStudent); //I hid password from the response.

                        return new GeneralResponse<>(ResponseStatus.CREATED, "Student Registered Successfully", studentInfo);


                    }
                } else if (request.getRole() == Role.INSTRUCTOR) {

                    Optional<Instructor> existingInstructor = this._instructorRepo.findByEmail(request.getEmail());

                    if (existingInstructor.isPresent()) {
                        //conflict 409
                        return new GeneralResponse<>(ResponseStatus.CONFLICT, "Failed to register, there's a current user attached to your email", null);
                    } else {
                        //Saving the user to the database
                        Instructor instructor = new Instructor();

                        instructor.setFullName(request.getName());
                        instructor.setEmail(request.getEmail());
                        instructor.setPassword(
                                this._passwordEncoder.encode(request.getPassword())
                        );
                        instructor.setRole(request.getRole());
                        instructor.setActive(1);
                        Instructor savedinstructor = this._instructorRepo.save(instructor);

                        //Displaying new user info using User DTO, I'll convert between User <> UserDTO using the mapper
                        //instead of using set() methods for each attribute
                        InstructorDTO instructorInfo = this._UserMapper.instructorToDTO(savedinstructor); //I hid password from the response.

                        return new GeneralResponse<>(ResponseStatus.CREATED, "Instructor Registered Successfully", instructorInfo);


                    }

                } else {
                    //Role is null
                    return new GeneralResponse<>(
                            ResponseStatus.BAD_REQUEST,
                            "Role is required",
                            null);

                }

            } catch (Exception ex) {
                ex.printStackTrace(); //to log any exception /error
                return new GeneralResponse<>(ResponseStatus.INTERNAL_SERVER_ERROR, null);
            }
        }

    public GeneralResponse<LoginResponseDTO> Login(AuthRequestDTO LoginRequest) {
        try {

            // Checking if this user has an email in the database
            Optional<User> existingUser =
                    this._userRepo.findByEmail(LoginRequest.getEmail());

            if (existingUser.isEmpty()) {

                // No user with this email
                return new GeneralResponse<>(
                        ResponseStatus.UNAUTHORIZED,
                        "Invalid Email or Password",
                        null
                );
            }

            User user = existingUser.get();

            // Checking if the user is active
            // 1 = active
            // 0 = deactivated
            if (user.getActive() != 1) {

                return new GeneralResponse<>(
                        ResponseStatus.UNAUTHORIZED,
                        "Invalid Email or Password",
                        null
                );
            }

            // Verifying the submitted password against
            // the BCrypt hash stored in the database
            if (!_passwordEncoder.matches(
                    LoginRequest.getPassword(),
                    user.getPassword()
            )) {

                return new GeneralResponse<>(
                        ResponseStatus.UNAUTHORIZED,
                        "Invalid Email or Password",
                        null
                );
            }

            // Authentication successful
            // Generate JWT for the authenticated user
            String token = this._JwtService.GeneratedToken(user);

            // Convert User entity to UserDTO
            UserDTO loggedInUser = this._UserMapper.toDTO(user);

            // Create login response
            LoginResponseDTO loginResponse =
                    new LoginResponseDTO(loggedInUser, token);

            return new GeneralResponse<>(
                    ResponseStatus.OK,
                    "User Logged in Successfully!",
                    loginResponse
            );

        } catch (Exception ex) {

            ex.printStackTrace();

            return new GeneralResponse<>(
                    ResponseStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }







}




