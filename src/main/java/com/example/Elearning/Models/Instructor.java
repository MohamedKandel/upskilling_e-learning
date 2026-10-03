package com.example.Elearning.Models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "instructor")
@PrimaryKeyJoinColumn(name = "account_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instructor extends User {

    @Lob
    @Column(name = "cv", columnDefinition = "LONGBLOB")
    private byte[] cv;

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;
}
