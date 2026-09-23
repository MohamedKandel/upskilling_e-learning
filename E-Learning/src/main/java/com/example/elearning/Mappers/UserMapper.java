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
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")

public interface  UserMapper {

    //the only thing I gonna set, Using "setMethods"

    //Mapping User
    @Mapping(source="role", target="roleName") //role enum attribute
    @Mapping(target = "password", ignore = true) //Hiding password from the response
    public UserDTO toDTO(User user);

    //Mapping Student
    @Mapping(source = "role", target = "roleName")
    @Mapping(target = "password", ignore = true)
    StudentDTO studentToDTO(Student student);

    //Mapping Instructor
    @Mapping(source = "role", target = "roleName")
    @Mapping(target = "password", ignore = true)
    InstructorDTO instructorToDTO(Instructor instructor);


    //MapStruct won't try to create those relationships automatically:
    //From DTO to USER: target is out User instance
    //ignoring what we're gonna set and cannot just set usin mapper but with set methods in user model
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "course", ignore = true) //ignoring the course taught by the instructors
    @Mapping(target = "courses", ignore = true) //ignoring the number of courses students enrolled in

    //Creating new User instance with userDTO info
    public User newUser(UserDTO userDto);


    // UserDto UPDATE EXISTING User data
    public void UpdateUserEntity(UserDTO userDto, @MappingTarget User user);

    // StudentDto UPDATE EXISTING Student data
    public void UpdateStudentEntity(StudentDTO StudentDto, @MappingTarget Student student);

    // InstructorDto UPDATE EXISTING Student data
    public void UpdateInstructorEntity(InstructorDTO instructorDto, @MappingTarget Instructor instructor);

}
