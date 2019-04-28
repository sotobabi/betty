package com.codingnomads.betty.presentation.controller;

import com.codingnomads.betty.logic.models.TeamProbabilityToWin;
import com.codingnomads.betty.logic.services.AnalyzeTweetsService;
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

    @Mock
    private TwitterService mockTwitterService;
    private SearchController testController;

    @Mock
    private AnalyzeTweetsService mockAnalyzeTweetsService;

    @Before
    public void setUp() {
        mockTwitterService = mock(TwitterService.class);
        mockAnalyzeTweetsService= mock(AnalyzeTweetsService.class);
        testController = new SearchController(mockAnalyzeTweetsService,mockTwitterService);
    }

    @Test
    public void whenEstimateTeamOddsIsCalled_probabilityIsReturned(){
        TeamProbabilityToWin testProbability = new TeamProbabilityToWin();
        Model uiModel = new ConcurrentModel();
        when(mockAnalyzeTweetsService.calculateProbabilityWithTweets("teamName")).thenReturn(testProbability);
        assertThat(testController.estimateTeamOdds("teamName",uiModel)).contains("getOdds");

    }

    @Test
    public void whenApiToDatabaseIsCalled_apiToDatabaseIsReturned() {
        when(mockTwitterService.callApiAndSaveStatusesAsTweets("someText", 1)).thenReturn(true);
        assertThat(testController.makeApiCallAndSinkTweetsToRemoteDatabase().contains("api-to-database")).isTrue();
    }

}
