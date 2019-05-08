package com.codingnomads.betty.data.api;

import com.codingnomads.betty.configurations.TwitterConfig;
import com.codingnomads.betty.logic.exceptions.TwitterSearchFailedException;
import com.codingnomads.betty.logic.interfaces.TwitterMinerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import twitter4j.*;
import java.util.List;
import java.util.NoSuchElementException;

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

    public void searchTweetsByUserId(String keyword) {
        Twitter twitter = twitterConfigurer.getTwitter();
        long userId1 = 265902729;
        long userId2 = 19230601;

        ResponseList<User> userInfo = null;
        try {
            userInfo = twitter.lookupUsers(new long[]{userId1, userId2});
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        for(User u: userInfo){
            System.out.println(u.getScreenName() + ": " + u.getStatus().getText());
        }
    }
}
