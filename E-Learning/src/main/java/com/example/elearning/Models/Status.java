package com.example.elearning.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="SYS_Status")
public class Status {

    @Id
    @Column(name="Status_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID statusId;

    @Column(name="Status_NAME")
    private String statusName;


    @OneToMany(mappedBy = "courseStatus")
    List<Course> courses = new ArrayList<>();


    @OneToMany(mappedBy = "sessionStatus")
    List<Session> sessions = new ArrayList<>();

    public Status() {
    }


    public Status(UUID statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public UUID getStatusId() {
        return statusId;
    }

    public void setStatusId(UUID statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
