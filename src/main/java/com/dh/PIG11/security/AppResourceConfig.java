package com.dh.PIG11.security;

import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class AppResourceConfig {
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        http.csrf().csrfTokenRepository(new CookieCsrfTokenRepository())
                .requireCsrfProtectionMatcher(
                        HttpServletRequest-> HttpServletRequest.getMethod().equals("None")
                );
        return http.build();
    }
}
