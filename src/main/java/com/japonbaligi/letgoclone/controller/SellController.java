package com.japonbaligi.letgoclone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SellController {

    @GetMapping("/ilan-ver")
    public String sellPage(Model model) {
        model.addAttribute("title", "İlan Ver - Takı Kazan");
        return "sell";
    }
}
