package com.codingnomads.betty.data.batch.tweetsjobs.hometeamjob;

import com.codingnomads.betty.data.models.Tweet;
import com.codingnomads.betty.logic.interfaces.TwitterJpaRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Qualifier("homeTeamTweets")
@Component
public class HomeTeamTweetsRdsWriter implements ItemWriter<List<Tweet>>{

    private TwitterJpaRepository twitterJpaRepository;

    @Autowired
    public HomeTeamTweetsRdsWriter(TwitterJpaRepository twitterJpaRepository) {
        this.twitterJpaRepository = twitterJpaRepository;
    }

    @Override
    public void write(List<? extends List<Tweet>> tweetsList) throws Exception {
        for (List<Tweet> tweets : tweetsList) {
            twitterJpaRepository.saveAll(tweets);
        }
    }
}
