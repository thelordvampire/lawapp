package com.app.law.controller;

import com.app.law.entity.Role;
import com.app.law.entity.User;
import com.app.law.service.IRoleService;
import com.app.law.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

    private final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value="/role/get-all", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Role>> getAllRole() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAll());
    }

}
