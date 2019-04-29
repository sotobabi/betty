package com.codingnomads.betty.data.batch;

import com.codingnomads.betty.data.batch.models.StatusItemReader;
import com.codingnomads.betty.data.models.Tweet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;

@Component
public class Processor implements ItemProcessor<List<Status>, List<Tweet>> {

    @Autowired
    private StatusItemReader statusItemReader;

    @Override
    public List<Tweet> process(List<Status> statusList) throws Exception {
        List<Tweet> listOfTweets = new ArrayList<>();

        for (Status status : statusList) {
            if (status == null) {
                return null;
            }
            Tweet tweet = new Tweet();
            tweet.setCreatedAt(status.getCreatedAt());
            tweet.setText(status.getText());
            tweet.setLanguage(status.getLang());
            tweet.setKeywordUsed(statusItemReader.getKeyword());
            listOfTweets.add(tweet);
        }
        return listOfTweets;
    }
}
