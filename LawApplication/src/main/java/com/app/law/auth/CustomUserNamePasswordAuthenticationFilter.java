/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.law.auth;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.savemycode.entity.User;
// import java.io.IOException;
// import java.util.logging.Level;
// import java.util.logging.Logger;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import org.apache.commons.io.IOUtils;
// import org.slf4j.LoggerFactory;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author bao
 */
public class CustomUserNamePasswordAuthenticationFilter {
//        extends UsernamePasswordAuthenticationFilter {

//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    private final org.slf4j.Logger log = LoggerFactory.getLogger(CustomUserNamePasswordAuthenticationFilter.class);
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        String requestBody;
//        log.debug(CustomUserNamePasswordAuthenticationFilter.class.getCanonicalName());
//        try {
//            requestBody = IOUtils.toString(request.getReader());
//            log.debug(requestBody);
//            User authRequest = objectMapper.readValue(requestBody, User.class);
//
//            UsernamePasswordAuthenticationToken token
//                = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
//            setDetails(request, token);
//
//            return this.getAuthenticationManager().authenticate(token);
//        } catch (IOException ex) {
//            log.debug("error: {}", ex.getMessage());
//            Logger.getLogger(CustomUserNamePasswordAuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return null;
//    }
}
