package com.example.elearning.Mappers;


import com.example.elearning.DTOs.CourseDTO;
import com.example.elearning.Models.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;


//CourseDTO → Course mapping should NOT necessarily map every field, we map what's needed
@Mapper(componentModel="spring")
public interface CourseMapper {


    public Course CourseToEntity(CourseDTO courseDto);


}
