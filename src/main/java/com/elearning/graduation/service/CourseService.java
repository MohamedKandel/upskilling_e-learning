package com.elearning.graduation.service;

import com.elearning.graduation.domain.Course;
import com.elearning.graduation.domain.Instructor;
import com.elearning.graduation.domain.User;
import com.elearning.graduation.dtos.CourseInput_DTO;
import com.elearning.graduation.dtos.Course_DTO;
import com.elearning.graduation.mapper.CourseMapper;
import com.elearning.graduation.model.GeneralResponse;
import com.elearning.graduation.repository.CourseRepository;
import com.elearning.graduation.repository.InstructorRepository;
import com.elearning.graduation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final InstructorRepository _instructorRepo;
    private final UserRepository _userRepo;
    private final CourseMapper _courseMapper;
    private final CourseRepository _courseRepo;



    //New course added by certain instructor
    @Transactional
    public synchronized GeneralResponse<Course_DTO> addNewCourse(Instructor instructor, CourseInput_DTO course)
    {
        try
        {
            Boolean InstructorExists = this._userRepo.existsByEmail(instructor.getEmail());

            //authenticated instructor in the db?
            if(InstructorExists) //true
            {

                Optional<User> existingInstructor = this._userRepo.findByEmail(instructor.getEmail());

                User Found = existingInstructor.get();

                Course crs = new Course();

                //make sure that the logged in instructor is the one created that course:
                course.getInstructorId();



                //save course that's updated:
                try
                {
                    //concurrency issue
                    this._courseRepo.saveAndFlush()
                }
                catch()
                {

                }



            } else
            {



            }
        }
        catch(Exception ex)
        {



        }

    }


}
