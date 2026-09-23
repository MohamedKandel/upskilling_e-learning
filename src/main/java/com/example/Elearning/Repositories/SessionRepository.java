package com.example.Elearning.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Elearning.Models.Course;
import com.example.Elearning.Models.Session;

public interface SessionRepository extends JpaRepository<Session, UUID> {

    List<Session> findByCourse(Course course);
}
