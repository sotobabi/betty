package com.codingnomads.betty.data.batch.tweetsjob.hometeamstep;

import com.codingnomads.betty.data.models.Tweet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class HomeTeamStatusProcessor implements ItemProcessor<List<Status>, List<Tweet>> {

    private HomeTeamStatusItemReader homeTeamStatusItemReader;

    @Autowired
    public HomeTeamStatusProcessor(HomeTeamStatusItemReader homeTeamStatusItemReader) {
        this.homeTeamStatusItemReader = homeTeamStatusItemReader;
    }

    @Override
    public List<Tweet> process(List<Status> statusList) throws Exception {
        List<Tweet> listOfTweets = new ArrayList<>();

        for (Status status : statusList) {
            if (status == null) {
                return Collections.emptyList();
            }
            Tweet tweet = new Tweet();
            tweet.setCreatedAt(status.getCreatedAt());
            tweet.setText(status.getText());
            tweet.setLanguage(status.getLang());
            tweet.setKeywordUsed(homeTeamStatusItemReader.getTeamKeyword());
            listOfTweets.add(tweet);
        }
        return listOfTweets;
    }
}
