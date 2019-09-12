package com.app.law.service.impl;

import com.app.law.entity.User_Privilege;
import com.app.law.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


public class UserDetailServiceImpl implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private IUserService userService;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.debug(this.getClass().getCanonicalName()+ " load user by email: {}", email);
        com.app.law.entity.User user = userService.findUserByEmail(email);
        if(user == null)
            throw new UsernameNotFoundException("User not authorized.");

        List<GrantedAuthority> grantList = new ArrayList<>();
        for (User_Privilege user_privilege : user.getListUserPrivilege()) {
            //add privilege
            GrantedAuthority authority = new SimpleGrantedAuthority(user_privilege.getPrivilege().getName());
            grantList.add(authority);
        }
////////////////////////////////////////////////////////////////////////////////////
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        UserDetails userDetails = new User(user.getEmail(),user.getPassword(), enabled,
            accountNonExpired, credentialsNonExpired, accountNonLocked, grantList);
//        Authentication request = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
//        SecurityContextHolder.getContext().setAuthentication(request);
        return userDetails;
    }

}