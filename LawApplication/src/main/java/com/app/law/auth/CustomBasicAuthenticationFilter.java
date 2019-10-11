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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    private UserDetailsService userDetailsService;

    public CustomBasicAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authToken = request.getHeader(TOKEN_HEADER);
        authToken = resolveToken(authToken);
        if(jwtProvider.validateTokenLogin(authToken))
        {
            String username = jwtProvider.getUsernameFromToken(authToken);
            try {
                UserDetails userDetail = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
            } catch(UsernameNotFoundException ex) {
                log.debug("user name not found: {}", ex.getMessage());
            }
        }
        super.doFilterInternal(request, response, chain);
//        chain.doFilter(request, response);
    }

    private String resolveToken(String token){
        return StringUtils.hasText(token) && token.startsWith("Bearer ") ?
            token.substring(7) : null;
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
