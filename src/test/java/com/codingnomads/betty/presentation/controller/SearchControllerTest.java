package com.codingnomads.betty.presentation.controller;

import com.codingnomads.betty.logic.models.TeamProbabilityToWin;
import com.codingnomads.betty.logic.services.AnalyzeTweetsService;
import com.codingnomads.betty.logic.services.ProcessTweetsThroughNlpService;
import com.codingnomads.betty.logic.services.SourceToResultPipelineService;
import com.codingnomads.betty.logic.services.TwitterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchControllerTest {

    private SearchController testController;

    @Mock
    private TwitterService mockTwitterService;

    @Mock
    private AnalyzeTweetsService mockAnalyzeTweetsService;

    @Mock
    private SourceToResultPipelineService mockSourceToResultPipelineService;

    @Mock
    private ProcessTweetsThroughNlpService mockProcessTweetsThroughNlpService;

    @Before
    public void setUp() {
        mockTwitterService = mock(TwitterService.class);
        mockAnalyzeTweetsService = mock(AnalyzeTweetsService.class);
        mockSourceToResultPipelineService = mock(SourceToResultPipelineService.class);
        mockProcessTweetsThroughNlpService = mock(ProcessTweetsThroughNlpService.class);
        testController = new SearchController(mockProcessTweetsThroughNlpService, mockAnalyzeTweetsService, mockTwitterService, mockSourceToResultPipelineService);
    }

    @Test
    public void whenEstimateTeamOddsIsCalled_calculateOddsIsReturned() {
        String teamName = "testTeam";
        TeamProbabilityToWin testProbability = new TeamProbabilityToWin();
        Model uiModel = new ConcurrentModel();
        when(mockAnalyzeTweetsService.calculateProbabilityWithTweets(teamName)).thenReturn(testProbability);
        assertThat(testController.estimateTeamOdds(teamName, uiModel)).contains("calculateOdds");

    }

    @Test
    public void whenDisplayTeamOddsIsCalled_displayOddsIsReturned() {
        Model uiModel = new ConcurrentModel();
        assertThat(testController.displayTeamOdds("someTeam", uiModel)).contains("displayOdds");

    }

    @Test
    public void whenApiToDatabaseIsCalled_apiToDatabaseIsReturned() {
        when(mockTwitterService.callApiAndSaveStatusesAsTweets("someText", 1)).thenReturn(true);
        assertThat(testController.makeApiCallAndSinkTweetsToRemoteDatabase().contains("api-to-database")).isTrue();
    }

    @Test
    public void whenGetSentimentScoreByKeywordUsedIsCalled_SentimentScoreIsReturned() {
        Double sentimentScore = null;
        when(mockProcessTweetsThroughNlpService.returnSentimentScoreByKeywordUsed("someText")).thenReturn(sentimentScore);

    }

    @Test
    public void whenGetSentimentScoreByKeywordUsedIsCalled_displaySentimentScoreIsReturned() {
        Model uiModel = new ConcurrentModel();
        assertThat(testController.getSentimentScoreByKeywordUsed("someTeam", uiModel)).contains("display-sentiment-score");
    }
}
