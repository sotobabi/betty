package com.codingnomads.betty.presentation.controller;

import com.codingnomads.betty.logic.services.AnalyzeTweetsService;
import com.codingnomads.betty.logic.services.TwitterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTests {

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
    public void whenApiToDatabaseIsCalled_apiToDatabaseIsReturned() {
        when(mockTwitterService.callApiAndSaveStatusesAsTweets("someText", 1)).thenReturn(true);
        assertThat(testController.makeApiCallAndSinkTweetsToRemoteDatabase().contains("api-to-database")).isTrue();
    }

}
