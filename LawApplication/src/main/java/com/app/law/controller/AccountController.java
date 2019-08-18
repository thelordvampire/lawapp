package com.app.law.controller;

import com.app.law.auth.JwtProvider;
import com.app.law.entity.User;
import com.app.law.service.IUserService;
import com.app.law.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class AccountController {

    private final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private IUserService userService;

//    @RequestMapping(value="/user/login", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
//    public ResponseEntity<Object> logina(@RequestParam(required = false) String username, @RequestParam(required = false) String password) throws URISyntaxException {
//        log.debug("login: {}, {}", username, password);
//
//        return ResponseEntity.accepted().headers(HttpHeaders.EMPTY).body("no logined user found");
//    }

    @RequestMapping(value="/user/login1", method = RequestMethod.GET)
    public ResponseEntity<Object> login111(@RequestParam(required = false) String username, @RequestParam(required = false) String password,
                                           HttpServletResponse response) throws URISyntaxException, IOException {

//        response.addHeader("Location", "http://localhost:3000/user/login");
        response.sendRedirect("/user/login");
        System.out.println("login 1");

        return null;
    }

//    @RequestMapping(value="/user/fail", method = RequestMethod.GET)
//    public ResponseEntity<Object> login_fail() throws URISyntaxException {
//        System.out.println("login fail");
//        return ResponseEntity.accepted().headers(HttpHeaders.EMPTY).body("login failed");
//    }
//
//    @RequestMapping(value="/user/success", method = RequestMethod.GET)
//    public ResponseEntity<Object> success(@RequestParam(required = false) String username, @RequestParam(required = false) String password) throws URISyntaxException {
//        System.out.println("success");
//        return null;
//    }

    @Autowired
    private JwtProvider jwtProvider;

    @RequestMapping(value="/user/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody User user) throws URISyntaxException {
        //201 - ok()   202 - accept()
        log.debug("login: {}", user);
        User loginedUser = userService.login(user.getUsername(), user.getPassword());
        String result;
        HttpStatus httpStatus;
        try
        {
            if (loginedUser!= null)
            {
                result = jwtProvider.generateTokenLogin(loginedUser.getUsername());
                loginedUser.setToken("Bearer "+ result);
                return ResponseEntity.ok().headers(HttpHeaders.EMPTY).body(loginedUser);
            } else
                throw new Exception("Login fail");
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("login fail");
        } catch (Exception ex) {
            result = "Server Error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(result, httpStatus);
    }

    @RequestMapping(value="/user/autologin", method = RequestMethod.POST)
    public ResponseEntity<Object> autologin() throws URISyntaxException {
        log.debug("autologin");
        User loginedUser = userService.getLoginedUser();
        if (loginedUser!= null)
            return ResponseEntity.ok().headers(HttpHeaders.EMPTY).body(loginedUser);
        else
            return ResponseEntity.ok().headers(HeaderUtil.createNotAuthenHeader()).body(null);
    }

    @RequestMapping(value="/user/sign_up", method = RequestMethod.POST)
    public ResponseEntity<Object> createAccount(@RequestBody User user) throws URISyntaxException {
        log.debug("sign_up: {}", user);
        if(user== null)
            return ResponseEntity.badRequest().headers(HttpHeaders.EMPTY).body("user can not be null");

        User foundUser = userService.findUserByUsername(user.getUsername());
        if (foundUser != null)
            return ResponseEntity.badRequest().headers(HttpHeaders.EMPTY).body("username existed");

        User createdUser = userService.createUser(user);
        if(createdUser!=null)
            return ResponseEntity.ok().headers(HttpHeaders.EMPTY).body(createdUser);

        return ResponseEntity.badRequest().headers(HttpHeaders.EMPTY).body("no user created");
    }

    @RequestMapping(value="/user/change_password", method = RequestMethod.POST)
    public ResponseEntity<Object> changePassword(@RequestBody String new_password) throws URISyntaxException {
        log.debug("change Password: {}", new_password);

        boolean isSuccess = userService.changePassword(new_password);
        if(isSuccess)
            return ResponseEntity.created(new URI("res/")).headers(HttpHeaders.EMPTY).body("change password successfully");
//        if(user== null)
//            return ResponseEntity.created(new URI("res/")).headers(HttpHeaders.EMPTY).body("user can not be null");
//
//        User foundUser = userService.findUserByUsername(user.getUsername());
//        if (foundUser != null)
//            return ResponseEntity.created(new URI("res/")).headers(HttpHeaders.EMPTY).body("username existed");
//
//        User createdUser = userService.createUser(user);
//        if(createdUser!=null)
//            return ResponseEntity.created(new URI("res/")).headers(HttpHeaders.EMPTY).body(createdUser);

        return ResponseEntity.created(new URI("res/")).headers(HttpHeaders.EMPTY).body("password not changed");
    }


}