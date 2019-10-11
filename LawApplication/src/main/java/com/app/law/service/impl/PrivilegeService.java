package com.app.law.service.impl;

import com.app.law.entity.Privilege;
import com.app.law.repository.PrivilegeRepository;
import com.app.law.service.IprivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegeService implements IprivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepo;

    @Override
    public List<Privilege> getAll() {
        return privilegeRepo.findAll();
    }
}
