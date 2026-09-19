package com.elearning.graduation.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "enrollment")
@IdClass(enrollmentID.class)
public class enrollment {

    @Id
    @Column(name = "account_id")
    private UUID accountId;

    @Id
    @Column(name = "crs_id")
    private UUID crsId;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private student student;

    @ManyToOne
    @JoinColumn(name = "crs_id", insertable = false, updatable = false)
    private courses course;

    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private EnrollmentStatus status;


    public UUID getAccountId() {
        return accountId;
    }
    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public UUID getCrsId() {
        return crsId;
    }
    public void setCrsId(UUID crsId) {
        this.crsId = crsId;
    }

    public student getStudent() {
        return student;
    }
    public void setStudent(student student) {
        this.student = student;
    }

    public courses getCourse() {
        return course;
    }
    public void setCourse(courses course) {
        this.course = course;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }
    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }
    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }


    public enum EnrollmentStatus {
        in_progress,
        done
    }
}