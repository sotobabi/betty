package com.codingnomads.betty.data.api;

import com.codingnomads.betty.configurations.TwitterConfig;
import com.codingnomads.betty.logic.exceptions.TwitterSearchFailedException;
import com.codingnomads.betty.logic.interfaces.TwitterMinerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import twitter4j.*;
import java.util.List;

@Repository
public class TwitterSourceMinerRepository implements TwitterMinerRepository {

    private TwitterConfig twitterConfigurer;

    @Autowired
    public TwitterSourceMinerRepository(TwitterConfig twitterConfigurer) {
        this.twitterConfigurer = twitterConfigurer;
    }

    public List<Status> searchTweets(String keyword, int numberOfStatus){

        Twitter twitter = twitterConfigurer.getTwitter();

        Query query = new Query(keyword);

        query.setCount(numberOfStatus);
        query.setLang("en");

        try {
            QueryResult queryResult = twitter.search(query);
            return queryResult.getTweets();
        } catch (TwitterException te) {
            throw new TwitterSearchFailedException("Tweet Search Failed!", te);
        }
    }
}
