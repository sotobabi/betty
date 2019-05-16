package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.data.models.MatchOdds;
import com.codingnomads.betty.logic.interfaces.MatchOddsJpaRepository;
import com.codingnomads.betty.logic.interfaces.TwitterKeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TwitterQueryConstructor {

    //MatchOdds -> finds the corresponding teams' hashtags and accounts and construct query

    //teamName -awayTeamName from a list of accounts
    //awayTeamName -teamName from a list of accounts

    private TwitterKeywordRepository twitterKeyWordRepository;

    @Autowired
    public TwitterQueryConstructor(TwitterKeywordRepository twitterKeyWordRepository) {
        this.twitterKeyWordRepository = twitterKeyWordRepository;
    }

    public List<String> constructHomeTeamQueries(MatchOdds match) {
        List<String> queries = new ArrayList<>();
        String homeTeam = match.getHomeTeam();
        String awayTeam = match.getAwayTeam();
        String prefix = constructQueryPrefix(homeTeam, awayTeam);

        List<String> suffixes = constructQuerySuffix(homeTeam);
        for(String suffix : suffixes){
            String query = prefix + suffixes;
            queries.add(query);
        }
        return queries;
    }

    public List<String> constructAwayTeamQueries(MatchOdds match) {
        List<String> queries = new ArrayList<>();
        String homeTeam = match.getHomeTeam();
        String awayTeam = match.getAwayTeam();
        String prefix = constructQueryPrefix(awayTeam, homeTeam);

        List<String> suffixes = constructQuerySuffix(awayTeam);
        for(String suffix : suffixes){
            String query = prefix + suffixes;
            queries.add(query);
        }
        return queries;
    }

    private List<String> constructQuerySuffix(String teamName) {
        List<String> twitterAccounts = twitterKeyWordRepository.getAccountsWithTeamName(teamName);
        List<String> querySuffixes = new ArrayList<>();
        for (String account : twitterAccounts) {
            String querySuffix = teamName + " " + "from:" + account;
            querySuffixes.add(querySuffix);
        }
        return querySuffixes;
    }

    private String constructQueryPrefix(String includeTeam, String excludeTeam) {
        return includeTeam + " -" + excludeTeam;
    }
}
