package com.app.law.service.impl;

import com.app.law.entity.Role;
import com.app.law.repository.RoleRepository;
import com.app.law.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleById(Integer id) {
        return this.roleRepository.getOne(id);
    }
}
