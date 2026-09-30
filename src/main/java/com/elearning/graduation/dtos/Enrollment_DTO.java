package com.elearning.graduation.dtos;

import java.time.LocalDate;
import java.util.UUID;

public class Enrollment_DTO {

    private UUID studentId;
    private String studentName;
    private UUID crsId;
    private String crsName;
    private LocalDate enrollmentDate;
    private String status;

    public Enrollment_DTO() {}

    public Enrollment_DTO(UUID studentId, String studentName, UUID crsId,
                          String crsName, LocalDate enrollmentDate, String status) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.crsId = crsId;
        this.crsName = crsName;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    public UUID getStudentId() { return studentId; }
    public void setStudentId(UUID studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public UUID getCrsId() { return crsId; }
    public void setCrsId(UUID crsId) { this.crsId = crsId; }

    public String getCrsName() { return crsName; }
    public void setCrsName(String crsName) { this.crsName = crsName; }

    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}