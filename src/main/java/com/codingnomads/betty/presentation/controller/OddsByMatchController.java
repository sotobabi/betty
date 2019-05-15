package com.codingnomads.betty.presentation.controller;

import com.codingnomads.betty.data.models.MatchOdds;
import com.codingnomads.betty.logic.services.MatchOddsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OddsByMatchController {

    private MatchOddsService matchOddsService;

    @Autowired
    public OddsByMatchController(MatchOddsService matchOddsService) {
        this.matchOddsService = matchOddsService;
    }

    @GetMapping("/odds-by-match")
    public String getOddsByMatch(Model model) {
        List<MatchOdds> mostCurrentOddsAndMatches = matchOddsService.findMostRecentMatchesAndOdds();
        model.addAttribute("mostCurrentOddsAndMatches", mostCurrentOddsAndMatches);
        return "odds-by-match";
    }

}
