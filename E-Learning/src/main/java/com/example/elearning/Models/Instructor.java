package com.example.elearning.Models;


import jakarta.persistence.*;

@Entity
@Table(name="SYS_Instructor")
@PrimaryKeyJoinColumn(name="USER_ID")
public class Instructor extends User {

    @Column(name="Experience_Years")
    int Experience_Years;

    public int getExperience_Years() {
        return Experience_Years;
    }

    public void setExperience_Years(int experience_Years) {
        Experience_Years = experience_Years;
    }
}
