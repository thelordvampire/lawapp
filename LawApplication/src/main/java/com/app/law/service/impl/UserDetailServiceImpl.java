package com.app.law.service.impl;

import com.app.law.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private IUserService IUserService;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug(this.getClass().getCanonicalName()+ " load user by username: {}", username);
        com.app.law.entity.User user = IUserService.findUserByUsername(username);
        if(user == null || user.getUsername() == null){
            throw new UsernameNotFoundException("User not authorized.");
        }

//        đoạn này chưa làm
//        List<GrantedAuthority> grantList = new ArrayList<>();
//        if (roleNames != null) {
//            for (String role : roleNames) {
//                // ROLE_USER, ROLE_ADMIN,..
//                GrantedAuthority authority = new SimpleGrantedAuthority(role);
//                grantList.add(authority);
//            }
//        }
////////////////////////////////////////////////////////////////////////////////////
        List<GrantedAuthority> grantList = new ArrayList<>();

        UserDetails userDetails = new User(user.getUsername(),user.getPassword(), grantList);
//        Authentication request = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
//        SecurityContextHolder.getContext().setAuthentication(request);
        return userDetails;
    }

}