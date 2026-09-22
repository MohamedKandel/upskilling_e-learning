package com.example.elearning.Services;


import com.example.elearning.DTOs.AuthRequestDTO;
import com.example.elearning.DTOs.LoginResponseDTO;
import com.example.elearning.DTOs.UserDTO;
import com.example.elearning.Enum.ResponseStatus;
import com.example.elearning.Mappers.UserMapper;
import com.example.elearning.Models.Role;
import com.example.elearning.Models.User;
import com.example.elearning.Repos.RoleRepo;
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

    RoleRepo _roleRepo;
    public AuthenticationService(RoleRepo _roleRepo , UserMapper _UserMapper, UserRepo _userRepo, JavaAuthService _JwtService, PasswordEncoder _passwordEncoder) {
        this._userRepo = _userRepo;
        this._JwtService = _JwtService;
        this._passwordEncoder = _passwordEncoder;
        this._UserMapper = _UserMapper;
        this._roleRepo = _roleRepo;
    }


    //Created this method intentionally so it does not generate a JWT.
    // JWT generation belongs in your login/authentication flow, not in registration.

    @Transactional
    //Registration Service:
    public GeneralResponse<UserDTO> RegisterUser(AuthRequestDTO request)
    {
        try
        {
            Optional<User> existingUser = this._userRepo.findByEmail(request.getEmail());
            if(existingUser.isPresent())
            {
                //conflict 409
               return new GeneralResponse<>(ResponseStatus.CONFLICT, "Failed to register, there's a current user attached to your email", null);
            }
            else
            {
                //Saving the user to the database
                User user = new User();
                //checking if Student role is in the data base first then, setting role by default to: Student
                Role role = this._roleRepo.findRoleByRoleName("STUDENT");
                if(role != null)
                {
                    user.setRole(role);
                    user.setEmail(request.getEmail());
                    user.setPassword(this._passwordEncoder.encode(request.getPassword())); //Encoding the password with Bcrypt

                    User savedUser = this._userRepo.save(user);

                    //Displaying new user info using User DTO, I'll convert between User <> UserDTO using the mapper
                    //instead of using set() methods for each attribute
                    UserDTO User = this._UserMapper.toDTO(savedUser); //I hid password from the response.

                    return new GeneralResponse<>(ResponseStatus.CREATED, "User Registered Successfully", User);

                }
                else
                {
                    //Cannot find the role: Student in db, sth went wrong/Resource not found:

                    return new GeneralResponse<>(
                            ResponseStatus.INTERNAL_SERVER_ERROR,
                            "Default STUDENT role is not found",
                            null
                    );

                }

            }

        }
        catch(Exception ex) {
            ex.printStackTrace(); //to log any exception /error
            return new GeneralResponse<>(ResponseStatus.INTERNAL_SERVER_ERROR, null);

    }

    }

    //Public Service:
    public GeneralResponse<LoginResponseDTO> Login(User user)
    {
        try
        {  //checking if this user has an email in the db:
            Optional<User> existingUser = this._userRepo.findByEmail(user.getEmail());
            if (existingUser.isPresent()) {

                //verifying the submitted password against the BCrypt hash stored in the database
                if(_passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) //if both match
                {

                    //if password match db hashed pass, generate jwt for the user

                    String token = this._JwtService.GeneratedToken(existingUser.get());

                    //Converting with mapper between user and user DTO:
                    UserDTO loggedInUser = this._UserMapper.toDTO(existingUser.get());


                    //Displaying suitable login response
                    LoginResponseDTO LoginResponse = new LoginResponseDTO(loggedInUser, token);
                    return new GeneralResponse<>(ResponseStatus.OK, "User Logged in Successfully!", LoginResponse); //OK:200 - Correct credentials + JWT


                }

                else
                {
                    //Entered password by the user who's trying to login is incorrect:

                    return new GeneralResponse<>(ResponseStatus.UNAUTHORIZED, "Invalid Email or Password", null); //401: user does not exit or credentials are invalid


                }

            } else {
                //no email:
                return new GeneralResponse<>(ResponseStatus.UNAUTHORIZED, "Invalid Email or Password", null); //401: user does not exit or credentials are invalid
            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return new GeneralResponse<>(ResponseStatus.INTERNAL_SERVER_ERROR, null);
        }

    }



}
