package com.codingnomads.betty.presentation.controller;

import com.codingnomads.betty.logic.TwitterAuthenticator;
import com.codingnomads.betty.logic.services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import twitter4j.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class Console {

    @Autowired
    private TwitterService twitterService;

    public void display() throws TwitterException {

        TwitterAuthenticator twitterAuthenticator = new TwitterAuthenticator();

        Twitter twitter = twitterAuthenticator.getTwitter();

        Query query = new Query("dogs");

        query.setCount(10);

        QueryResult queryResult = twitter.search(query);



        List<Status> tweets = queryResult.getTweets();


       // List<String> list = queryResult.getTweets().stream().map(item -> item.getText()).collect(Collectors.toList());

        System.out.println(tweets.size());

        for (Status tweet: tweets)
             {

                 System.out.println(tweet.getText());
                 System.out.println();
                 System.out.println(tweet.getFavoriteCount());
                 System.out.println("=============================================");

        }



    }

    public void displayService() throws TwitterException {

        System.out.println(twitterService.searchTweets().size());

    }
}
