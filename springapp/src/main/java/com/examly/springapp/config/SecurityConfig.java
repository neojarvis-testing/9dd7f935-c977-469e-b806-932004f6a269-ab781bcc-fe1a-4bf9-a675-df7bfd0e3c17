
package com.examly.springapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
@Autowired
JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
@Autowired
UserDetailsService userDetailsService;
@Autowired
PasswordEncoder encoder;
@Autowired
JwtAuthenticationFilter jwtAuthenticationFilter;
@Autowired
public void configure(AuthenticationManagerBuilder authority)throws Exception{
    authority.userDetailsService(userDetailsService).passwordEncoder(encoder);
}
@Bean
public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
    return http.getSharedObject(AuthenticationManagerBuilder.class)
    .userDetailsService(userDetailsService)
    .passwordEncoder(encoder)
    .and()
    .build();
}
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    http.cors(cors->cors.disable())
    .csrf(csrf->csrf.disable())
    .authorizeHttpRequests(auth->auth
    .requestMatchers("/api/register","/api/login","/api/registers","/api/logins").permitAll()
    .requestMatchers(HttpMethod.GET,"/api/products/{productId}","/api/products/category/{category}","/api/products").hasAnyRole("ADMIN","USER")
    .requestMatchers(HttpMethod.GET,"/api/user","/api/feedback","/api/orders").hasRole("ADMIN")
    .requestMatchers(HttpMethod.PUT,"/api/orders/{orderId}","/api/products/{productId}").hasRole("ADMIN")
    .requestMatchers(HttpMethod.POST,"/api/products").hasRole("ADMIN")
    .requestMatchers(HttpMethod.DELETE,"/api/user/{userId}","/api/orders/{orderId}","/api/products/{productId}").hasRole("ADMIN")
    .requestMatchers(HttpMethod.GET,"/api/orders/user/{userId}","/api/orders/{orderId}","/api/feedback/user/{userId}","/api/products/user/{userId}").hasRole("USER")
    .requestMatchers(HttpMethod.POST,"/api/feedback","/api/orders").hasRole("USER")
    .requestMatchers(HttpMethod.DELETE,"/api/feedback/{id}").hasRole("USER")
    .anyRequest().permitAll())
    .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); 
    return http.build();
    }

}
