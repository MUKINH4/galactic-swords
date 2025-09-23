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

    public Adventurer save(OAuth2User principal) {
        String email = principal.getAttribute("email");
        if (email == null || email.isEmpty()) {
            logger.error("OAuth2User principal does not contain an email attribute.");
            return null;
        }
        Adventurer adventurer = adventurerRepository.findByEmail(email);
        if (adventurer == null) {
            adventurer = new Adventurer(principal);
            adventurerRepository.save(adventurer);
            logger.info("Novo Adventurer salvo: {}", email);
        } else {
            logger.info("Adventurer já existe: {}", email);
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
