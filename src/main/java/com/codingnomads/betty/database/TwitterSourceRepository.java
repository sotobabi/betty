package com.codingnomads.betty.database;

import com.codingnomads.betty.configurations.TwitterConfigurer;
import com.codingnomads.betty.logic.exceptions.TwitterSearchFailedException;
import com.codingnomads.betty.logic.repositories.TwitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.*;
import java.util.List;

@Service
public class TwitterSourceRepository implements TwitterRepository {

    @Autowired
    private TwitterConfigurer twitterConfigurer;

    public List<Status> searchTweets(String keyword, int numberOfStatus){

        Twitter twitter = twitterConfigurer.getTwitter();

        Query query = new Query(keyword);

        query.setCount(numberOfStatus);

        QueryResult queryResult = null;
        try {
            queryResult = twitter.search(query);
        } catch (TwitterException te) {
            throw new TwitterSearchFailedException("Tweet Search Failed!", te);
        }

        List<Status> tweets = queryResult.getTweets();

        return tweets;
    }
}
