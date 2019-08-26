package com.app.law.service.impl;

import com.app.law.entity.Role;
import com.app.law.repository.RoleRepository;
import com.app.law.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepo;

    public Role getRoleById(Integer id) {
        return roleRepo.getOne(id);
    }

    @Override
    public List<Role> getAll() {
        return roleRepo.findAll();
    }
}
