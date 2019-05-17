package com.codingnomads.betty.data.api;

import com.codingnomads.betty.configurations.TwitterConfig;
import com.codingnomads.betty.logic.exceptions.TwitterSearchFailedException;
import com.codingnomads.betty.logic.interfaces.TwitterMinerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import twitter4j.*;

import java.util.*;

@Repository
public class TwitterSourceMinerRepository implements TwitterMinerRepository {

    private TwitterConfig twitterConfigurer;

    @Autowired
    public TwitterSourceMinerRepository(TwitterConfig twitterConfigurer) {
        this.twitterConfigurer = twitterConfigurer;
    }

    public List<Status> searchTweets(String keyword, int numberOfStatus) {
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

    public List<Status> searchTweetFromAccounts(String keyword, String startDate) {
        Queue<String> queries = new LinkedList<>();
        queries.add("Chelsea -Arsenal from:ChelseaFC");
        queries.add("Chelsea -Arsenal from:BBCSport");

        Twitter twitter = twitterConfigurer.getTwitter();
        int index = 1;
        List<Status> tweets = new ArrayList<>();

        while (!queries.isEmpty()) {
            Query query = new Query(queries.poll());
            query.setLang("en");
            query.setSince(startDate);

            QueryResult queryResult = null;
            try {
                queryResult = twitter.search(query);
            } catch (TwitterException te) {
                throw new TwitterSearchFailedException("Tweet Search Failed!", te);
            }

            tweets = queryResult.getTweets();

            for (Status tweet : tweets) {
                System.out.println(index++ + "--------------------------");
                System.out.println(tweet.getCreatedAt() + "-->" + tweet.getId());
                System.out.println(tweet.getText());
            }
        }
        return tweets;
    }

}
