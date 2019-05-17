package com.codingnomads.betty.presentation.controller;

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

    private TwitterService twitterService;
    private SourceToResultPipelineService sourceToResultPipelineService;
    private ProcessTweetsThroughNlpService processTweetsThroughNlpService;

    @Autowired
    public SearchController(ProcessTweetsThroughNlpService processTweetsThroughNlpService,
                            TwitterService twitterService,
                            SourceToResultPipelineService sourceToResultPipelineService) {
        this.twitterService = twitterService;
        this.sourceToResultPipelineService = sourceToResultPipelineService;
        this.processTweetsThroughNlpService = processTweetsThroughNlpService;
    }

    @GetMapping("/display-odds")
    public String displayTeamOdds(@RequestParam(name = "teamName", required = false) String teamName, Model model) {
        model.addAttribute("inputTeam", new InputTeam());
        return "/displayOdds";
    }

    @GetMapping("/get-sentiment-score")
    public String getSentimentScoreByKeywordUsed(@RequestParam(name = "keyword", required = true) String keyword, Model model) {
        double sentimentScore = processTweetsThroughNlpService.returnSentimentScoreByKeywordUsed(keyword);
        model.addAttribute("sentimentScore", sentimentScore);
        return "display-sentiment-score";
    }

}
