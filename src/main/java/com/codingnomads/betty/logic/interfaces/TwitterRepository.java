package com.codingnomads.betty.logic.interfaces;

import com.codingnomads.betty.data.models.Tweet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TwitterRepository {
    void saveTweets(List<Tweet> listOfTweets);
}
