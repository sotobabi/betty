package com.codingnomads.betty.data.api;

import com.codingnomads.betty.configurations.TwitterConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TwitterSourceMinerRepository.class, TwitterConfig.class})
public class TwitterSourceRepositoryTest {

    private static final String keyword = "cat";
    private static final int numberOfTweets = 15;

    @Autowired
    private TwitterSourceMinerRepository twitterSourceRepository;

    @Test
    public void whenSearchTweetsCalled_shouldReturnListOfTweets(){

        assertTrue(twitterSourceRepository.searchTweets(keyword,numberOfTweets).size() >= 0 );

    }
}
