package com.app.law.controller;

import com.app.law.entity.Specialization;
import com.app.law.service.ISpecializationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;

@RestController
public class SpecializationController {

    private final Logger log = LoggerFactory.getLogger(SpecializationController.class);

    @Autowired
    private ISpecializationService specializationService;

    @RequestMapping(value="/specialization/getAll", method = RequestMethod.GET)
    public ResponseEntity<Object> getAll() {
        log.info("get all specialization");
        List<Specialization> listSpecialization = specializationService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(listSpecialization);
    }

}
