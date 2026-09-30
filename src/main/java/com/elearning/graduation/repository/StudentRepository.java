package com.elearning.graduation.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.graduation.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    Optional<Student> findByPhone(String phone);

    boolean existsByPhone(String phone);
}