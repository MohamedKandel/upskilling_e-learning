package com.elearning.graduation.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.graduation.domain.instructor;

public interface InstructorRepository extends JpaRepository<instructor, UUID> {

    Optional<instructor> findByEmail(String email);

    boolean existsByEmail(String email);
    
}
