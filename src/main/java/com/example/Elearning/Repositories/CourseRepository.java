package com.example.Elearning.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Elearning.Models.Course;
import com.example.Elearning.Models.Instructor;

public interface CourseRepository extends JpaRepository<Course, UUID> {

     List<Course> findByInstructor_AccountId(int instructorId);
}
