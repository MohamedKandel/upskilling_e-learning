package com.example.elearning.Controllers;


import com.example.elearning.Repos.InstructorRepo;
import com.example.elearning.Repos.StudentRepo;
import com.example.elearning.Repos.UserRepo;
import com.example.elearning.Services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {


    UserService _UserService;

    public UserController(UserService _UserService) {
        this._UserService = _UserService;
    }
    //Instructor adjusting his years of experiences:


    //Instructor Create New Course:




}
