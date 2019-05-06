package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.data.models.Tweet;
import com.codingnomads.betty.logic.models.SentimentResult;
import com.codingnomads.betty.logic.models.TeamProbabilityToWin;
import com.codingnomads.betty.logic.models.TeamSentimentScore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnalyzeTweetsServiceTest {

    private AnalyzeTweetsService testAnalyzeTweetsService;

    @Mock
    TwitterService mockTwitterService;

    @Mock
    SentimentAnalyserService mockSentimentAnalyserService;

    @Mock
    ProbabilityToWinService mockProbabilityToWinService;

    @Before
    public void setUp() {
        mockTwitterService = mock(TwitterService.class);
        mockProbabilityToWinService = mock(ProbabilityToWinService.class);
        mockSentimentAnalyserService = mock(SentimentAnalyserService.class);
        testAnalyzeTweetsService = new AnalyzeTweetsService(mockTwitterService, mockSentimentAnalyserService, mockProbabilityToWinService);

    }

    @Test
    public void whenCalculateSentimentResultWithTweet_shouldReturnSentimentResult() {
//        SentimentResult testSentimentResult = new SentimentResult();
//        testSentimentResult.setSentimentScore(2);
//        List<SentimentResult> testResultList = new ArrayList<>();
//        testResultList.add(testSentimentResult);
//
//        TeamSentimentScore testScore = mockSentimentAnalyserService.getAverageSentimentScore(testResultList);
//        testScore.setTeamName("testTeam");
//
//        assertThat(mockSentimentAnalyserService.getSentimentResult(testScore.getTeamName()).getSentimentScore().equals(2));


    }
}