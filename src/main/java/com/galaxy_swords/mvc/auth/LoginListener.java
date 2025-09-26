package com.galaxy_swords.mvc.auth;

import com.galaxy_swords.mvc.service.AdventurerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoginListener implements ApplicationListener<AuthenticationSuccessEvent> {
    
    private final AdventurerService adventurerService;

    public LoginListener(AdventurerService adventurerService) {
        this.adventurerService = adventurerService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        try {
            log.info("Evento de autenticação bem-sucedida capturado: {}", event.getAuthentication().getClass().getSimpleName());
            
            if (event.getAuthentication() instanceof OAuth2AuthenticationToken) {
                OAuth2User principal = (OAuth2User) event.getAuthentication().getPrincipal();
                log.info("Processando login OAuth2 para usuário: {}", (String) principal.getAttribute("email"));
                adventurerService.save(principal);
            } else {
                log.warn("Tipo de autenticação não suportado: {}", event.getAuthentication().getClass());
            }
        } catch (Exception e) {
            log.error("Erro ao processar evento de login: {}", e.getMessage(), e);
        }
    }
}