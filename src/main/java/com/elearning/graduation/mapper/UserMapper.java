package com.elearning.graduation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.elearning.graduation.domain.Instructor;
import com.elearning.graduation.domain.Student;
import com.elearning.graduation.domain.User;
import com.elearning.graduation.dtos.Instructor_DTO;
import com.elearning.graduation.dtos.Student_DTO;
import com.elearning.graduation.dtos.User_DTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // ===== User -> User_DTO =====
    @Mapping(source = "role.roleName", target = "roleName")
    User_DTO toDTO(User user);

    // ===== Student -> Student_DTO =====
    Student_DTO studentToDTO(Student student);

    // ===== Instructor -> Instructor_DTO =====
    Instructor_DTO instructorToDTO(Instructor instructor);

    // ===== User_DTO -> User =====
    @Mapping(target = "role", ignore = true)
    User toEntity(User_DTO dto);
}