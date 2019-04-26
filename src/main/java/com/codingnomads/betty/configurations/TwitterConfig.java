package com.codingnomads.betty.configurations;

import org.springframework.stereotype.Component;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Component
public class TwitterConfig {

    private Twitter twitter;
    private TwitterFactory twitterFactory;

    public TwitterConfig() {

        buildTwitterConfiguration();
    }

    public Twitter getTwitter() {
        return twitter;
    }

    private void buildTwitterConfiguration() {

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();

        configurationBuilder
                .setDebugEnabled(true)
                .setOAuthConsumerKey("hv8cRVlIIsTK44fly4Bdd6sRn")
                .setOAuthConsumerSecret("7XFSc7ZPdOSfiGJgdJB8GwYWeOE0YeOOvBaQcyWKPUqoLDLPV8")
                .setOAuthAccessToken("302127626-tIpN4z9Rb2qbj8r8nuAKGQhRYPdTp07VKg0YI0PI")
                .setOAuthAccessTokenSecret("onVEyjUBZp4SQX3yXiSytZ1UfnYpcxBSnpr8DF20bEIHb");

        twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter = twitterFactory.getInstance();
    }
}


