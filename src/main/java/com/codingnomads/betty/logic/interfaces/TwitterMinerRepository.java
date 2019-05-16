package com.codingnomads.betty.logic.interfaces;

import twitter4j.Status;
import java.util.List;

public interface TwitterMinerRepository {
    List<Status> searchTweets(String keyword, int numberOfStatus);
    List<Status> searchTweetFromAccounts(String keyword, String startDate);
}
