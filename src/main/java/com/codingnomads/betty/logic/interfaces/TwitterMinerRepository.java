package com.codingnomads.betty.logic.interfaces;

import com.codingnomads.betty.data.models.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import twitter4j.Status;
import java.util.List;
import java.util.Optional;

public interface TwitterMinerRepository {
    List<Status> searchTweets(String keyword, int numberOfStatus);
}
