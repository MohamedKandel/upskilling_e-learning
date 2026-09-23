package com.example.elearning.Repos;

import com.example.elearning.Models.Instructor;
import com.example.elearning.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InstructorRepo extends JpaRepository<Instructor, UUID> {

    public Optional<Instructor> findByUserId(UUID id);

    public Optional<Instructor> findByEmail(String email);

}
