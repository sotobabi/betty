package com.codingnomads.betty.presentation.controller;

import com.codingnomads.betty.logic.models.TeamProbabilityToWin;
import com.codingnomads.betty.logic.services.AnalyzeTweetsService;
import com.codingnomads.betty.logic.services.TwitterService;
import com.codingnomads.betty.presentation.webmodel.InputTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    private AnalyzeTweetsService analyzeTweetsService;
    private TwitterService twitterService;

    @Autowired
    public SearchController(AnalyzeTweetsService analyzeTweetsService, TwitterService twitterService) {
        this.analyzeTweetsService = analyzeTweetsService;
        this.twitterService = twitterService;
    }

    @GetMapping("/getOdds")
    public String estimateTeamOdds(@RequestParam(name = "teamName", required = false) String teamName, Model model) {
        model.addAttribute("inputTeam", new InputTeam());
        model.addAttribute("teamProbabilityToWin", analyzeTweetsService.calculateProbabilityWithTweets(teamName)); //manually putting in keyword for now (PS. dog has 38% of winning, cats 40%)
        return "/getOdds";
    }

    @GetMapping("/api-to-database")
    public String makeApiCallAndSinkTweetsToRemoteDatabase() {
        twitterService.callApiAndSaveStatusesAsTweets("cat", 15);
        return "api-to-database";
    }

}
