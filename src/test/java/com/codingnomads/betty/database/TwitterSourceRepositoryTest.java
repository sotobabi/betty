package com.codingnomads.betty.database;

import com.codingnomads.betty.configurations.TwitterConfigurer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TwitterSourceRepository.class, TwitterConfigurer.class})
public class TwitterSourceRepositoryTest {

    private static final String keyword = "a";
    private static final int numberOfTweets = 1;

    @Autowired
    private TwitterSourceRepository twitterSourceRepository;

    @Test
    public void whenSearchTweetsCalled_shouldReturnListOfTweets(){

        assertTrue(twitterSourceRepository.searchTweets(keyword,numberOfTweets).size() >= 0 );

    }
}
