package com.app.law.service;

import com.app.law.entity.Field;

import java.util.List;

public interface IFieldService {

    List<Field> getAll();

    Field getById(Integer fieldId);

    Field create(Field field);
    Field update(Field field);
    Field delete(Integer fieldId);
}
