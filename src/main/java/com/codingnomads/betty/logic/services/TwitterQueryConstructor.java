package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.logic.interfaces.TwitterKeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TwitterQueryConstructor {

    private TwitterKeywordRepository twitterKeyWordRepository;

    @Autowired
    public TwitterQueryConstructor(TwitterKeywordRepository twitterKeyWordRepository) {
        this.twitterKeyWordRepository = twitterKeyWordRepository;
    }

    public List<String> constructQueriesWithTeamNames(String searchTeam, String opponentTeam) {
        List<String> queries = new ArrayList<>();

        String prefix = constructQueryPrefix(searchTeam, opponentTeam);

        List<String> suffixes = constructQuerySuffix(searchTeam);
        for (String suffix : suffixes) {
            String query = prefix + suffix;
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
