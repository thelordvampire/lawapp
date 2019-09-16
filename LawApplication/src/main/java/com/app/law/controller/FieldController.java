package com.app.law.controller;

import com.app.law.entity.Field;
import com.app.law.service.IFieldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
