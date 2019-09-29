package com.app.law.service.impl;

import com.app.law.constant.RoleConstant;
import com.app.law.dto.user.UserDto;
import com.app.law.entity.User;
import com.app.law.entity.mapper.UserMapper;
import com.app.law.mapper.UserMapperCustom;
import com.app.law.repository.UserRepository;
import com.app.law.service.IUserService;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

//    @Autowired
//    private AuthenticationManager authenManager;

    @Override
    public User createUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User createdUser = null;
        try {
            createdUser = userRepo.saveAndFlush(user);
            logger.info("create user success");
        } catch(Exception ex) {
            logger.info("create user fail: {}", ex.getMessage());
        }
        return createdUser;
    }

    @Override
    public UserDto createUser2(UserDto dto) {
        User user = UserMapperCustom.dtoToEntity(dto);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        try {
            User createdUser = userRepo.saveAndFlush(user);
            logger.info("create user success");
            return UserMapperCustom.entityToDto(createdUser);
        } catch(Exception ex) {
            logger.info("create user fail: {}", ex.getMessage());
        }
        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = UserMapperCustom.dtoToEntity(userDto);
        try {
            User createdUser = userRepo.saveAndFlush(user);
            logger.info("update user success");
            return UserMapperCustom.entityToDto(createdUser);
        } catch(Exception ex) {
            logger.info("update user fail: {}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<UserDto> getAllLawer() {
        return userRepo.findAll()
        .stream()
        .filter(user ->user.getRoleId().equals(RoleConstant.LUAT_SU) || user.getRoleId().equals(RoleConstant.TRO_LY_LUAT_SU))
        .map(UserMapperCustom::entityToDto)
        .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getLawerByType(Integer type) {
        return userRepo.findByType(type)
            .stream()
            .map(UserMapperCustom::entityToDto)
            .collect(Collectors.toList());
    }

    @Override
    public UserDto login(String email, String raw_password) {
        User user = userRepo.findOneByEmail(email);
        return user!= null && passwordEncoder.matches(raw_password, user.getPassword()) ?
                userMapper.toDTO(user) : null;
    }

    @Override
    public User getLoginedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if(principal instanceof UserDetails) {
            UserDetails objUserDetail = (UserDetails) principal;
            String email = objUserDetail.getUsername();
            return this.findUserByEmail(email);
        }

        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        //gọi khi tạo user, xem user name và email có chưa
        if(email!=null && !email.isEmpty())
            return userRepo.findOneByEmail(email);

        return null;
    }

    @Override
    public User findUserById(Integer userId) {
        return userRepo.getOne(userId);
    }

    @Override
    public boolean changePassword(String newPassword) {
        User loginedUser = getLoginedUser();
        String encodedPassword = passwordEncoder.encode(newPassword);
        loginedUser.setPassword(encodedPassword);
        return userRepo.save(loginedUser)!= null;
    }

    @Override
    public List<UserDto> getAll() {
        return userRepo.findAll().stream()
            .map(UserMapperCustom::entityToDto)
            .collect(Collectors.toList());
    }



//    @Autowired
//    private UserDetailsService userDetailsService;

//    @Override
//    public String findLoggedInEmail() {
//        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
//        if (userDetails instanceof UserDetails) {
//            return ((UserDetails)userDetails).getUsername();
//        }
//
//        return null;
//    }
//
//    @Override
//    public void autologin(String email, String password) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//
//        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//
//        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
//            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            logger.debug(String.format("Auto login %s successfully!", email));
//        }
//    }

}