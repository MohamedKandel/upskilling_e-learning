package com.example.elearning.Controllers;


import com.example.elearning.DTOs.InstructorDTO;
import com.example.elearning.Enum.ResponseStatus;
import com.example.elearning.Models.Instructor;
import com.example.elearning.Repos.InstructorRepo;
import com.example.elearning.Repos.StudentRepo;
import com.example.elearning.Repos.UserRepo;
import com.example.elearning.Responses.GeneralResponse;
import com.example.elearning.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.awt.geom.GeneralPath;

@RestController
@RequestMapping("api/users")
public class UserController {

    UserService _UserService;

    public UserController(UserService _UserService) {
        this._UserService = _UserService;
    }
    //Instructor adjusting his years of experiences: testing; idea of authenticated user

    @PostMapping("/experience-years")
    public ResponseEntity<GeneralResponse<InstructorDTO>> ChangingExperienceYears(
            Authentication authentication,
            @RequestParam int updatedExpYears) {

        Instructor instructor = (Instructor) authentication.getPrincipal();

        GeneralResponse<InstructorDTO> Response =
                this._UserService.ChangingExperienceYears(
                        instructor,
                        updatedExpYears
                );

        switch (Response.getResponse()) {

            case OK:
                return ResponseEntity.status(HttpStatus.OK).body(Response);

            case BAD_REQUEST:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response);

            default:
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Response);
        }
    }

    //Instructor Creates New Course:


}






