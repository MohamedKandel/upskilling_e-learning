package com.example.Elearning.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Elearning.Models.Course;
import com.example.Elearning.Models.Enrollment;
import com.example.Elearning.Models.Student;

public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {

    boolean existsByStudentAndCourse(Student student, Course course);

    List<Enrollment> findByStudent(Student student);

    List<Enrollment> findByCourse(Course course);
}
