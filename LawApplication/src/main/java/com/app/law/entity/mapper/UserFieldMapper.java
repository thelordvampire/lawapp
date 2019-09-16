package com.app.law.entity.mapper;

import com.app.law.dto.UserFieldDto;
import com.app.law.entity.User_Field;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserFieldMapper {

    UserFieldMapper INSTANCE = Mappers.getMapper( UserFieldMapper.class );

    @Mapping(source = "field.name", target = "fieldName")
    UserFieldDto toDTO(User_Field user_field);
}
