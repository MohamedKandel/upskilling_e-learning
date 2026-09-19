package com.elearning.graduation.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class doneSessionID implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID studentId;
    private UUID sessionId;

    public doneSessionID() {
    }

    public doneSessionID(UUID studentId, UUID sessionId) {
        this.studentId = studentId;
        this.sessionId = sessionId;
    }

    public UUID getStudentId() {
        return studentId;
    }
    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public UUID getSessionId() {
        return sessionId;
    }
    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof doneSessionID)) return false;
        doneSessionID that = (doneSessionID) o;
        return Objects.equals(studentId, that.studentId) &&
               Objects.equals(sessionId, that.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, sessionId);
    }
}