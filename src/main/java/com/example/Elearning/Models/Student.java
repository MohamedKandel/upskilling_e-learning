package com.example.Elearning.Models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "account_id")
public class Student extends User {

    @Column(name = "phone", length = 20)
    private String phone;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    public Student() {
    }

    public Student(String phone) {
        this.phone = phone;
    }

    public Student(int accountId, String name, String email, String password, Role role) {
        super(accountId, name, email, password, role);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
