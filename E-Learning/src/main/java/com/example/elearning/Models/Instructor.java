package com.example.elearning.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SYS_Instructor")
@PrimaryKeyJoinColumn(name = "USER_ID")
public class Instructor extends User {

    @Column(name = "Experience_Years")
    int Experience_Years;

    @OneToMany(mappedBy = "instructor")
    List<Course> course = new ArrayList<>();

    public Instructor() {
    }

    public int getExperience_Years() {
        return Experience_Years;
    }

    public void setExperience_Years(int experience_Years) {
        Experience_Years = experience_Years;
    }

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }
}
