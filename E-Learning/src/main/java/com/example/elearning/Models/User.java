package com.example.elearning.Models;


import com.example.elearning.Enum.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="SYS_USER")

//Option 8A to avoid nulls
//User:Supertype  holds shared attributes, subtypes: holds supertype id + the specific attributes
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @Column(name="USER_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(name="Full_Name")
    private String fullName;


    @Enumerated(EnumType.STRING) //Review
    @Column(name="Role")
    private Role role;

    @Column(name="Email")
    private String email;

    @Column(name="Password")
    private String password;


    //Soft deletion
    // 1 = active/present true  --instead of deletion of user record
    // 0 =  deactivated false
    @Column(name="Active", nullable = false)
    private int active = 1;


    @Column(name="CREATED_AT")
    private LocalDateTime createdAt;


    @Column(name="UPDATE_AT")
    private LocalDateTime updatedAt;

    //user if instructor teaches 0,1,many courses
    @OneToMany(mappedBy = "instructor")
    List<Course> course = new ArrayList<>();



    //MANY to MANY student can get enrolled in many courses and Many courses have many students
    @ManyToMany
    //representing the associative entity with annotations since, no additional attributes
    //relate to the enrollment table itself
    @JoinTable(name="SYS_Enrollment",
    joinColumns = @JoinColumn(name="USER_ID")
    , inverseJoinColumns = @JoinColumn(name="COURSE_ID"))
    List<Course> courses = new ArrayList<>();




    public User() {
    }

    public User( UUID userId, String fullName, Role role, String email, String password,int active, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.role = role;
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
