package com.elearning.graduation.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.graduation.domain.Course;
import com.elearning.graduation.domain.CourseStatus;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {

    List<Course> findByInstructor_AccountId(UUID instructorId);

    List<Course> findByCrsNameContainingIgnoreCase(String keyword);

    long countByInstructor_AccountId(UUID instructorId);

    List<Course> findByStatus(CourseStatus status);

    List<Course> findByInstructor_AccountIdAndStatus(UUID instructorId, CourseStatus status);

    long countByStatus(CourseStatus status);

    //Add course by instructor:



}