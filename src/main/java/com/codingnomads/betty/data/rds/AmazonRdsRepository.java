package com.codingnomads.betty.data.rds;

import com.codingnomads.betty.data.models.Tweet;
import com.codingnomads.betty.logic.interfaces.TwitterJpaRepository;
import com.codingnomads.betty.logic.interfaces.TwitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AmazonRdsRepository implements TwitterRepository {

    @Autowired
    private TwitterJpaRepository twitterJpaRepository;

    @Override
    public void saveTweets(List<Tweet> listOfTweets) {
        saveListToDatabaseAndFlush(listOfTweets);
    }

    private void saveListToDatabaseAndFlush(List<Tweet> listOfTweets) {
        twitterJpaRepository.saveAll(listOfTweets);
        twitterJpaRepository.flush();
    }


}
