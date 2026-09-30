package com.elearning.graduation.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CourseInput_DTO {

    private String crsName;
    private String description;
    private String status;
    private String thumbnailUrl;


}
