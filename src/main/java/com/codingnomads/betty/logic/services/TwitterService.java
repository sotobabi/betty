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

    private TwitterMinerRepository twitterMinerRepository;
    private TwitterRepository twitterRepository;

    @Autowired
    public TwitterService(TwitterMinerRepository twitterMinerRepository, TwitterRepository twitterRepository) {
        this.twitterMinerRepository = twitterMinerRepository;
        this.twitterRepository = twitterRepository;
    }


    public List<Status> searchTweets(String keyword, int numberOfStatus){
        return twitterMinerRepository.searchTweets(keyword, numberOfStatus);
    }

    public Boolean callApiAndSaveStatusesAsTweets(String keyword, int numberOfStatus) {
        List<Status> listOfStatus = searchTweets(keyword, numberOfStatus);
        return createTweetObjectsAndAddToList(listOfStatus, keyword);
    }

    private Boolean createTweetObjectsAndAddToList(List<Status> listOfStatus, String keyword) {
        List<Tweet> listOfTweets = new ArrayList<>();

        for (Status status : listOfStatus) {
            Tweet tweet = new Tweet();
            tweet.setCreatedAt(status.getCreatedAt());
            tweet.setText(status.getText());
            tweet.setLanguage(status.getLang());
            tweet.setKeywordUsed(keyword);
            listOfTweets.add(tweet);
        }
        return twitterRepository.saveTweets(listOfTweets);
    }


}
