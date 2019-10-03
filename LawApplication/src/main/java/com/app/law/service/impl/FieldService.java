package com.app.law.service.impl;

import com.app.law.entity.Field;
import com.app.law.repository.FieldRepository;
import com.app.law.service.IFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService implements IFieldService {

    @Autowired
    private FieldRepository fieldRepo;

    @Override
    public List<Field> getAll() {
        return fieldRepo.findAll();
    }

    @Override
    public Field getById(Integer fieldId) {
        return fieldRepo.getOne(fieldId);
    }

    @Override
    public Field create(Field field) {
        return fieldRepo.save(field);
    }

    @Override
    public Field update(Field field) {
        return fieldRepo.save(field);
    }

    @Override
    public Field delete(Integer fieldId) {
        Field field = getById(fieldId);
        fieldRepo.deleteById(fieldId);
        return field;
    }
}
