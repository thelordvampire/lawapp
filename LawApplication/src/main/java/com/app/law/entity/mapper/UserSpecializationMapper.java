package com.app.law.entity.mapper;

import com.app.law.dto.UserSpecializationDto;
import com.app.law.entity.User_Specialization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserSpecializationMapper {

    UserSpecializationMapper INSTANCE = Mappers.getMapper( UserSpecializationMapper.class );

    @Mapping(source = "specialization.name", target = "specializationName")
    UserSpecializationDto toDTO(User_Specialization user_specialization);
}
