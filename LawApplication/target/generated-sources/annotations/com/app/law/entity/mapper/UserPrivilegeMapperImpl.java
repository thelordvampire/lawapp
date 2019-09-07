package com.app.law.entity.mapper;

import com.app.law.dto.UserPrivilegeDto;
import com.app.law.entity.Privilege;
import com.app.law.entity.User_Privilege;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-09-07T22:49:43+0700",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_65 (Oracle Corporation)"
)
public class UserPrivilegeMapperImpl implements UserPrivilegeMapper {

    @Override
    public UserPrivilegeDto toDTO(User_Privilege userPrivilege) {
        if ( userPrivilege == null ) {
            return null;
        }

        UserPrivilegeDto userPrivilegeDto = new UserPrivilegeDto();

        String name = userPrivilegePrivilegeName( userPrivilege );
        if ( name != null ) {
            userPrivilegeDto.setPrivilegeName( name );
        }

        return userPrivilegeDto;
    }

    private String userPrivilegePrivilegeName(User_Privilege user_Privilege) {
        if ( user_Privilege == null ) {
            return null;
        }
        Privilege privilege = user_Privilege.getPrivilege();
        if ( privilege == null ) {
            return null;
        }
        String name = privilege.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
