package com.elearning.graduation.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.graduation.domain.doneSessionID;
import com.elearning.graduation.domain.doneSessions;

@Repository
public interface doneSessionsRepository extends JpaRepository<doneSessions, doneSessionID> {

    List<doneSessions> findByStudent_AccountId(UUID studentId);

    List<doneSessions> findBySession_SessionId(UUID sessionId);

    long countBySession_SessionIdAndIsCompletedTrue(UUID sessionId);

    long countByStudent_AccountIdAndIsCompletedTrue(UUID studentId);

    boolean existsByStudent_AccountIdAndSession_SessionIdAndIsCompletedTrue(UUID studentId, UUID sessionId);

    List<doneSessions> findByStudent_AccountIdAndSession_Course_CrsId(UUID studentId, UUID crsId);
}