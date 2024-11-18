//package com.workflow.engine.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/api/tasks/**").hasRole("USER")
//                                .requestMatchers("/admin/**").hasRole("ADMIN")
//                                .anyRequest().authenticated()
//                )
//                .formLogin(formLogin ->
//                        formLogin.permitAll()
//                )
//                .logout(logout ->
//                        logout.permitAll()
//                )
//                .exceptionHandling(exceptionHandling ->
//                        exceptionHandling.accessDeniedPage("/403")
//                ); // Added custom exception handling
//        return http.build();
//    }
//}