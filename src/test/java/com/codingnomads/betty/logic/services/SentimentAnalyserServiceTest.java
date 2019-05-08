package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.logic.models.SentimentResult;

import com.codingnomads.betty.logic.models.TeamSentimentScore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class SentimentAnalyserServiceTest {


    @Mock
    private SentimentAnalyserService mockSentimentAnalyserService;

    @InjectMocks
    private SentimentAnalyserService sentimentAnalyserService;

    @Before
    public void setUp() {
        mockSentimentAnalyserService = mock(SentimentAnalyserService.class);
    }


    @Test(expected = NullPointerException.class)
    public void ifAverageSentimentResultScoreIsNull_throwANullPointerException() {
        SentimentResult sentimentResult = new SentimentResult();
        List<SentimentResult> sentimentResultList = new ArrayList<SentimentResult>();
        sentimentResultList.add(0, sentimentResult);

        sentimentAnalyserService.getAverageSentimentScore(sentimentResultList);

    }

    @Test
    public void ifAverageSentimentScoreIsPassedSentimentResultList_teamSentimentScoreIsReturned() {
        List<SentimentResult> sentimentResultList = new ArrayList<>();
        TeamSentimentScore teamSentimentScore = new TeamSentimentScore();
        teamSentimentScore.setScore(100.0);

        for(int i = 0; i <= 10; i++) {

            SentimentResult sentimentResult = new SentimentResult();
            sentimentResult.setSentimentScore(100);
            sentimentResultList.add(sentimentResult);

        }

        when(mockSentimentAnalyserService.getAverageSentimentScore(sentimentResultList)).thenReturn(teamSentimentScore);

    }

    @Test
    public void ifSentimentResultListContainingScores4IsPassedToAverageSentimentScore_averageSentimentScoreReturns100() {

        List<SentimentResult> sentimentResultList = new ArrayList<>();
        Double teamSentimentScore = 100.0;

        for(int i = 0; i <= 10; i++) {

            SentimentResult sentimentResult = new SentimentResult();
            sentimentResult.setSentimentScore(4);
            sentimentResultList.add(sentimentResult);

        }

        TeamSentimentScore teamSentimentScore2 = sentimentAnalyserService.getAverageSentimentScore(sentimentResultList);
        Assert.assertEquals((teamSentimentScore2.getScore()), teamSentimentScore);
    }

    @Test
    public void ifSentimentResultListContainingScores0IsPassedToAverageSentimentScore_averageSentimentScoreReturns100() {

        List<SentimentResult> sentimentResultList = new ArrayList<>();
        Double teamSentimentScore = 0.0;

        for(int i = 0; i <= 10; i++) {

            SentimentResult sentimentResult = new SentimentResult();
            sentimentResult.setSentimentScore(0);
            sentimentResultList.add(sentimentResult);

        }

        TeamSentimentScore teamSentimentScore2 = sentimentAnalyserService.getAverageSentimentScore(sentimentResultList);
        Assert.assertEquals((teamSentimentScore2.getScore()), teamSentimentScore);
    }

}