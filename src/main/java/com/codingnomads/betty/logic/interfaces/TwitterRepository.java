package com.codingnomads.betty.logic.interfaces;

import twitter4j.Status;
import java.util.List;

public interface TwitterRepository {
    List<Status> searchTweets(String keyword, int numberOfStatus);
}