package com.elearning.graduation.mapper;

import com.elearning.graduation.domain.Course;
import com.elearning.graduation.dtos.CourseInput_DTO;
import com.elearning.graduation.dtos.Course_DTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface CourseMapper
{
    //from Course to CourseDTO
    @Mapping(target="instructorId", source="instructor.instructorId")

    @Mapping(target="instructorName", source="instructor.name")
    //list of session: the length / size of the list
    @Mapping(target = "sessionCount", expression = "java(course.getSessions().size())")
    public Course_DTO CoursetoDTO(Course course);


    @Mapping(target="crsId", ignore = true)
    @Mapping(target="instructor", ignore = true)

    @Mapping(target="instructor", ignore = true)
    Course toCourse(CourseInput_DTO course);

}
