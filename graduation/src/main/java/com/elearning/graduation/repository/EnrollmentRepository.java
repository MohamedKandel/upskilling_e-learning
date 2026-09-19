package com.elearning.graduation.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.graduation.domain.enrollment;

public interface EnrollmentRepository extends JpaRepository<enrollment, UUID> {

    List<enrollment> findByStudent_StdId(UUID studentId);

    List<enrollment> findBySession_SesId(UUID sessionId);

    long countBySession_SesId(UUID sessionId);
    
}
