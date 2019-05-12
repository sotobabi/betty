package com.codingnomads.betty.configurations;

import org.springframework.stereotype.Component;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

@Component
public class TwitterConfig {

    private Twitter twitter;
    private TwitterStream twitterStream;
    private TwitterFactory twitterFactory;

    public TwitterConfig() {

        buildTwitterConfiguration();
        buildTwitterStreamConfiguration();
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public TwitterStream getTwitterStream() {return twitterStream;}

    private void buildTwitterConfiguration() {

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();

        configurationBuilder
                .setDebugEnabled(true)
                .setOAuthConsumerKey(System.getenv("CONSUMER_KEY"))
                .setOAuthConsumerSecret(System.getenv("CONSUMER_SECRET"))
                .setOAuthAccessToken(System.getenv("ACCESS_TOKEN"))
                .setOAuthAccessTokenSecret(System.getenv("ACCESS_TOKEN_SECRET"));

        twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter = twitterFactory.getInstance();

    }

    private void buildTwitterStreamConfiguration(){
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();

        configurationBuilder
                .setDebugEnabled(true)
                .setOAuthConsumerKey(System.getenv("CONSUMER_KEY"))
                .setOAuthConsumerSecret(System.getenv("CONSUMER_SECRET"))
                .setOAuthAccessToken(System.getenv("ACCESS_TOKEN"))
                .setOAuthAccessTokenSecret(System.getenv("ACCESS_TOKEN_SECRET"));

        twitterStream = new TwitterStreamFactory(configurationBuilder.build()).getInstance();
    }
}


