package com.app.law.entity.mapper;

import com.app.law.dto.UserPrivilegeDto;
import com.app.law.dto.UserSpecializationDto;
import com.app.law.entity.User_Privilege;
import com.app.law.entity.User_Specialization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserPrivilegeMapper {

    UserPrivilegeMapper INSTANCE = Mappers.getMapper( UserPrivilegeMapper.class );

    @Mapping(source = "privilege.name", target = "privilegeName")
    UserPrivilegeDto toDTO(User_Privilege userPrivilege);

}