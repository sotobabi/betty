package com.codingnomads.betty.data.batch.batchreaders;

import com.codingnomads.betty.logic.interfaces.TwitterMinerRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.Status;

import java.util.List;

@Component
public class StatusItemReader implements ItemReader<List<Status>> {

    TwitterMinerRepository twitterMinerRepository;
    private String keyword;
    private int numberOfStatus;
    private boolean batchJobState = false;

    //TODO: Keyword and status should not be hardcoded
    @Autowired
    public StatusItemReader(TwitterMinerRepository twitterMinerRepository) {
        this.twitterMinerRepository = twitterMinerRepository;
        this.keyword = "cat";
    }

    @Override
    public List<Status> read()
            throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (!batchJobState) {
            batchJobState = true;
            return twitterMinerRepository.searchTweets(keyword,numberOfStatus);
        }

        batchJobState = false;

        return null;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getNumberOfStatus() {
        return numberOfStatus;
    }

    public void setNumberOfStatus(int numberOfStatus) {
        this.numberOfStatus = numberOfStatus;
    }
}
