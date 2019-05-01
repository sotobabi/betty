package com.codingnomads.betty.data.batch.batchwriters;

import com.codingnomads.betty.data.models.Tweet;
import com.codingnomads.betty.logic.interfaces.TwitterJpaRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RdsWriter implements ItemWriter<List<Tweet>>{

    private TwitterJpaRepository twitterJpaRepository;

    @Autowired
    public RdsWriter(TwitterJpaRepository twitterJpaRepository) {
        this.twitterJpaRepository = twitterJpaRepository;
    }

    @Override
    public void write(List<? extends List<Tweet>> tweetsList) throws Exception {
        for (List<Tweet> tweets : tweetsList) {
            twitterJpaRepository.saveAll(tweets);
        }
    }
}
