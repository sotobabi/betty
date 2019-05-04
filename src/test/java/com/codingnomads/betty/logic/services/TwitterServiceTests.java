package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.data.models.Tweet;
import com.codingnomads.betty.logic.interfaces.TwitterMinerRepository;
import com.codingnomads.betty.logic.interfaces.TwitterRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TwitterServiceTests {

    private TwitterService testTwitterService;
    @Mock
    private TwitterMinerRepository mockTwitterMinerRepository;
    @Mock
    private TwitterRepository mockTwitterRepository;
    @Mock
    private Tweet mockTweet;

    @Before
    public void setUp() {
        mockTwitterMinerRepository = mock(TwitterMinerRepository.class);
        mockTwitterRepository = mock(TwitterRepository.class);
        testTwitterService = new TwitterService(mockTwitterMinerRepository, mockTwitterRepository);
        mockTweet = mock(Tweet.class);
    }

    @Test
    public void whenCallApiAndSaveStatusesAsTweetsIsCalledWithInValidParams_returnsFalse() {
        List<Tweet> mockListOfTweets = new ArrayList<>();
        mockListOfTweets.add(mockTweet);

        when(mockTwitterRepository.saveTweets(mockListOfTweets)).thenReturn(false);

        assertThat(testTwitterService.callApiAndSaveStatusesAsTweets("someText", 1)).isFalse();

    }
}
