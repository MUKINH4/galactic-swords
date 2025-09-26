package com.galaxy_swords.mvc.auth;

import com.galaxy_swords.mvc.service.AdventurerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final AdventurerService adventurerService;

    public CustomOAuth2UserService(AdventurerService adventurerService) {
        this.adventurerService = adventurerService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        
        try {
            // Salvar o usuário no banco de dados
            adventurerService.save(oAuth2User);
            log.info("Usuário OAuth2 processado e salvo: {}", (String) oAuth2User.getAttribute("email"));
        } catch (Exception e) {
            log.error("Erro ao salvar usuário OAuth2: {}", e.getMessage(), e);
            // Não interrompe o processo de login se falhar ao salvar
        }
        
        return oAuth2User;
    }
}