package com.codingnomads.betty.data.api;

import com.codingnomads.betty.configurations.TwitterConfigurer;
import com.codingnomads.betty.logic.services.TwitterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import twitter4j.Status;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TwitterSourceRepository.class, TwitterConfigurer.class, TwitterService.class})
public class TwitterSourceRepositoryTest {

    private static final String keyword = "cat";
    private static final int numberOfTweets = 15;

//    @Autowired
//    private TwitterSourceRepository twitterSourceRepository;
    @Autowired
    private TwitterService twitterService;

    @Test
    public void whenSearchTweetsCalled_shouldReturnListOfTweets(){

//        List<Status> list = twitterSourceRepository.searchTweets(keyword,numberOfTweets);

        assertTrue(twitterService.searchTweets(keyword,numberOfTweets).size() > 0);

//        assertTrue(twitterSourceRepository.searchTweets(keyword,numberOfTweets).size() >= 0 );

    }
}
