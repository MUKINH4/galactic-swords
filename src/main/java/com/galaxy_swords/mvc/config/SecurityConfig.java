package com.galaxy_swords.mvc.config;

import com.galaxy_swords.mvc.auth.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/", "/login/**", "/register/**", "/error", "/.well-known/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .oauth2Login(oauth ->
                        oauth
                            .loginPage("/login")
                            .defaultSuccessUrl("/swords")
                            .userInfoEndpoint(userInfo -> 
                                userInfo.userService(customOAuth2UserService)
                            )
                            .permitAll()
                )
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login"))
                .build();
    }

}
