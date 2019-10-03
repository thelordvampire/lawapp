package com.app.law.controller;

import com.app.law.entity.Field;
import com.app.law.service.IFieldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FieldController {

    private final Logger log = LoggerFactory.getLogger(FieldController.class);

    @Autowired
    private IFieldService fieldService;

    @RequestMapping(value="/field/getAll", method = RequestMethod.GET)
    public ResponseEntity<Object> getAll() {
        log.info("get all field");
        List<Field> listField = fieldService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(listField);
    }

    @RequestMapping(value="/field/{fieldId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getbyId(@PathVariable Integer fieldId) {
        log.info("get field by id: {}", fieldId);
        Field field = fieldService.getById(fieldId);
        return ResponseEntity.status(HttpStatus.OK).body(field);
    }

    @RequestMapping(value="/field/create", method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Field field) {
        log.info("create field: {}", field);
        field = fieldService.create(field);
        return ResponseEntity.status(HttpStatus.OK).body(field);
    }

    @RequestMapping(value="/field/update", method = RequestMethod.PUT)
    public ResponseEntity<Field> update(@RequestBody Field field) {
        log.info("update field: {}", field);
        field = fieldService.update(field);
        return ResponseEntity.status(HttpStatus.OK).body(field);
    }

    @RequestMapping(value="/field/delete/{fieldId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable Integer fieldId) {
        log.info("delete field by id: {}", fieldId);
        Field field = fieldService.delete(fieldId);
        return ResponseEntity.status(HttpStatus.OK).body(field);
    }

}
