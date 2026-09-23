package com.example.Elearning.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Elearning.Models.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
