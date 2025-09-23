package com.galaxy_swords.mvc.auth;

import com.galaxy_swords.mvc.service.AdventurerService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class LoginListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private final AdventurerService adventurerService;

    public LoginListener(AdventurerService adventurerService) {
        this.adventurerService = adventurerService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        adventurerService.save((OAuth2User) event.getAuthentication().getPrincipal());
    }
}