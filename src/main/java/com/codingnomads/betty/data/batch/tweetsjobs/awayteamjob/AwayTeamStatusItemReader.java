package com.codingnomads.betty.data.batch.tweetsjobs.awayteamjob;

import com.codingnomads.betty.logic.interfaces.MatchOddsJpaRepository;
import com.codingnomads.betty.logic.interfaces.TwitterMinerRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import twitter4j.Status;

import java.util.List;

@Qualifier("awayTeamTweets")
@Component
public class AwayTeamStatusItemReader implements ItemReader<List<Status>> {

    private TwitterMinerRepository twitterMinerRepository;
    private MatchOddsJpaRepository matchOddsJpaRepository;
    private String teamKeyword;
    private int numberOfStatus;
    private boolean batchJobState = false;

    @Autowired
    public AwayTeamStatusItemReader(TwitterMinerRepository twitterMinerRepository,
                                    MatchOddsJpaRepository matchOddsJpaRepository) {
        this.twitterMinerRepository = twitterMinerRepository;
        this.matchOddsJpaRepository = matchOddsJpaRepository;
    }

    @Override
    public List<Status> read()
            throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (!batchJobState) {
            batchJobState = true;
            setTeamKeyword(matchOddsJpaRepository.findLatestInstanceInMatchOddsTable().getAwayTeam());
            return getTweetsForTeam();
        }
        batchJobState = false;
        return null;
    }

    private List<Status> getTweetsForTeam() {
        return twitterMinerRepository.searchTweets(getTeamKeyword(), numberOfStatus);
    }

    public String getTeamKeyword() {
        return teamKeyword;
    }

    public void setTeamKeyword(String teamKeyword) {
        this.teamKeyword = teamKeyword;
    }

    public int getNumberOfStatus() {
        return numberOfStatus;
    }

    public void setNumberOfStatus(int numberOfStatus) {
        this.numberOfStatus = numberOfStatus;
    }
}
