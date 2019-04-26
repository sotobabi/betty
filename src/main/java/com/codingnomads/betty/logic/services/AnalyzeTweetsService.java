package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.data.models.Tweet;
import com.codingnomads.betty.logic.models.TeamProbabilityToWin;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AnalyzeTweetsService {

    @Autowired
    private TwitterService twitterService;

    @Autowired
    SentimentAnalyserService sentimentAnalyserService;

    public TeamProbabilityToWin calculateProbabilityWithTweets(String teamName) {
        List<Tweet> tweets = twitterService.searchTweets(teamName, 50); //todo: for now manually setting the number of tweets to be analyzed


    }
}
