package com.app.law.entity.mapper;

import com.app.law.dto.UserSpecializationDto;
import com.app.law.entity.Specialization;
import com.app.law.entity.User_Specialization;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-09-07T22:49:43+0700",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_65 (Oracle Corporation)"
)
public class UserSpecializationMapperImpl implements UserSpecializationMapper {

    @Override
    public UserSpecializationDto toDTO(User_Specialization user_specialization) {
        if ( user_specialization == null ) {
            return null;
        }

        UserSpecializationDto userSpecializationDto = new UserSpecializationDto();

        String name = user_specializationSpecializationName( user_specialization );
        if ( name != null ) {
            userSpecializationDto.setSpecializationName( name );
        }

        return userSpecializationDto;
    }

    private String user_specializationSpecializationName(User_Specialization user_Specialization) {
        if ( user_Specialization == null ) {
            return null;
        }
        Specialization specialization = user_Specialization.getSpecialization();
        if ( specialization == null ) {
            return null;
        }
        String name = specialization.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
