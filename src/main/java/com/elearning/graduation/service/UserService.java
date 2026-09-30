package com.elearning.graduation.service;

import org.springframework.stereotype.Service;

import com.elearning.graduation.domain.Instructor;
import com.elearning.graduation.dtos.Instructor_DTO;
import com.elearning.graduation.mapper.UserMapper;
import com.elearning.graduation.model.GeneralResponse;
import com.elearning.graduation.repository.InstructorRepository;

@Service
public class UserService {

    private final InstructorRepository instructorRepository;
    private final UserMapper userMapper;

    public UserService(InstructorRepository instructorRepository, UserMapper userMapper) {
        this.instructorRepository = instructorRepository;
        this.userMapper = userMapper;
    }

    // Instructor updates his experience years
    public GeneralResponse<Instructor_DTO> updateExperienceYears(
            Instructor authenticatedInstructor, int newExperienceYears) {

        if (newExperienceYears <= 0) {
            return GeneralResponse.badRequest("Experience years must be greater than 0");
        }

        authenticatedInstructor.setExperienceYears(newExperienceYears);

        Instructor updated = instructorRepository.save(authenticatedInstructor);

        Instructor_DTO dto = userMapper.instructorToDTO(updated);

        return GeneralResponse.success(dto, "Experience years updated successfully");
    }
}