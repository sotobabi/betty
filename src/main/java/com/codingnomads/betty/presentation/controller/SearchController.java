package com.codingnomads.betty.presentation.controller;

import com.codingnomads.betty.logic.services.AnalyzeTweetsService;
import com.codingnomads.betty.logic.services.SourceToResultPipelineService;
import com.codingnomads.betty.logic.services.ProcessTweetsThroughNlpService;
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
    private SourceToResultPipelineService sourceToResultPipelineService;
    private ProcessTweetsThroughNlpService processTweetsThroughNlpService;

    @Autowired
    public SearchController(AnalyzeTweetsService analyzeTweetsService, TwitterService twitterService, SourceToResultPipelineService sourceToResultPipelineService) {
        this.analyzeTweetsService = analyzeTweetsService;
        this.twitterService = twitterService;
        this.sourceToResultPipelineService = sourceToResultPipelineService;
        this.processTweetsThroughNlpService = processTweetsThroughNlpService;
    }

    @GetMapping("/calculate-odds")
    public String estimateTeamOdds(@RequestParam(name = "teamName", required = false) String teamName, Model model) {
        model.addAttribute("inputTeam", new InputTeam());
        model.addAttribute("teamProbabilityToWin", analyzeTweetsService.calculateProbabilityWithTweets(teamName));
        return "/calculateOdds";
    }

    @GetMapping("/display-odds")
    public String displayTeamOdds(@RequestParam(name = "teamName", required = false) String teamName, Model model){
        model.addAttribute("inputTeam", new InputTeam());
        return "/displayOdds";
    }

    @GetMapping("/api-to-database")
    public String makeApiCallAndSinkTweetsToRemoteDatabase() {
        twitterService.callApiAndSaveStatusesAsTweets("cat", 15);
        return "api-to-database";
    }

    @GetMapping("/getTweets")
    public String retrieveTweetsbyKeyword(@RequestParam(name = "keyword", required = true) String keyword) {
        processTweetsThroughNlpService.retrieveTweetsFromDatabase("cat");
        return "databaseTweetSearchResults";
    }



}
