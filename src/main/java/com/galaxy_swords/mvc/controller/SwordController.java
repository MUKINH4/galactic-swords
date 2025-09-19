package com.galaxy_swords.mvc.controller;

import com.galaxy_swords.mvc.model.Sword;
import com.galaxy_swords.mvc.service.SwordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/swords")
public class SwordController {

    private final SwordService swordService;

    public SwordController(SwordService swordService) {
        this.swordService = swordService;
    }

    @GetMapping
    public String index(Model model) {
        List<Sword> swords = swordService.listarEspadas();
        model.addAttribute("swords", swords);
        return "swords";
    }


}
