package com.codingnomads.betty.database;

import com.codingnomads.betty.configurations.TwitterConfigurer;
import com.codingnomads.betty.database.TwitterSourceRepository;
import com.codingnomads.betty.logic.repositories.TwitterRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TwitterSourceRepository.class, TwitterConfigurer.class})
public class TwitterSourceRepositoryTest {

    private static final String keyword = "test";
    private static final int numberOfTweets = 1;

    @Autowired
    private TwitterSourceRepository twitterSourceRepository;

    @Test
    public void whenSearchTweetsCalled_shouldReturnListOfTweets(){

        assertTrue(twitterSourceRepository.searchTweets(keyword,numberOfTweets).size() == 1 );

    }
}
