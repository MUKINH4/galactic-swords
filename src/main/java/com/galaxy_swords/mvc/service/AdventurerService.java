package com.galaxy_swords.mvc.service;

import com.galaxy_swords.mvc.model.Adventurer;
import com.galaxy_swords.mvc.model.Sword;
import com.galaxy_swords.mvc.repository.AdventurerRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class AdventurerService {

    private final AdventurerRepository adventurerRepository;
    private static final Logger logger = LoggerFactory.getLogger(AdventurerService.class);

    public AdventurerService(AdventurerRepository adventurerRepository) {
        this.adventurerRepository = adventurerRepository;
    }

    @Transactional
    public Adventurer save(OAuth2User principal) {
        String email = principal.getAttribute("email");
        String name = principal.getAttribute("name");
        String picture = principal.getAttribute("picture");
        
        logger.info("Tentando salvar usuário OAuth2 - Email: {}, Nome: {}", email, name);
        
        if (email == null || email.isEmpty()) {
            logger.error("OAuth2User principal não contém um atributo email válido.");
            return null;
        }
        
        Adventurer adventurer = adventurerRepository.findByEmail(email);
        if (adventurer == null) {
            adventurer = new Adventurer(principal);
            adventurer = adventurerRepository.save(adventurer);
            logger.info("Novo Adventurer criado e salvo com sucesso - ID: {}, Email: {}, Nome: {}", 
                       adventurer.getId(), email, name);
        } else {
            // Atualizar informações se necessário
            boolean updated = false;
            if (name != null && !name.equals(adventurer.getNome())) {
                adventurer.setNome(name);
                updated = true;
            }
            if (picture != null && !picture.equals(adventurer.getPicture())) {
                adventurer.setPicture(picture);
                updated = true;
            }
            
            if (updated) {
                adventurer = adventurerRepository.save(adventurer);
                logger.info("Adventurer atualizado - Email: {}", email);
            } else {
                logger.info("Adventurer já existe e está atualizado - Email: {}", email);
            }
        }
        return adventurer;
    }

    public Adventurer findByEmail(String email) {
        return adventurerRepository.findByEmail(email);
    }

    @Transactional
    public Adventurer save(Adventurer adventurer) {
        return adventurerRepository.save(adventurer);
    }

    @Transactional
    public void addGold(double amount, String email) {
        Adventurer adventurer = findByEmail(email);
        if (adventurer != null) {
            adventurer.setGold(adventurer.getGold() + amount);
            adventurerRepository.save(adventurer);
            logger.info("Adicionado {} moedas ao Adventurer: {}", amount, email);
        } else {
            logger.error("Adventurer não encontrado para o email: {}", email);
        }
    }

    public List<Sword> getSwords(String email) {
        Adventurer adventurer = findByEmail(email);
        return adventurer != null ? adventurer.getSwords() : Collections.emptyList();
    }

}
