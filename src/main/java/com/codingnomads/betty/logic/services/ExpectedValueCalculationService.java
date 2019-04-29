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

    public Map<String, Double> calculateExpectedValue(String teamName1, String teamName2){

        Map<String, Double> expectedValueMap = new HashMap<>();

        double probabilityWithTweets = analyzeTweetsService.calculateProbabilityWithTweets(teamName1).getProbabilityToWin();

        Map<String, Double> marketOdds = gameInformationService.getOddsByMatch(teamName1, teamName2);

        double expectedValue = probabilityWithTweets * (marketOdds.get(teamName1) - 1) - (1 - probabilityWithTweets) * marketOdds.get(teamName2);

        expectedValueMap.put(teamName1, expectedValue);

        probabilityWithTweets = analyzeTweetsService.calculateProbabilityWithTweets(teamName2).getProbabilityToWin();

        expectedValue = probabilityWithTweets * (marketOdds.get(teamName2) -1) - (1 - probabilityWithTweets) * marketOdds.get(teamName1);

        expectedValueMap.put(teamName2, expectedValue);

        return expectedValueMap;
    }
}
