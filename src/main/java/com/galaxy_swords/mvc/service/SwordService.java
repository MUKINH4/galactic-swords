package com.galaxy_swords.mvc.service;

import com.galaxy_swords.mvc.model.Adventurer;
import com.galaxy_swords.mvc.model.Sword;
import com.galaxy_swords.mvc.repository.SwordRepository;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SwordService {

    private final SwordRepository swordRepository;
    private final AdventurerService adventurerService;
    private static final Logger logger = LoggerFactory.getLogger(SwordService.class);

    public SwordService(SwordRepository swordRepository, AdventurerService adventurerService) {
        this.adventurerService = adventurerService;
        this.swordRepository = swordRepository;
    }

    @Transactional(readOnly = true)
    public List<Sword> listarEspadas() {
        return swordRepository.findAll();
    }


    @Transactional
    public void buySword(Long swordId, String buyerEmail) {
        Sword sword = swordRepository.findById(swordId)
                .orElseThrow(() -> new IllegalArgumentException("Espada não encontrada: id=" + swordId));
        Adventurer adventurer = adventurerService.findByEmail(buyerEmail);
        if (adventurer == null) {
            throw new IllegalArgumentException("Adventurer não encontrado: email=" + buyerEmail);
        }
        processPurchase(sword, adventurer);
    }

    private void processPurchase(Sword sword, Adventurer adventurer) {
        if (sword.getOwner() != null) {
            throw new IllegalStateException("Esta espada já foi vendida.");
        }
        double price = sword.getPrice();
        if (adventurer.getGold() < price) {
            throw new IllegalStateException("Gold insuficiente para comprar esta espada.");
        }
        sword.setOwner(adventurer);
        if (adventurer.getSwords() == null) {
            adventurer.setSwords(new ArrayList<>());
        }
        adventurer.getSwords().add(sword);
        adventurer.setGold(adventurer.getGold() - price);
        swordRepository.save(sword);
        adventurerService.save(adventurer);
        logger.info("{} comprou a espada '{}' por {} gold.", adventurer.getEmail(), sword.getName(), price);
    }
}
