package com.app.law.config;

import com.app.law.auth.CustomBasicAuthenticationFilter;
import com.app.law.constant.Enviroment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailService;

    // Enable jdbc authentication
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//            auth.jdbcAuthentication().dataSource(dataSource).
//                    passwordEncoder(bCryptPasswordEncoder()).and().
//                    userDetailsService(userDetailService);

//                    auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder());

//                    auth.authenticationProvider(getAuthenticationProvider());
///////////////////////////////////
//                    auth.inMemoryAuthentication().and().authenticationProvider(getAuthenticationProvider());
        auth.authenticationProvider(getAuthenticationProvider());
    }

    ///////////////////////////////////////////////////////
    @Bean
    public DaoAuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Bean
//    public SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter() {
//      return new SecurityContextHolderAwareRequestFilter();
//    }

//     @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//        configuration.addExposedHeader("Set-Cookie");
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }


        //    corsFilter
        @Bean
        public FilterRegistrationBean setCors() {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowCredentials(true);
            config.addExposedHeader("Set-Cookie");
            config.addExposedHeader("xsrf-token");
//            if(Enviroment.cors != null) {
//                Enviroment.cors.forEach(config::addAllowedOrigin);
//            }
            config.addAllowedOrigin("*");

            config.setAllowedMethods(Arrays.asList("POST", "OPTIONS", "GET", "DELETE", "PUT"));
            config.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization", "xsrf-token", "x-csrf-token", "credentials"));
            config.setMaxAge(8000L);
            source.registerCorsConfiguration("/**", config);

//            new FilterRegistrationBean<>(new org.springframework.web.filter.CorsFilter((CorsConfigurationSource) source));
            FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
            bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
            return bean;
        }

//    @Bean
//    public CustomUserNamePasswordAuthenticationFilter authenticationFilter() throws Exception {
//        CustomUserNamePasswordAuthenticationFilter authenticationFilter
//            = new CustomUserNamePasswordAuthenticationFilter();
//        authenticationFilter.setAuthenticationSuccessHandler(this::loginSuccessHandler);
////        authenticationFilter.setAuthenticationFailureHandler(this::loginFailureHandler);
//        authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/user/login", "POST"));
//        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
//        return authenticationFilter;
//    }

        @Bean
        public CustomBasicAuthenticationFilter authenticationFilter() throws Exception {
            return new CustomBasicAuthenticationFilter(authenticationManagerBean());
        }

        private final ObjectMapper objectMapper = new ObjectMapper();

        private void loginSuccessHandler(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException
        {
            response.setStatus(HttpStatus.OK.value());
            objectMapper.writeValue(response.getWriter(), "Yayy you logged in!");
        }

//    @Autowired
//    private ServletContext servletContext;

//    @Bean
//    public LocaleResolver localeResolver(){
//        CookieLocaleResolver resolver = new CookieLocaleResolver();
//        resolver.setDefaultLocale(new Locale("en"));
//        resolver.setCookieName("locale");
//        resolver.setCookiePath(servletContext.getContextPath());
//        resolver.setCookieMaxAge(31536000);
//        return resolver;
//    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/user/login");
//    }



        @Override
        protected void configure(HttpSecurity http) throws Exception {
//         http.csrf().disable().authorizeRequests()
//                .anyRequest().authenticated()
//                .and().httpBasic();

            http
                .csrf()
                .disable()
//            .ignoringAntMatchers("/user/login")
//            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//            .and()
                    .cors().and()
//                    .authorizeRequests()
//                    .antMatchers("/user/login", "/user/sign_up")
//                    .permitAll()
//                    .anyRequest()
//                    .authenticated()
//                    .and()
                    .addFilterBefore(authenticationFilter(), CustomBasicAuthenticationFilter.class);
//                .formLogin()
////                .loginPage("/user/login")
//                .loginProcessingUrl("/user/login")
//                .failureForwardUrl("/user/fail")
//                .defaultSuccessUrl("/user/success", true)
//                .permitAll();
            //////////////////////////////////////////////////////////////////////

            http.sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);



//             .ignoringAntMatchers("/order/refresh", "/order/add", "/order/delete", "/order/update",
//                     "/order/next", "/order/pre","/order/month/next","/order/month/pre",
//                     "/dish", "/order/rate",
//                     "/admin/menu/thongke/month",
//                     "/order/changePaid");//quan trọng khi sử dụng ajax
//            http
//                .authorizeRequests()
//                    .antMatchers("/resources/**","/js/**","/thai/**").permitAll()
////                    .antMatchers("").hasAnyRole("Admin","Emp")
//                    .antMatchers("/admin/**","/menu/**").hasAuthority("Admin")
//                    .anyRequest().authenticated()
//                    .and()
//                .formLogin()
//                    .loginPage("/user/login")
//                    .defaultSuccessUrl("/home.html", true)
//                    .permitAll()
//                    .and()
//                .logout()
////                    .logoutUrl("/user/logout")
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
//                    .logoutSuccessUrl("/")
//                    .invalidateHttpSession(true)
//                    .permitAll();
        }
    }
