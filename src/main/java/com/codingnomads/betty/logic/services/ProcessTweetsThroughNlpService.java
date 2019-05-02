package com.codingnomads.betty.logic.services;


import com.codingnomads.betty.data.models.Tweet;
import com.codingnomads.betty.logic.interfaces.TwitterJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessTweetsThroughNlpService {


    //Fields
    private TwitterJpaRepository twitterJpaRepository;

    //Constructor
    @Autowired
    public ProcessTweetsThroughNlpService(TwitterJpaRepository twitterJpaRepository) {
        this.twitterJpaRepository = twitterJpaRepository;
    }

    public List<Tweet> retrieveTweetsFromDatabase(String keyword) {
        List<Tweet> tweetList = twitterJpaRepository.findByKeywordUsedLike(keyword);
        return tweetList;
    }

}
