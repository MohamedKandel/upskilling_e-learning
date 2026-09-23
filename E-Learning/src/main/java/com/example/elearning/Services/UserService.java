package com.example.elearning.Services;

import com.example.elearning.DTOs.InstructorDTO;
import com.example.elearning.Enum.ResponseStatus;
import com.example.elearning.Mappers.UserMapper;
import com.example.elearning.Models.Instructor;
import com.example.elearning.Repos.InstructorRepo;
import com.example.elearning.Repos.StudentRepo;
import com.example.elearning.Repos.UserRepo;
import com.example.elearning.Responses.GeneralResponse;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserMapper _Mapper;
    private InstructorRepo _instructorRepo;
    private UserRepo _userRepo;
    private StudentRepo _studentRepo;

    //Instructor adjusting his years of experiences:


    public UserService(UserMapper _Mapper, InstructorRepo _instructorRepo, UserRepo _userRepo, StudentRepo _studentRepo) {
        this._Mapper = _Mapper;
        this._instructorRepo = _instructorRepo;
        this._userRepo = _userRepo;
        this._studentRepo = _studentRepo;
    }

    //Authenticated user has a token, used in making these requests:
    public GeneralResponse<InstructorDTO> ChangingExperienceYears(Instructor authenticatedInstructor, int updatedExpYears) {
        try {
            if (updatedExpYears > 0) {

                authenticatedInstructor.setExperience_Years(updatedExpYears);
                Instructor updatedInstructor = this._instructorRepo.save(authenticatedInstructor);


                //Mapping date from updatedInstructor to instructorDto
                InstructorDTO instructorDto = this._Mapper.instructorToDTO(updatedInstructor);

                return new GeneralResponse<>(ResponseStatus.OK, "Years of Experience have been updated successfully", instructorDto);
            } else {
                return new GeneralResponse<>(ResponseStatus.BAD_REQUEST, "Insert valid value for the Exp. Years", null);

            }

        } catch (Exception ex) {
            return new GeneralResponse<>(ResponseStatus.INTERNAL_SERVER_ERROR, null);

        }

    }


    //Instructor Create New Course:



}
