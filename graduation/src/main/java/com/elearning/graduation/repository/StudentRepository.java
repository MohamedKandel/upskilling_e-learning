package com.elearning.graduation.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.graduation.domain.student;

public interface StudentRepository extends JpaRepository<student, UUID> {

    Optional<student> findByEmail(String email);

    boolean existsByEmail(String email);
    
}
