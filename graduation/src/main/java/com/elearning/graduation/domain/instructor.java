package com.elearning.graduation.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "instructors")
@PrimaryKeyJoinColumn(name = "account_id")
public class instructor extends User {

    @Lob
    @Column(name = "cv_file", columnDefinition = "LONGBLOB")
    private byte[] cvFile;

    @Column(name = "cv_file_name", length = 255)
    private String cvFileName;

    @Column(name = "cv_file_size")
    private Integer cvFileSize;

    @Column(name = "cv_uploaded_at")
    private LocalDateTime cvUploadedAt;

    @Column(name = "bio", length = 500)
    private String bio;

    public byte[] getCvFile() {
        return cvFile;
    }
    public void setCvFile(byte[] cvFile) {
        this.cvFile = cvFile;
    }

    public String getCvFileName() {
        return cvFileName;
    }
    public void setCvFileName(String cvFileName) {
        this.cvFileName = cvFileName;
    }

    public Integer getCvFileSize() {
        return cvFileSize;
    }
    public void setCvFileSize(Integer cvFileSize) {
        this.cvFileSize = cvFileSize;
    }

    public LocalDateTime getCvUploadedAt() {
        return cvUploadedAt;
    }
    public void setCvUploadedAt(LocalDateTime cvUploadedAt) {
        this.cvUploadedAt = cvUploadedAt;
    }

    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
}