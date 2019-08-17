/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.law.auth;

import com.app.law.entity.User;
import com.app.law.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author bao
 */
public class CustomBasicAuthenticationFilter extends BasicAuthenticationFilter {
    
    private final Logger log = LoggerFactory.getLogger(CustomBasicAuthenticationFilter.class);
    private final static String TOKEN_HEADER = "authorization";
    
    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private IUserService IUserService;

    public CustomBasicAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
    
    private String resolveToken(String token){
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7, token.length());
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authToken = request.getHeader(TOKEN_HEADER);
        authToken = resolveToken(authToken);
        if(jwtProvider.validateTokenLogin(authToken)) 
        {
            String username = jwtProvider.getUsernameFromToken(authToken);
            User user = IUserService.findUserByUsername(username);
            if (user != null) {
                boolean enabled = true;
                boolean accountNonExpired = true;
                boolean credentialsNonExpired = true;
                boolean accountNonLocked = true;
                UserDetails userDetail = new org.springframework.security.core.userdetails.User(
                        username, user.getPassword(), enabled, accountNonExpired,credentialsNonExpired, accountNonLocked, new ArrayList<>());

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        super.doFilterInternal(request, response, chain);
//        chain.doFilter(request, response);
    }

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        System.out.println("end");
    }

    @Override
    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        System.out.println("unsuccess");
    }
    
    
    
    
    
    
}
