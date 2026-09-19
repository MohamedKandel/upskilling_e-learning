package com.elearning.graduation.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "crs_id",
            nullable = false,
            updatable = false,
            columnDefinition = "BINARY(16)")
    private UUID crsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", nullable = false)
    private instructor instructor;

    @Column(name = "crs_name", nullable = false, length = 300)
    private String crsName;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // ===== Getters & Setters =====

    public UUID getCrsId() {
        return crsId;
    }
    public void setCrsId(UUID crsId) {
        this.crsId = crsId;
    }

    public instructor getInstructor() {
        return instructor;
    }
    public void setInstructor(instructor instructors) {
        this.instructor = instructors;
    }

    public String getCrsName() {
        return crsName;
    }
    public void setCrsName(String crsName) {
        this.crsName = crsName;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}