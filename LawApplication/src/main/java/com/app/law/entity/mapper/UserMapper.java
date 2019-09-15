package com.app.law.entity.mapper;

import com.app.law.dto.user.UserDto;
import com.app.law.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mappings({
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "listUserPrivilege", target = "listPrivilege")
    })
    UserDto toDTO(User user);
}
