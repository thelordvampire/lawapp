package com.app.law.service.impl;

import com.app.law.entity.Specialization;
import com.app.law.repository.SpecializationRepository;
import com.app.law.service.ISpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationService implements ISpecializationService {

    @Autowired
    private SpecializationRepository specializationRepo;

    @Override
    public List<Specialization> getAll() {
        return specializationRepo.findAll();
    }
}
