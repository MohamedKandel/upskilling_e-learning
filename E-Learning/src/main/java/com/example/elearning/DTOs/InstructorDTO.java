package com.example.elearning.DTOs;

import com.example.elearning.Enum.Role;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InstructorDTO {
    private UUID userId;

    private String fullName;

    private String email;

    private String password;
    int Experience_Years;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private Role roleName; //insert role name, that's better and check if there's a role with this name, I can get its id

    //2 relationships:
    //One to many: user if instructor teaches 0,1,many courses
    List<UUID> courseTaughtIds = new ArrayList<>(); //courses ids, avoiding null bu initialization

    //MANY to MANY student can get enrolled in many courses and Many courses have many students

    List<UUID> coursesStudentEnrolledIn = new ArrayList<>();

    //Empty Constructor - non-parametrized

    public InstructorDTO() {
    }


    //Constructor for what's gonna be displayed

    //Won't display hashed passwords, if users info is required
    public InstructorDTO(UUID userId, String fullName,  String email, int Experience_Years, LocalDateTime createdAt, LocalDateTime updatedAt, Role roleName, List<UUID> courseTaughtIds, List<UUID> coursesStudentEnrolledIn) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.roleName = roleName;
        this.Experience_Years = Experience_Years;
        this.courseTaughtIds = courseTaughtIds;
        this.coursesStudentEnrolledIn = coursesStudentEnrolledIn;

    }

    public int getExperience_Years() {
        return this.Experience_Years;
    }

    public void setExperience_Years(int experience_Years) {
        this.Experience_Years = experience_Years;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.fullName;
    }

    public void setName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<UUID> getCourseTaughtIds() {
        return this.courseTaughtIds;
    }

    public void setCourseTaughtIds(List<UUID> courseTaughtIds) {
        this.courseTaughtIds = courseTaughtIds;
    }

    public Role getRoleName() {
        return this.roleName;
    }

    public void setRoleName(Role roleName) {
        this.roleName = roleName;
    }

    public List<UUID> getCoursesStudentEnrolledIn() {
        return coursesStudentEnrolledIn;
    }

    public void setCoursesStudentEnrolledIn(List<UUID> coursesStudentEnrolledIn) {
        this.coursesStudentEnrolledIn = coursesStudentEnrolledIn;
    }


}
