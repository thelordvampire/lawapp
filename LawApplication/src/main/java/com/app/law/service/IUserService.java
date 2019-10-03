package com.app.law.service;

import com.app.law.dto.user.UserDto;
import com.app.law.entity.User;

import java.util.List;

public interface IUserService {

    UserDto login(String email, String password);

    User findUserByEmail(String email);

    User findUserById(Integer userId);

    User createUser(User user);

    UserDto createUser2(UserDto dto);

    UserDto updateUser(UserDto user);

    boolean changePassword(String newPassword);

    User getLoginedUser();

    List<UserDto> getAll();

    List<UserDto> getAllLawer();
    List<UserDto> getLawerByType(Integer type);
}