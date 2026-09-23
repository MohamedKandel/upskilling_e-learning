package com.example.elearning.Mappers;

import com.example.elearning.DTOs.InstructorDTO;
import com.example.elearning.DTOs.StudentDTO;
import com.example.elearning.DTOs.UserDTO;
import com.example.elearning.Models.Instructor;
import com.example.elearning.Models.Student;
import com.example.elearning.Models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {


    // Mapping User -> UserDTO


    @Mapping(source = "fullName", target = "name")
    @Mapping(source = "role", target = "roleName")
    @Mapping(target = "password", ignore = true)

    // These are DTO fields.
    // They will be handled separately using set methods.
    @Mapping(target = "courseTaughtIds", ignore = true)
    @Mapping(target = "coursesStudentEnrolledIn", ignore = true)

    public UserDTO toDTO(User user);



    // Mapping Student -> StudentDTO


    @Mapping(source = "fullName", target = "name")
    @Mapping(source = "role", target = "roleName")
    @Mapping(target = "password", ignore = true)

    // These are DTO fields.
    // Student.enrolledCourses is not mapped automatically to UUIDs.
    @Mapping(target = "courseTaughtIds", ignore = true)
    @Mapping(target = "coursesStudentEnrolledIn", ignore = true)

    StudentDTO studentToDTO(Student student);



    // Mapping Instructor -> InstructorDTO


    @Mapping(source = "fullName", target = "name")
    @Mapping(source = "role", target = "roleName")
    @Mapping(target = "password", ignore = true)

    // These are DTO fields.
    // Instructor.course is not mapped automatically to UUIDs.
    @Mapping(target = "courseTaughtIds", ignore = true)
    @Mapping(target = "coursesStudentEnrolledIn", ignore = true)

    InstructorDTO instructorToDTO(Instructor instructor);


    // Creating new User from UserDTO


    @Mapping(source = "name", target = "fullName")
    @Mapping(source = "roleName", target = "role")

    // Password is handled by AuthenticationService
    @Mapping(target = "password", ignore = true)

    // Active is handled by the service
    @Mapping(target = "active", ignore = true)

    public User newUser(UserDTO userDto);



    // UserDTO -> existing User


    @Mapping(source = "name", target = "fullName")
    @Mapping(source = "roleName", target = "role")

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "active", ignore = true)

    public void UpdateUserEntity(
            UserDTO userDto,
            @MappingTarget User user
    );



    // StudentDTO -> existing Student


    @Mapping(source = "name", target = "fullName")
    @Mapping(source = "roleName", target = "role")
    @Mapping(source = "phoneNumber", target = "phoneNumber")

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "active", ignore = true)

    // Student.enrolledCourses is managed separately
    @Mapping(target = "enrolledCourses", ignore = true)

    public void UpdateStudentEntity(
            StudentDTO StudentDto,
            @MappingTarget Student student
    );



    // InstructorDTO -> existing Instructor

    @Mapping(source = "name", target = "fullName")
    @Mapping(source = "roleName", target = "role")

    // IMPORTANT:
    // Your JavaBean property is experience_Years
    @Mapping(source = "experience_Years", target = "experience_Years")

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "active", ignore = true)

    // Instructor.course is managed separately
    @Mapping(target = "course", ignore = true)

    public void UpdateInstructorEntity(
            InstructorDTO instructorDto,
            @MappingTarget Instructor instructor
    );

}
