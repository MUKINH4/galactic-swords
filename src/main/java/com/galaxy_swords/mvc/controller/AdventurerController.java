package com.galaxy_swords.mvc.controller;

import com.galaxy_swords.mvc.model.Adventurer;
import com.galaxy_swords.mvc.model.Sword;
import com.galaxy_swords.mvc.service.AdventurerService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/adventurer")
public class AdventurerController {

    private final AdventurerService adventurerService;

    public AdventurerController(AdventurerService adventurerService) {
        this.adventurerService = adventurerService;
    }

    @GetMapping
    public String adventurer(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            adventurerService.save(principal);
            Adventurer adventurer = adventurerService.findByEmail(principal.getAttribute("email"));
            model.addAttribute("adventurer", adventurer);
        }
        return "adventurer";
    }

    @GetMapping("/addGold")
    public String addGoldForm(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            Adventurer adventurer = adventurerService.findByEmail(principal.getAttribute("email"));
            model.addAttribute("adventurer", adventurer);
        }
        return "addGold";
    }

    @PostMapping("/addGold")
    public String addGold(double amount, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            adventurerService.addGold(amount, principal.getAttribute("email"));
        }
        return "redirect:/adventurer";
    }

    @GetMapping("/inventory")
    public String inventory(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            Adventurer adventurer = adventurerService.findByEmail(principal.getAttribute("email"));
            List<Sword> swords = adventurerService.getSwords(principal.getAttribute("email"));
            model.addAttribute("ownedSwords", swords);
            model.addAttribute("adventurer", adventurer);
        }
        return "inventory";
    }


}
