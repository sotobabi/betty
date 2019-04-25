package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.data.models.Tweet;
import com.codingnomads.betty.logic.interfaces.TwitterMinerRepository;
import com.codingnomads.betty.logic.interfaces.TwitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;

@Service
public class TwitterService {

    @Autowired
    private TwitterMinerRepository twitterMinerRepository;
    @Autowired
    private TwitterRepository twitterRepository;

    public List<Status> searchTweets(String keyword, int numberOfStatus){
        return twitterMinerRepository.searchTweets(keyword, numberOfStatus);
    }

    public void callApiAndSaveStatusesAsTweets(String keyword, int numberOfStatus) {
        List<Status> listOfStatus = searchTweets(keyword, numberOfStatus);
        createTweetObjectsAndAddToList(listOfStatus, keyword);
    }

    private void createTweetObjectsAndAddToList(List<Status> listOfStatus, String keyword) {
        List<Tweet> listOfTweets = new ArrayList<>();

        for (Status status : listOfStatus) {
            Tweet tweet = new Tweet();
            tweet.setCreatedAt(status.getCreatedAt().toString());
            tweet.setText(status.getText());
            tweet.setLanguage(status.getLang());
            tweet.setKeywordUsed(keyword);
            listOfTweets.add(tweet);
        }
        twitterRepository.saveTweets(listOfTweets);
    }


}
