
package com.example.Elearning.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.Elearning.DTOs.CourseResponse;
import com.example.Elearning.DTOs.CourseRequest;
import com.example.Elearning.DTOs.UpdateCourseRequest;
import com.example.Elearning.Models.Course;
import com.example.Elearning.Models.Instructor;
import com.example.Elearning.Repositories.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                instructor.getAccountId());
    }

    public CourseResponse updateCourse(
            UUID courseId,
            UpdateCourseRequest request,
            Instructor instructor) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Check course ownership
        if (course.getInstructor().getAccountId() != instructor.getAccountId()) {

            throw new RuntimeException(
                    "You are not the owner of this course");
        }

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setThumbnail(request.getThumbnail());

        course.setUpdatedAt(LocalDateTime.now());

        Course updatedCourse = courseRepository.save(course);

        return new CourseResponse(
                updatedCourse.getCourseId(),
                updatedCourse.getTitle(),
                updatedCourse.getDescription(),
                updatedCourse.getThumbnail(),
                updatedCourse.getCreatedAt(),
                updatedCourse.getStatus(),
                instructor.getAccountId());
    }


public Page<CourseResponse> getAllCourses(Pageable pageable) {

    Page<Course> courses =
            courseRepository.findAll(pageable);

    return courses.map(course ->
            new CourseResponse(
                    course.getCourseId(),
                    course.getTitle(),
                    course.getDescription(),
                    course.getThumbnail(),
                    course.getCreatedAt(),
                    course.getStatus(),
                    course.getInstructor().getAccountId()
            )
    );
}



}
