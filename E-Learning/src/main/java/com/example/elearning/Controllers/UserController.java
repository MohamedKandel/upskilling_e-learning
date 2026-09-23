package com.example.elearning.Controllers;


import com.example.elearning.Repos.InstructorRepo;
import com.example.elearning.Repos.StudentRepo;
import com.example.elearning.Repos.UserRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

    private InstructorRepo _instructorRepo
    private  UserRepo _userRepo;
    private StudentRepo _studentRepo;

    public UserController(InstructorRepo _instructorRepo, UserRepo _userRepo, StudentRepo _studentRepo) {
        this._instructorRepo = _instructorRepo;
        this._userRepo = _userRepo;
        this._studentRepo = _studentRepo;
    }
    //Instructor adjusting his years of experiences:


    //Instructor Create New Course:




}
