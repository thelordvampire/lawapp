package com.app.law.mapper;

import com.app.law.dto.user.*;
import com.app.law.entity.User;
import com.google.gson.Gson;

import java.lang.reflect.Type;

public class UserMapperCustom {

    public static User dtoToEntity(UserDto dto) {
        Gson gson = new Gson();
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setImage(dto.getName());
        user.setPassword(dto.getPassword());
        user.setIntroduce(dto.getIntroduce());
        user.setCharges(dto.getCharges());
        user.setField(dto.getField());
        user.setInforDetail(gson.toJson(dto.getInforDetails()));
        user.setPrize(gson.toJson(dto.getPrizes()));
        user.setEducation(gson.toJson(dto.getEducations()));
        user.setExperience(gson.toJson(dto.getExperiences()));
        user.setRoleId(dto.getRoleId());

        return user;
    }

    public static UserDto entityToDto(User user) {
        Gson gson = new Gson();
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setImage(user.getImage());
        dto.setIntroduce(user.getIntroduce());
        dto.setCharges(user.getCharges());
        dto.setField(user.getField());
        dto.setInforDetails(gson.fromJson(user.getInforDetail() , InforDetail[].class));
        dto.setPrizes(gson.fromJson(user.getPrize() , Prize[].class));
        dto.setEducations(gson.fromJson(user.getEducation() , Education[].class));
        dto.setExperiences(gson.fromJson(user.getExperience() , Experience[].class));

        return dto;

    }

}
