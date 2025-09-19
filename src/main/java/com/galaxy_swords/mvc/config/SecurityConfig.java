package com.galaxy_swords.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/login/**", "/register/**");
                    auth.anyRequest().authenticated();
                })
                .oauth2Login(oauth ->
                        oauth
                            .loginPage("/login").defaultSuccessUrl("/swords").permitAll()
                )
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login"))
                .build();
    }

}
