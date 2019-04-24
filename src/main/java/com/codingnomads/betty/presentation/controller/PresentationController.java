package com.codingnomads.betty.presentation.controller;

import com.codingnomads.betty.logic.models.SentimentResult;
import com.codingnomads.betty.logic.services.SentimentAnalyserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.SQLOutput;
import java.util.List;

@Controller
public class PresentationController {

    private final String text = "'Super amazing.’ Maurizio Sarri assesses our chances in the Europa League and the Premier League top-four race... #CHEBUR";

    @Autowired
    private SentimentAnalyserService sentimentAnalyserService;

    public void start() {
        SentimentResult sentimentResult = sentimentAnalyserService.getSentimentResult(text);
        System.out.println("Average sentiment analysis score is: " + sentimentAnalyserService.getAverageSentimentScore(sentimentAnalyserService.sentimentResultList));
    }

    private void printResults(SentimentResult sentimentResult) {
        System.out.println("Sentiments Classification:");
        System.out.println("Very positive: " + sentimentResult.getSentimentClass().getVeryPositive()+"%");
        System.out.println("Positive: " + sentimentResult.getSentimentClass().getPositive()+"%");
        System.out.println("Neutral: " + sentimentResult.getSentimentClass().getNeutral()+"%");
        System.out.println("Negative: " + sentimentResult.getSentimentClass().getNegative()+"%");
        System.out.println("Very negative: " + sentimentResult.getSentimentClass().getVeryNegative()+"%");
        System.out.println("\nSentiments result:");
        System.out.println("Sentiment Score: " + sentimentResult.getSentimentScore());
        System.out.println("Sentiment Type: " + sentimentResult.getSentimentType());
    }
}
