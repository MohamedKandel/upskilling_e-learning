package com.example.elearning.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name="SYS_COURSE")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="COURSE_ID")
    private UUID courseId;

    //Course ---- USER(if instructor)
    //can be taught by one instructor

    @ManyToOne
    @JoinColumn(name="USER_ID") //must be the id shown in the db
    private User instructor;

    @ManyToOne
    @JoinColumn(name="Status_ID")
    private Status courseStatus;

    @Column(name="COURSE_NAME")
    private String courseName;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="THUMBNAIL_URL")
    private String thumbnailUrl;


    @Column(name="CREATED_AT")
    LocalDateTime createdAt;

    @Column(name="UPDATED_AT")
    LocalDateTime updatedAt;

    @ManyToMany (mappedBy = "courses") //the associative entity relationship
    List<User> students = new ArrayList<>();
    //Course --- USER(if Student) --there's enrollment in between(Associative entity)

    //Course has many sessions
    @OneToMany(mappedBy = "course")
    List<Session> courseSessions = new ArrayList<>();

    public Course() {
    }


    public Course(UUID courseId, User instructor, Status courseStatus, String courseName, String description, String thumbnailUrl, LocalDateTime createdAt, LocalDateTime updatedAt, List<User> students, List<Session> courseSessions) {
        this.courseId = courseId;
        this.instructor = instructor;
        this.courseStatus = courseStatus;
        this.courseName = courseName;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.students = students;
        this.courseSessions = courseSessions;
    }


    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public Status getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(Status courseStatus) {
        this.courseStatus = courseStatus;
    }

    public User getInstructor() {
        return this.instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDESCRIPTION() {
        return this.description;
    }

    public void setDESCRIPTION(String description) {
        this.description = description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public List<Session> getCourseSessions() {
        return courseSessions;
    }

    public void setCourseSessions(List<Session> courseSessions) {
        this.courseSessions = courseSessions;
    }

    @PrePersist
    public void createdAt()
    {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

    }
    @PreUpdate
    public void UpdatedAt()
    {
        this.updatedAt = LocalDateTime.now();

    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
