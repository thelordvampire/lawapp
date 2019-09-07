package com.app.law.entity.mapper;

import com.app.law.dto.UserDto;
import com.app.law.dto.UserSpecializationDto;
import com.app.law.entity.User;
import com.app.law.entity.User_Privilege;
import com.app.law.entity.User_Specialization;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-09-07T22:49:43+0700",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_65 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    private final UserSpecializationMapper userSpecializationMapper = Mappers.getMapper( UserSpecializationMapper.class );

    @Override
    public UserDto toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setListSpecialization( user_SpecializationListToUserSpecializationDtoList( user.getListUserSpecialization() ) );
        userDto.setEmail( user.getEmail() );
        userDto.setListPrivilege( user_PrivilegeListToStringList( user.getListUserPrivilege() ) );
        userDto.setId( user.getId() );
        userDto.setPassword( user.getPassword() );
        userDto.setRepassword( user.getRepassword() );
        userDto.setName( user.getName() );
        userDto.setImage( user.getImage() );
        userDto.setToken( user.getToken() );

        return userDto;
    }

    protected List<UserSpecializationDto> user_SpecializationListToUserSpecializationDtoList(List<User_Specialization> list) {
        if ( list == null ) {
            return null;
        }

        List<UserSpecializationDto> list1 = new ArrayList<UserSpecializationDto>( list.size() );
        for ( User_Specialization user_Specialization : list ) {
            list1.add( userSpecializationMapper.toDTO( user_Specialization ) );
        }

        return list1;
    }

    protected List<String> user_PrivilegeListToStringList(List<User_Privilege> list) {
        if ( list == null ) {
            return null;
        }

        List<String> list1 = new ArrayList<String>( list.size() );
        for ( User_Privilege user_Privilege : list ) {
            list1.add( getPrivilegeName( user_Privilege ) );
        }

        return list1;
    }
}
