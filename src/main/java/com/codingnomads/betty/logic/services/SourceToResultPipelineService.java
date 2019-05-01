package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.logic.models.SentimentResult;
import com.codingnomads.betty.logic.models.TeamProbabilityToWin;
import com.codingnomads.betty.logic.models.TeamSentimentScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SourceToResultPipelineService {

    private SentimentAnalyserService sentimentAnalyserService;
    private ProbabilityToWinService probabilityToWinService;

    @Autowired
    public SourceToResultPipelineService(SentimentAnalyserService sentimentAnalyserService, ProbabilityToWinService probabilityToWinService) {
        this.sentimentAnalyserService = sentimentAnalyserService;
        this.probabilityToWinService = probabilityToWinService;
    }


    public TeamProbabilityToWin transformTextToTeamProbability(List<String> listOfTextToAnalyze){

        TeamSentimentScore teamSentimentScore = convertTextsToSentimentResultList(listOfTextToAnalyze);

        return probabilityToWinService.getProbabilityToWinFromSentimentAnalysis(teamSentimentScore);

    }

    private TeamSentimentScore convertTextsToSentimentResultList(List<String> listOfTextToAnalyze) {
        List<SentimentResult> sentimentResultList = new ArrayList<>();

        for (String text : listOfTextToAnalyze) {
            SentimentResult sentimentResult = sentimentAnalyserService.getSentimentResult(text);
            sentimentResultList.add(sentimentResult);
        }
        return sentimentAnalyserService.getAverageSentimentScore(sentimentResultList);
    }

}
