package com.galaxy_swords.mvc.controller;

import com.galaxy_swords.mvc.model.Adventurer;
import com.galaxy_swords.mvc.model.Sword;
import com.galaxy_swords.mvc.service.AdventurerService;
import com.galaxy_swords.mvc.service.SwordService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/swords")
public class SwordController {

    private final SwordService swordService;
    private final AdventurerService adventurerService;

    public SwordController(SwordService swordService, AdventurerService adventurerService) {
        this.adventurerService = adventurerService;
        this.swordService = swordService;
    }

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal OAuth2User principal) {
        Adventurer adventurer = adventurerService.findByEmail(principal.getAttribute("email"));
        model.addAttribute("adventurer", adventurer);
        List<Sword> swords = swordService.listarEspadas();
        model.addAttribute("swords", swords);
        return "swords";
    }

    @PostMapping("/buy")
    public String buySword(Long swordId, RedirectAttributes redirect, @AuthenticationPrincipal OAuth2User principal) {
        try {
            swordService.buySword(swordId, principal.getAttribute("email"));
            redirect.addFlashAttribute("successMessage", "Espada comprada com sucesso!");
        } catch (IllegalStateException e) {
            redirect.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/swords";
    }


}
