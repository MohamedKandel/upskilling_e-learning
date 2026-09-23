package com.example.Elearning.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Elearning.Models.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
}
