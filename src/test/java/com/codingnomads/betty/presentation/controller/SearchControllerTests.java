package com.codingnomads.betty.presentation.controller;

import com.codingnomads.betty.logic.models.TeamProbabilityToWin;
import com.codingnomads.betty.logic.services.AnalyzeTweetsService;
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
public class SearchControllerTests {

    private SearchController testController;

    @Mock
    private TwitterService mockTwitterService;

    @Mock
    private AnalyzeTweetsService mockAnalyzeTweetsService;

    @Mock
    private SourceToResultPipelineService mockSourceToResultPipelineService;

    @Before
    public void setUp() {
        mockTwitterService = mock(TwitterService.class);
        mockAnalyzeTweetsService = mock(AnalyzeTweetsService.class);
        mockSourceToResultPipelineService = mock(SourceToResultPipelineService.class);
        testController = new SearchController(mockAnalyzeTweetsService,mockTwitterService,mockSourceToResultPipelineService);
    }

    @Test
    public void whenEstimateTeamOddsIsCalled_calculateOddsIsReturned(){
        String teamName = "testTeam";
        TeamProbabilityToWin testProbability = new TeamProbabilityToWin();
        Model uiModel = new ConcurrentModel();
        when(mockAnalyzeTweetsService.calculateProbabilityWithTweets(teamName)).thenReturn(testProbability);
        assertThat(testController.estimateTeamOdds(teamName,uiModel)).contains("calculateOdds");

    }

    @Test
    public void whenDisplayTeamOddsIsCalled_displayOddsIsReturned(){
        Model uiModel = new ConcurrentModel();
        assertThat(testController.displayTeamOdds("someTeam",uiModel)).contains("displayOdds");

    }

    @Test
    public void whenApiToDatabaseIsCalled_apiToDatabaseIsReturned() {
        when(mockTwitterService.callApiAndSaveStatusesAsTweets("someText", 1)).thenReturn(true);
        assertThat(testController.makeApiCallAndSinkTweetsToRemoteDatabase().contains("api-to-database")).isTrue();
    }

}
