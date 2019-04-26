package com.codingnomads.betty.presentation.controller;

import com.codingnomads.betty.logic.models.TeamProbabilityToWin;
import com.codingnomads.betty.logic.services.AnalyzeTweetsService;
import com.codingnomads.betty.presentation.webmodel.InputTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Autowired
    private AnalyzeTweetsService analyzeTweetsService;

    @GetMapping("/getOdds")
    public String estimateTeamOdds(@RequestParam(name = "teamName", required = false) String teamName, Model model) {
        model.addAttribute("inputTeam", new InputTeam());
        model.addAttribute("teamProbabilityToWin",new TeamProbabilityToWin());
        model.addAttribute("teamProbabilityToWin", analyzeTweetsService.calculateProbabilityWithTweets(teamName));
        return "/getOdds";
    }
}
