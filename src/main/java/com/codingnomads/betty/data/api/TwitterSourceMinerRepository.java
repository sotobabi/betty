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

    public void searchTweetsByUserId(String keyword) {

        TwitterStream twitterStream = twitterConfigurer.getTwitterStream();
        twitterStream.addListener(setUpSetUpListener());

        FilterQuery filterQuery = new FilterQuery();
        filterQuery.language("EN");
        String[] queries = {"Chelsea -Arsenal from:ChelseaFC", "Manchester City from:BBCSport"};
        filterQuery.track(queries);

        twitterStream.filter(filterQuery);
        twitterStream.sample();
    }

    private StatusListener setUpSetUpListener() {
        return new StatusListener() {
            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }

            @Override
            public void onStatus(Status status) {
                System.out.println("--------Inside OnStatus-----");
                System.out.println(status.getText());
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println(warning);
            }
        };
    }
}
