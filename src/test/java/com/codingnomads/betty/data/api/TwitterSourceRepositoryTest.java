package com.codingnomads.betty.data.api;

import com.codingnomads.betty.configurations.TwitterConfig;
import org.junit.Before;
import org.junit.Test;
import twitter4j.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TwitterSourceRepositoryTest {

    private static final String keyword = "cat";
    private static final int numberOfTweets = 15;
    private TwitterConfig mockTwitterConfigurer;
    private TwitterSourceMinerRepository testTwitterSourceMinerRepository;
    private Status mockStatus;
    private Query testQuery;
    private Twitter mockTwitter;
    private QueryResult mockQueryResult;
    private List<Status> list;

    @Before
    public void setUp(){

        mockTwitterConfigurer = mock(TwitterConfig.class);
        testTwitterSourceMinerRepository = new TwitterSourceMinerRepository(mockTwitterConfigurer);
        mockStatus = mock(Status.class);
        testQuery = new Query("keyword");
        testQuery.setCount(1);
        mockTwitter = mock(Twitter.class);
        mockQueryResult = mock(QueryResult.class);
        list = Arrays.asList(mockStatus);

    }


    @Test
    public void whenSearchTweetsCalled_shouldReturnListOfTweets() throws TwitterException {

        when(mockTwitterConfigurer.getTwitter()).thenReturn(mockTwitter);
        when(mockTwitter.search(testQuery)).thenReturn(mockQueryResult);
        when(mockQueryResult.getTweets()).thenReturn(list);

        assertTrue(testTwitterSourceMinerRepository.searchTweets("keyword",1).size() == 1);

    }
}
