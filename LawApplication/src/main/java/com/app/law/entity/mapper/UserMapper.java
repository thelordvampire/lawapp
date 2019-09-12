package com.app.law.entity.mapper;

import com.app.law.dto.user.UserDto;
import com.app.law.entity.User;
import com.app.law.entity.User_Privilege;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UserSpecializationMapper.class, UserPrivilegeMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mappings({
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "listUserPrivilege", target = "listPrivilege"),
            @Mapping(source = "listUserSpecialization", target = "listSpecialization")
    })
    UserDto toDTO(User user);

    default String getPrivilegeName(User_Privilege userPrivilege) {
        return userPrivilege.getPrivilege().getName();
    }
}
