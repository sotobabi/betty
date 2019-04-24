package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.logic.repositories.TwitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Status;
import java.util.List;

@Service
public class TwitterService {

    @Autowired
    private TwitterRepository twitterRepository;

    public List<Status> searchTweets(String keyword, int numberOfStatus){

        return twitterRepository.searchTweets(keyword, numberOfStatus);
    }
}
