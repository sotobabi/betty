package com.codingnomads.betty.data.repositories;

import com.codingnomads.betty.data.models.Tweet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TwitterRepository {
    Boolean saveTweets(List<Tweet> listOfTweets);
}
