package com.elearning.graduation.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.graduation.domain.Session;
import com.elearning.graduation.domain.SessionStatus;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {

    List<Session> findByCourse_CrsId(UUID courseId);

    List<Session> findByCourse_CrsIdOrderBySessionOrderAsc(UUID courseId);

    List<Session> findBySessionNameContainingIgnoreCase(String keyword);

    long countByCourse_CrsId(UUID courseId);

    List<Session> findByStatus(SessionStatus status);
}