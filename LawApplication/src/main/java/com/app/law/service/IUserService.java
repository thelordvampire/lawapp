package com.app.law.service;

import com.app.law.entity.User;

public interface IUserService {

    User login(String username, String password);

    User findUserByUsername(String username);

    User createUser(User user);

    User updateUser(User user);

    boolean changePassword(String newPassword);

    User getLoginedUser();
}