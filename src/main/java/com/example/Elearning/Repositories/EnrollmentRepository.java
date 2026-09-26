package com.example.Elearning.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Elearning.Models.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {

    boolean existsByStudent_AccountIdAndCourse_Id(int studentId, UUID courseId);

    List<Enrollment> findByStudent_AccountId(int studentId);

    List<Enrollment> findByCourse_Id(UUID courseId);
}