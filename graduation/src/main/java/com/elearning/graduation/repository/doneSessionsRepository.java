package com.elearning.graduation.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.graduation.domain.doneSessions;

public interface doneSessionsRepository extends JpaRepository<doneSessions, UUID> {

    List<doneSessions> findByStudent_StdId(UUID studentId);

    List<doneSessions> findBySession_SesId(UUID sessionId);

    long countBySession_SesId(UUID sessionId);

    boolean existsByStudent_AccountIdAndSession_SessionIdAndIsCompletedTrue(UUID studentId, UUID sessionId);
    
}
