package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.data.models.Tweet;
import com.codingnomads.betty.logic.interfaces.TwitterJpaRepository;
import com.codingnomads.betty.logic.interfaces.TwitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Status;
import java.util.List;

@Service
public class TwitterService {

    @Autowired
    private TwitterRepository twitterRepository;
    @Autowired
    private TwitterJpaRepository twitterJpaRepository;

    public List<Status> searchTweets(String keyword, int numberOfStatus){

        List<Status> listOfTweets = twitterRepository.searchTweets(keyword, numberOfStatus);

        convertListToTweets(listOfTweets);

        return listOfTweets;
    }

    private void convertListToTweets(List<Status> listOfTweets) {
        for (Status status : listOfTweets) {
            createTweetObjectAndSaveToRepository(status);
        }
        System.out.println("List Converted----------------------------->");
    }

    private void createTweetObjectAndSaveToRepository(Status status) {
        Tweet tweet = new Tweet();
        tweet.setCreatedAt(status.getCreatedAt().toString());
        tweet.setLanguage(status.getLang());
        tweet.setText(status.getText());
        twitterJpaRepository.saveAndFlush(tweet);
        System.out.println("Object Created----------------------------->");
    }

}
