package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.data.models.Tweet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterServiceTests {

    private TwitterService service;

    @Before
    public void setUp() {
        service = new TwitterService();
    }

    @Test
    public void ifATweetCannotBeSaved_shouldReturnFalse() {
        Tweet testTweet = new Tweet();
        testTweet.setText("Test Text");

        assertThat(service.saveTweet(testTweet)).isFalse();

    }
}
