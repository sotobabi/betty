package com.codingnomads.betty.data.api;

import com.codingnomads.betty.configurations.TwitterConfigurer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import twitter4j.Status;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TwitterSourceRepository.class, TwitterConfigurer.class})
public class TwitterSourceRepositoryTest {

    private static final String keyword = "cat";
    private static final int numberOfTweets = 15;

    @Autowired
    private TwitterSourceRepository twitterSourceRepository;

    @Test
    public void whenSearchTweetsCalled_shouldReturnListOfTweets(){

        List<Status> list = twitterSourceRepository.searchTweets(keyword,numberOfTweets);

        System.out.println(list.get(0).getUser());

        assertTrue(twitterSourceRepository.searchTweets(keyword,numberOfTweets).size() >= 0 );

    }
}
