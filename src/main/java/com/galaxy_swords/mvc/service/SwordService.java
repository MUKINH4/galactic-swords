package com.galaxy_swords.mvc.service;

import com.galaxy_swords.mvc.model.Sword;
import com.galaxy_swords.mvc.repository.SwordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwordService {

    private final SwordRepository swordRepository;

    public SwordService(SwordRepository swordRepository) {
        this.swordRepository = swordRepository;
    }

    public List<Sword> listarEspadas() {
        return swordRepository.findAll();
    }

}
