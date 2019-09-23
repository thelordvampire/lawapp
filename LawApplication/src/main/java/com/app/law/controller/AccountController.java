package com.app.law.controller;

import com.app.law.auth.JwtProvider;
import com.app.law.dto.user.UserDto;
import com.app.law.entity.User;
import com.app.law.mapper.UserMapperCustom;
import com.app.law.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class AccountController {

    private final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private IUserService userService;

//    @RequestMapping(value="/user/login", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
//    public ResponseEntity<Object> logina(@RequestParam(required = false) String username, @RequestParam(required = false) String password) throws URISyntaxException {
//        log.info("login: {}, {}", username, password);
//
//        return ResponseEntity.accepted().headers(HttpHeaders.EMPTY).body("no logined user found");
//    }

//    @RequestMapping(value="/user/login1", method = RequestMethod.GET)
//    public ResponseEntity<Object> login111(@RequestParam(required = false) String username, @RequestParam(required = false) String password,
//                                           HttpServletResponse response) throws URISyntaxException, IOException {
//
////        response.addHeader("Location", "http://localhost:3000/user/login");
//        response.sendRedirect("/user/login");
//        System.out.println("login 1");
//
//        return null;
//    }

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
    public ResponseEntity<Object> login(@RequestBody User user) {
        //201 - ok()   202 - accept()
        log.info("login: {}", user);
        UserDto loginedUser = userService.login(user.getEmail(), user.getPassword());
        String result = "Login failed";
        try {
            if (loginedUser!= null) {
                String token = jwtProvider.generateTokenLogin(loginedUser.getEmail());
                loginedUser.setToken("Bearer "+ token);
                return ResponseEntity.ok().body(loginedUser);
            }
        } catch (Exception ex) {
            log.info("Login failed: {}", ex.getMessage());
            result = "Server Error";
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

    @RequestMapping(value="/user/autologin", method = RequestMethod.POST)
    public ResponseEntity<Object> autologin() {
        log.info("autologin");
        User loginedUser = userService.getLoginedUser();
        return ResponseEntity.status(loginedUser != null ?
            HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(loginedUser);
    }

//    @RequestMapping(value="/user/create", method = RequestMethod.POST)
//    public ResponseEntity<Object> createAccount(@RequestBody User user) {
//        log.info("create user: {}", user);
//        HttpStatus status = HttpStatus.BAD_REQUEST;
//        String message = "no user created";
//
//        if(user== null) {
//            message = "user can not be null";
//        } else {
//            User foundUser = userService.findUserByEmail(user.getEmail());
//            if (foundUser != null)
//                return ResponseEntity.badRequest().body("username existed");
//
//            User createdUser = userService.createUser(user);
//            if(createdUser!=null)
//                return ResponseEntity.ok().body(createdUser);
//        }
//        return ResponseEntity.status(status).body(message);
//    }

    @RequestMapping(value="/user/create", method = RequestMethod.POST)
    public ResponseEntity<Object> createAccount(@RequestBody @Valid UserDto dto , BindingResult bindingResult) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "no user created";

        if(bindingResult.hasErrors()) {
            message = "user can not be null";
        } else {
            User foundUser = userService.findUserByEmail(dto.getEmail());
            if (foundUser != null)
                return ResponseEntity.badRequest().body("username existed");

            User createdUser = userService.createUser2(dto);
            if(createdUser!=null)
                return ResponseEntity.ok().body(createdUser);
        }
        return ResponseEntity.status(status).body(message);
    }

    @RequestMapping(value="/user/{userId}/image", method = RequestMethod.GET)
    public ResponseEntity<String> getImagebyUserId(@PathVariable Integer userId) {
        log.info("getImagebyUserId : {}", userId);

        User user = userService.findUserById(userId);
        if(user != null) {
            return ResponseEntity.ok().body(user.getImage());
        } else {
            log.info("getImagebyUserId : user not found");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value="/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
        log.info("getUserById : {}", userId);
        User user = userService.findUserById(userId);
        if(user != null) {
            UserDto userDto = UserMapperCustom.entityToDto(user);
            return ResponseEntity.ok().body(userDto);
        } else {
            log.info("getUserById : user not found");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @RequestMapping(value="/user/get-lawer", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getAllAvaiableLawer() {
        log.info("getAllAvaiableLawer ");
        List<UserDto> listLawer =  userService.getAllLawer();
        return ResponseEntity.status(HttpStatus.OK).body(listLawer);
    }

    @RequestMapping(value="/user/change_password", method = RequestMethod.POST)
    public ResponseEntity<String> changePassword(@RequestBody String new_password) throws URISyntaxException {
        log.info("change Password: {}", new_password);

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

        return ResponseEntity.created(new URI("res/")).body("password not changed");
    }


}