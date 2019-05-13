package com.codingnomads.betty.data.api;

import com.codingnomads.betty.configurations.TwitterConfig;
import org.junit.Before;
import org.junit.Test;
import twitter4j.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TwitterSourceRepositoryTest {

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
        testQuery.setLang("en");
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

    @Test
    public void whenSearchTweetsFromAccountsCalled_shouldReturnListOfTweets() throws TwitterException {
        Queue<String> mockSearchQueries = new LinkedList();
        mockSearchQueries.add("keyword");
        Query testQuery2 = new Query(mockSearchQueries.poll());
        testQuery2.setSince("2019-05-10");
        testQuery2.setLang("en");

        when(mockTwitterConfigurer.getTwitter()).thenReturn(mockTwitter);
        when(mockTwitter.search(testQuery2)).thenReturn(mockQueryResult);
        when(mockQueryResult.getTweets()).thenReturn(list);

        assertTrue(testTwitterSourceMinerRepository.searchTweetFromAccounts("keyword","2019-05-10").size() == 1);

    }
}
