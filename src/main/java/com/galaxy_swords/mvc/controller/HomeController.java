package com.galaxy_swords.mvc.controller;

import com.galaxy_swords.mvc.model.Adventurer;
import com.galaxy_swords.mvc.service.AdventurerService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final AdventurerService adventurerService;

    public HomeController(AdventurerService adventurerService) {
        this.adventurerService = adventurerService;
    }

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            Adventurer adventurer = adventurerService.findByEmail(principal.getAttribute("email"));
            model.addAttribute("adventurer", adventurer);
        }
        return "index";
    }
}

