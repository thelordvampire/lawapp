package com.app.law.auth;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
public class CorsFilter {
//        implements Filter {
//
//    @Override
//    public void init(FilterConfig fc) throws ServletException {
//    }
//
//    @Override
//    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) sr;
//        HttpServletResponse response =  (HttpServletResponse) sr1;
////        System.out.println("111111111111111111: "+request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type Origin");
////        response.setHeader("Access-Control-Expose-Headers", "Set-Cookie");
////        response.setHeader("Access-Control-Expose-Headers", "xsrf-token");
//        fc.doFilter(request, response);
//
/////////////////////////////////////////////////////////////////////////////////////////////
//
////        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
////	        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
////	        response.setHeader("Access-Control-Max-Age", "3600");
////	        response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");
////	        response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
////	        if ("OPTIONS".equals(request.getMethod())) {
////	            response.setStatus(HttpServletResponse.SC_OK);
////	        } else {
////	            fc.doFilter(request, response);
////	        }
//    }
//
//    @Override
//    public void destroy() {
//    }

}

