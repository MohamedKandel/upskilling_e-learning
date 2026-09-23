package com.example.elearning.Repos;

import com.example.elearning.Models.Student;
import com.example.elearning.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepo extends JpaRepository<Student, UUID>
{

    public Optional<Student> findByEmail(String email);


}

