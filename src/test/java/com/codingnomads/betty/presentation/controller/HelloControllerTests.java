package com.codingnomads.betty.presentation.controller;

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
    private HelloController testController;

    @Before
    public void setUp() {
        mockTwitterService = mock(TwitterService.class);
        testController = new HelloController(mockTwitterService);
    }

    @Test
    public void whenRootMappingIsCalled_helloTemplateIsReturned() {
        assertThat(testController.sayHello().contains("hello")).isTrue();
    }

    @Test
    public void whenApiToDatabaseIsCalled_apiToDatabaseIsReturned() {
        when(mockTwitterService.callApiAndSaveStatusesAsTweets("someText", 1)).thenReturn(true);
        assertThat(testController.makeApiCallAndSinkTweetsToRemoteDatabase().contains("api-to-database")).isTrue();
    }

}
