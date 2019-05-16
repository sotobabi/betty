package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.data.models.Tweet;
import com.codingnomads.betty.logic.interfaces.TwitterMinerRepository;
import org.junit.Before;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;

public class TwitterServiceTests {

    private TwitterService testTwitterService;
    @Mock
    private TwitterMinerRepository mockTwitterMinerRepository;
    @Mock
    private Tweet mockTweet;

    @Before
    public void setUp() {
        mockTwitterMinerRepository = mock(TwitterMinerRepository.class);
        testTwitterService = new TwitterService(mockTwitterMinerRepository);
        mockTweet = mock(Tweet.class);
    }
}
