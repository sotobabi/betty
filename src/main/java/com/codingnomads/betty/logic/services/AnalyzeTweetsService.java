package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.data.models.Tweet;
import com.codingnomads.betty.logic.models.SentimentResult;
import com.codingnomads.betty.logic.models.TeamProbabilityToWin;
import com.codingnomads.betty.logic.models.TeamSentimentScore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AnalyzeTweetsService {

    @Autowired
    private TwitterService twitterService;

    @Autowired
    SentimentAnalyserService sentimentAnalyserService;

    @Autowired
    ProbabilityToWinService probabilityToWinService;

    public TeamProbabilityToWin calculateProbabilityWithTweets(String teamName) {
        List<Tweet> tweets = twitterService.searchTweets(teamName, 30); //todo: for now manually setting the number of tweets to be analyzed

        List<SentimentResult> sentimentResultList = new ArrayList<>();
        for(Tweet tweet : tweets){
            String tweetContent = tweet.getText();
            SentimentResult sentimentResult = sentimentAnalyserService.getSentimentResult(tweetContent);
            sentimentResultList.add(sentimentResult);
        }

        TeamSentimentScore teamSentimentScore = sentimentAnalyserService.getAverageSentimentScore(sentimentResultList);

        return probabilityToWinService.getProbabilityToWinFromSentimentAnalysis(teamSentimentScore);
    }
}
