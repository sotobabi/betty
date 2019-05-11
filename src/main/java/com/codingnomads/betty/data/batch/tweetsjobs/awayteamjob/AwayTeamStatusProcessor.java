package com.codingnomads.betty.data.batch.tweetsjobs.awayteamjob;

import com.codingnomads.betty.data.models.Tweet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Qualifier("awayTeamTweets")
@Component
public class AwayTeamStatusProcessor implements ItemProcessor<List<Status>, List<Tweet>> {
    private AwayTeamStatusItemReader awayTeamStatusItemReader;

    @Autowired
    public AwayTeamStatusProcessor(AwayTeamStatusItemReader awayTeamStatusItemReader) {
        this.awayTeamStatusItemReader = awayTeamStatusItemReader;
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
            tweet.setKeywordUsed(awayTeamStatusItemReader.getTeamKeyword());
            listOfTweets.add(tweet);
        }
        return listOfTweets;
    }
}
