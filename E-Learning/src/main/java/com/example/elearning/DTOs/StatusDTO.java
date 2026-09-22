package com.example.elearning.DTOs;

import com.example.elearning.Models.Course;
import com.example.elearning.Models.Session;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StatusDTO {

    private UUID statusId;

    private String statusName;

    List<UUID> coursesIds = new ArrayList<>();
    List<UUID> sessionsIds = new ArrayList<>();

    public StatusDTO() {
    }

    //Constructor For inserting New Status
    public StatusDTO(String statusName) {
        this.statusName = statusName;
    }


    //Constructor For Displaying any  Status Info

    public StatusDTO(UUID statusId, String statusName, List<UUID> coursesIds, List<UUID> sessionsIds) {
        this.statusId = statusId;
        this.statusName = statusName;
        this.coursesIds = coursesIds;
        this.sessionsIds = sessionsIds;
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

    public List<UUID> getCoursesIds() {
        return coursesIds;
    }

    public void setCoursesIds(List<UUID> coursesIds) {
        this.coursesIds = coursesIds;
    }

    public List<UUID> getSessionsIds() {
        return sessionsIds;
    }

    public void setSessionsIds(List<UUID> sessionsIds) {
        this.sessionsIds = sessionsIds;
    }
}
