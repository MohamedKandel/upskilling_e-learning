package com.example.elearning.Mappers;


import ch.qos.logback.core.model.ComponentModel;
import com.example.elearning.DTOs.StatusDTO;
import com.example.elearning.Models.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StatusMapper {


//REVIEW: we can change StatusDTO though so , we don't have to ignore these
    @Mapping(target = "statusId", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "sessions", ignore = true)
    public Status ToStatusEntity(StatusDTO statusDTO);

}



