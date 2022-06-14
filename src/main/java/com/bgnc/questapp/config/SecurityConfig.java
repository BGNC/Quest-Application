package com.bgnc.questapp.config;

import com.bgnc.questapp.security.JwtAuthenticationEntryPoint;
import com.bgnc.questapp.security.JwtAuthenticationFilter;
import com.bgnc.questapp.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;

    private JwtAuthenticationEntryPoint handler;



    SecurityConfig(UserDetailsServiceImpl userDetailsService , JwtAuthenticationEntryPoint handler){
        this.userDetailsService=userDetailsService;
        this.handler=handler;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();

    }





}
