package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.logic.models.SentimentResult;
import com.codingnomads.betty.logic.models.TeamProbabilityToWin;
import com.codingnomads.betty.logic.models.TeamSentimentScore;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SourceToResultPipelineServiceTest {

    private SourceToResultPipelineService testSourceToResultPipelineService;
    @Mock
    private SentimentAnalyserService mockSentimentAnalyserService;
    @Mock
    private ProbabilityToWinService mockProbabilityToWinService;


    @Before
    public void setUp(){
        mockSentimentAnalyserService = mock(SentimentAnalyserService.class);
        mockProbabilityToWinService = mock(ProbabilityToWinService.class);
        testSourceToResultPipelineService = new SourceToResultPipelineService(mockSentimentAnalyserService,mockProbabilityToWinService);
    }

    @Test
    public void whenTransformTextToTeamProbability_shouldReturnTeamProbability() {


        List<String> texts = new ArrayList<>();
        texts.add("a");
        SentimentResult sentimentResult = new SentimentResult();
        sentimentResult.setSentimentScore(1);
        TeamSentimentScore teamSentimentScore = new TeamSentimentScore();
        teamSentimentScore.setTeamName("testTeam");
        TeamProbabilityToWin teamProbabilityToWin = new TeamProbabilityToWin();
        teamProbabilityToWin.setTeamName("testTeam");

        when(mockSentimentAnalyserService.getSentimentResult("a")).thenReturn(sentimentResult);
        when(mockSentimentAnalyserService.getAverageSentimentScore(Arrays.asList(sentimentResult))).thenReturn(teamSentimentScore);
        when(mockProbabilityToWinService.getProbabilityToWinFromSentimentAnalysis(teamSentimentScore)).thenReturn(teamProbabilityToWin);

        TeamProbabilityToWin testResult = testSourceToResultPipelineService.transformTextToTeamProbability(texts);

        assertThat(testResult).isEqualTo(teamProbabilityToWin);
        assertThat(testResult.getTeamName()).isEqualTo("testTeam");

    }
}