package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.logic.TwitterAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.util.List;

@Service
public class TwitterService {

    @Autowired
    private TwitterAuthenticator twitterAuthenticator;

    public List<Status> searchTweets() throws TwitterException {

        Twitter twitter = twitterAuthenticator.getTwitter();

        Query query = new Query("source:dogs");

        query.setCount(10);

        QueryResult queryResult = twitter.search(query);

        List<Status> tweets = queryResult.getTweets();

        return tweets;
    }
}
