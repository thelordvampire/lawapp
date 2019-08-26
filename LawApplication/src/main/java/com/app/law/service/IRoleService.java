package com.app.law.service;

import com.app.law.entity.Role;

import java.util.List;

public interface IRoleService {

    Role getRoleById(Integer id);

    List<Role> getAll();
}
