package com.elearning.graduation.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.graduation.domain.Course;

public interface CourseRepository extends JpaRepository<Course, UUID> {

    List<Course> findByInstructor_AccountId(UUID instructorId);

    List<Course> findByCrsNameContainingIgnoreCase(String keyword);

    long countByInstructor_AccountId(UUID instructorId);
    
}
