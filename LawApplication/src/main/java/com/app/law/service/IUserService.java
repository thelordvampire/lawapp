package com.app.law.service;

import com.app.law.entity.User;

import java.util.List;

public interface IUserService {

    User login(String email, String password);

    User findUserByEmail(String email);

    User findUserById(Integer userId);

    User createUser(User user);

    User updateUser(User user);

    boolean changePassword(String newPassword);

    User getLoginedUser();

    List<User> getAll();
}