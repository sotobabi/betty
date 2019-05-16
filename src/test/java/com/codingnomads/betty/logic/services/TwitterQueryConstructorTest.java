package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.logic.interfaces.TwitterKeywordRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TwitterQueryConstructorTest {

    private TwitterKeywordRepository mockTwitterKeywordRepository;
    private TwitterQueryConstructor testTwitterQueryConstructor;

    @Before
    public void setUp(){
        mockTwitterKeywordRepository = mock(TwitterKeywordRepository.class);
        testTwitterQueryConstructor = new TwitterQueryConstructor(mockTwitterKeywordRepository);
    }

    @Test
    public void whenConstructQueriesWithTeamNames_shouldReturnQuery() {
        String homeTeam = "homeTeam";
        String awayTeam = "awayTeam";
        List<String> accounts = new ArrayList<>();
        accounts.add("account1");

        when(mockTwitterKeywordRepository.getAccountsWithTeamName(homeTeam)).thenReturn(accounts);

        assertThat(testTwitterQueryConstructor.constructQueriesWithTeamNames(homeTeam,awayTeam).get(0).contains("account1"));

    }

}