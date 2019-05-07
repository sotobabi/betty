package com.codingnomads.betty.logic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExpectedValueCalculationService {

    private AnalyzeTweetsService analyzeTweetsService;
    private GameInformationService gameInformationService;

    @Autowired
    public ExpectedValueCalculationService(AnalyzeTweetsService analyzeTweetsService, GameInformationService gameInformationService) {
        this.analyzeTweetsService = analyzeTweetsService;
        this.gameInformationService = gameInformationService;
    }

    public Map<String, Double> calculateExpectedValue(String homeTeamName, String awayTeamName){

        Map<String, Double> expectedValueMap = new HashMap<>();

        double probabilityWithTweets = analyzeTweetsService.calculateProbabilityWithTweets(homeTeamName).getProbabilityToWin();

        Map<String, Double> marketOdds = gameInformationService.getOddsByMatch(homeTeamName, awayTeamName);

        double expectedValue = probabilityWithTweets * (marketOdds.get(homeTeamName) - 1) - (1 - probabilityWithTweets) * marketOdds.get(awayTeamName);

        expectedValueMap.put(homeTeamName, expectedValue);

        probabilityWithTweets = analyzeTweetsService.calculateProbabilityWithTweets(awayTeamName).getProbabilityToWin();

        expectedValue = probabilityWithTweets * (marketOdds.get(awayTeamName) -1) - (1 - probabilityWithTweets) * marketOdds.get(homeTeamName);

        expectedValueMap.put(awayTeamName, expectedValue);

        return expectedValueMap;
    }
}
