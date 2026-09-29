package com.example.Elearning.Services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.Elearning.DTOs.CourseResponse;
import com.example.Elearning.DTOs.CourseRequest;
import com.example.Elearning.Models.Course;
import com.example.Elearning.Models.Instructor;
import com.example.Elearning.Repositories.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseResponse createCourse(
            CourseRequest request,
            Instructor instructor) {

        Course course = new Course();

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setThumbnail(request.getThumbnail());

        course.setCreatedAt(LocalDateTime.now());
        course.setUpdatedAt(LocalDateTime.now());

        course.setStatus("PENDING");

        course.setInstructor(instructor);

        Course savedCourse = courseRepository.save(course);

        return new CourseResponse(
                savedCourse.getCourseId(),
                savedCourse.getTitle(),
                savedCourse.getDescription(),
                savedCourse.getThumbnail(),
                savedCourse.getCreatedAt(),
                savedCourse.getStatus(),
                instructor.getAccountId()
        );
    }
}