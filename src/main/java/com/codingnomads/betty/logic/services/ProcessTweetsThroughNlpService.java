package com.codingnomads.betty.logic.services;


import com.codingnomads.betty.data.models.Tweet;
import com.codingnomads.betty.logic.interfaces.TwitterJpaRepository;
import com.codingnomads.betty.logic.models.TeamSentimentScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessTweetsThroughNlpService {


    //Fields
    private TwitterJpaRepository twitterJpaRepository;
    private SourceToResultPipelineService sourceToResultPipelineService;

    //Constructor
    @Autowired
    public ProcessTweetsThroughNlpService(TwitterJpaRepository twitterJpaRepository, SourceToResultPipelineService sourceToResultPipelineService) {
        this.twitterJpaRepository = twitterJpaRepository;
        this.sourceToResultPipelineService = sourceToResultPipelineService;
    }

    public Double returnSentimentScoreByKeywordUsed(String keyword) {
        List<Tweet> tweetList = twitterJpaRepository.findByKeywordUsedLike(keyword);
        List<String> stringsFromTweets = extractStringsFromTweets(tweetList);
        TeamSentimentScore teamSentimentScore = sourceToResultPipelineService.convertTextsToSentimentResultList(stringsFromTweets);

        return teamSentimentScore.getScore();
    }


    private List<String> extractStringsFromTweets(List<Tweet> tweetList) {
        List<String> tweetStringList = new ArrayList<>();

        for(Tweet tweet : tweetList) {
            tweetStringList.add(tweet.getText());
        }

        return tweetStringList;
    }

}
