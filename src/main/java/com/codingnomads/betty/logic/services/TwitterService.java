package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.logic.interfaces.TwitterMinerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwitterService {

    private TwitterMinerRepository twitterMinerRepository;

    @Autowired
    public TwitterService(TwitterMinerRepository twitterMinerRepository) {
        this.twitterMinerRepository = twitterMinerRepository;
    }

    public void searchTweetFromAccounts(String keyword, String startDate){
        twitterMinerRepository.searchTweetFromAccounts(keyword, startDate);
    }

}
