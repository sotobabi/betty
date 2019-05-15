package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.data.models.MatchOdds;
import com.codingnomads.betty.logic.interfaces.MatchOddsJpaRepository;
import com.codingnomads.betty.logic.interfaces.TwitterKeyWordRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TwitterQueryConstructor {

    //MatchOdds -> finds the corresponding teams' hashtags and accounts and construct query

    //teamName -awayTeamName from a list of accounts
    //awayTeamName -teamName from a list of accounts

    private MatchOddsJpaRepository matchOddsJpaRepository;
    private TwitterKeyWordRepository twitterKeyWordRepository;

    @Autowired
    public TwitterQueryConstructor(MatchOddsJpaRepository matchOddsJpaRepository, TwitterKeyWordRepository twitterKeyWordRepository) {
        this.matchOddsJpaRepository = matchOddsJpaRepository;
        this.twitterKeyWordRepository = twitterKeyWordRepository;
    }

    private List<String> constructQueryPrefixFromTeamNames(){
        List<MatchOdds> matches = matchOddsJpaRepository.findAll();
        List<String> queryPrefixList = new ArrayList<>();
        for(MatchOdds match : matches){
            String homeTeam = match.getHomeTeam();
            String awayTeam = match.getAwayTeam();
            queryPrefixList.add(homeTeam + " -" + awayTeam);
            queryPrefixList.add(awayTeam + " -"+homeTeam);

        }
        return queryPrefixList;
    }

    private List<String> searchForAccountsWithTeamNames(List<MatchOdds> matches){
        for(MatchOdds match: matches){
            String homeTeam = match.getHomeTeam();
            twitterKeyWordRepository.getAccountsWithTeamName(homeTeam);
        }
        return null;
    }

    private List<MatchOdds> getMostRecentMatches(){
        return matchOddsJpaRepository.findAll();
    }

}
