package com.codingnomads.betty.data.batch.batchprocessors;

import com.codingnomads.betty.data.batch.tweetsjobs.hometeamjob.HomeTeamStatusItemReader;
import com.codingnomads.betty.data.batch.tweetsjobs.hometeamjob.HomeTeamStatusProcessor;
import com.codingnomads.betty.data.models.Tweet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HomeTeamStatusProcessorTests {

    @Mock
    private HomeTeamStatusItemReader mockHomeTeamStatusItemReader;
    private HomeTeamStatusProcessor testHomeTeamStatusProcessor;

    @Before
    public void setUp() throws Exception {
        mockHomeTeamStatusItemReader = mock(HomeTeamStatusItemReader.class);
        testHomeTeamStatusProcessor = new HomeTeamStatusProcessor(mockHomeTeamStatusItemReader);
    }

    @Test
    public void whenProcessorIsCalledWithListOfStatus_returnsAListOfTweets() throws Exception {
        List<Status> mockListOfStatus = new ArrayList<>();
        List<Tweet> expectedTweetsList = new ArrayList<>();

        when(mockHomeTeamStatusItemReader.getTeamKeyword()).thenReturn("testWord");
        assertThat(testHomeTeamStatusProcessor.process(mockListOfStatus)).isEqualTo(expectedTweetsList);
    }

    @Test
    public void whenProcessorIsCalledWithNullListOfStatus_returnsANullListOfTweets() throws Exception {
        List<Status> mockListOfStatus = new ArrayList<>();
        mockListOfStatus.add(null);

        List<Tweet> expectedTweetsList = Collections.emptyList();

        when(mockHomeTeamStatusItemReader.getTeamKeyword()).thenReturn("testWord");
        assertThat(testHomeTeamStatusProcessor.process(mockListOfStatus)).isEqualTo(expectedTweetsList);

    }
}
