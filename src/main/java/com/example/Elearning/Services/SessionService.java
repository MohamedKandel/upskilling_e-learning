
package com.example.Elearning.Services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.Elearning.DTOs.SessionRequest;
import com.example.Elearning.Models.Course;
import com.example.Elearning.Models.Instructor;
import com.example.Elearning.Models.Session;
import com.example.Elearning.Repositories.CourseRepository;
import com.example.Elearning.Repositories.SessionRepository;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final CourseRepository courseRepository;

    public SessionService(
            SessionRepository sessionRepository,
            CourseRepository courseRepository) {

        this.sessionRepository = sessionRepository;
        this.courseRepository = courseRepository;
    }

    public Session createSession(
            SessionRequest request,
            byte[] video,
            Instructor instructor) {

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        if (course.getInstructor().getAccountId()
                != instructor.getAccountId()) {

            throw new RuntimeException(
                    "You are not the owner of this course");
        }

        Session session = new Session();

        session.setTitle(request.getTitle());
        session.setDescription(request.getDescription());
        session.setVideoUrl(video);
        session.setCreatedAt(LocalDateTime.now());
        session.setCourse(course);

        return sessionRepository.save(session);
    }
}

