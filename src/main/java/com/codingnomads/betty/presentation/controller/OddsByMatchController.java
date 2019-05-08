package com.codingnomads.betty.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OddsByMatchController {

    @GetMapping("/odds-by-match")
    public String getOddsByMatch() {
        return "odds-by-match";
    }

}
