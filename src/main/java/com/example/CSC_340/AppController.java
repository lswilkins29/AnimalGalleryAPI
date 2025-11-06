package com.example.CSC_340;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping({"", "/", "/home"})
    public String redirectToCats() {
        return "redirect:/cats";
    }
}