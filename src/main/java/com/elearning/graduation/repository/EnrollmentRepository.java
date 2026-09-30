package com.elearning.graduation.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.graduation.domain.Enrollment;
import com.elearning.graduation.domain.EnrollmentId;
import com.elearning.graduation.domain.EnrollmentStatus;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {

    List<Enrollment> findByStudent_AccountId(UUID studentId);

    List<Enrollment> findByCourse_CrsId(UUID crsId);

    long countByCourse_CrsId(UUID crsId);

    long countByStudent_AccountId(UUID studentId);

    List<Enrollment> findByStudent_AccountIdAndStatus(UUID studentId, EnrollmentStatus status);

    boolean existsByStudent_AccountIdAndCourse_CrsId(UUID studentId, UUID crsId);
}