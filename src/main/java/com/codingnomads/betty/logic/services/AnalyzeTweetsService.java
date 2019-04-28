package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.data.models.Tweet;
import com.codingnomads.betty.logic.models.SentimentResult;
import com.codingnomads.betty.logic.models.TeamProbabilityToWin;
import com.codingnomads.betty.logic.models.TeamSentimentScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyzeTweetsService {

    private TwitterService twitterService;
    private SentimentAnalyserService sentimentAnalyserService;
    private ProbabilityToWinService probabilityToWinService;

    @Autowired
    public AnalyzeTweetsService(TwitterService twitterService, SentimentAnalyserService sentimentAnalyserService, ProbabilityToWinService probabilityToWinService) {
        this.twitterService = twitterService;
        this.sentimentAnalyserService = sentimentAnalyserService;
        this.probabilityToWinService = probabilityToWinService;
    }

    public TeamProbabilityToWin calculateProbabilityWithTweets(String teamName) {

        if(teamName == null){
            return new TeamProbabilityToWin();
        }

        List<Status> tweets = twitterService.searchTweets(teamName, 30);

        List<SentimentResult> sentimentResultList = new ArrayList<>();

        for (Status tweet : tweets) {
            String tweetContent = tweet.getText();
            SentimentResult sentimentResult = sentimentAnalyserService.getSentimentResult(tweetContent);
            sentimentResultList.add(sentimentResult);
        }

        TeamSentimentScore teamSentimentScore = sentimentAnalyserService.getAverageSentimentScore(sentimentResultList);

        return probabilityToWinService.getProbabilityToWinFromSentimentAnalysis(teamSentimentScore);
    }
}
