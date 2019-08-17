package com.app.law.service.impl;

import com.app.law.entity.User;
import com.app.law.repository.UserRepository;
import com.app.law.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

//    @Autowired
//    private AuthenticationManager authenManager;

    @Override
    public User createUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepo.save(user);
    }

    @Override
    public User login(String username, String raw_password) {
        User user = userRepo.findOneByUsername(username);
        if(user!= null && passwordEncoder.matches(raw_password, user.getPassword()))
            return user;

        return null;
    }

    @Override
    public User getLoginedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if(principal instanceof UserDetails) {
            UserDetails objUserDetail = (UserDetails) principal;
            String username = objUserDetail.getUsername();
            return findUserByUsername(username);
        }

        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        //gọi khi tạo user, xem user name và email có chưa
        if(username!=null && !username.isEmpty())
            return userRepo.findOneByUsername(username);

        return null;
    }

    @Override
    public User updateUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean changePassword(String newPassword) {
        User loginedUser = getLoginedUser();
        String encodedPassword = passwordEncoder.encode(newPassword);
        loginedUser.setPassword(encodedPassword);
        return userRepo.save(loginedUser)!= null;
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