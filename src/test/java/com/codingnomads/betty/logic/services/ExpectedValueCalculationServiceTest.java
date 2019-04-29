package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.logic.models.TeamProbabilityToWin;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpectedValueCalculationServiceTest {

    private GameInformationService mockGameInformationService;
    private AnalyzeTweetsService mockAnalyzeTweetsService;
    private ExpectedValueCalculationService testExpectedValueCalculationService;
    private static final String teamName1 = "team1";
    private static final String teamName2 = "team2";
    private TeamProbabilityToWin teamProbabilityToWin1;
    private TeamProbabilityToWin teamProbabilityToWin2;
    private Map<String, Double> matchOdds;

    @Before
    public void setUp(){
        mockGameInformationService = mock(GameInformationService.class);
        mockAnalyzeTweetsService = mock(AnalyzeTweetsService.class);
        testExpectedValueCalculationService = new ExpectedValueCalculationService(mockAnalyzeTweetsService, mockGameInformationService);

        teamProbabilityToWin1 = new TeamProbabilityToWin();
        teamProbabilityToWin1.setTeamName(teamName1);
        teamProbabilityToWin1.setProbabilityToWin(0.1);

        teamProbabilityToWin2 = new TeamProbabilityToWin();
        teamProbabilityToWin2.setTeamName(teamName2);
        teamProbabilityToWin2.setProbabilityToWin(0.2);

        matchOdds = new HashMap<>();
        matchOdds.put(teamName1, 2.0);
        matchOdds.put(teamName2, 3.0);
    }


    @Test
    public void whenRunCalculateExpectedValue_shouldReturnExpectedValue(){

        when(mockAnalyzeTweetsService.calculateProbabilityWithTweets(teamName1)).thenReturn(teamProbabilityToWin1);
        when(mockAnalyzeTweetsService.calculateProbabilityWithTweets(teamName2)).thenReturn(teamProbabilityToWin2);

        when(mockGameInformationService.getOddsByMatch(teamName1,teamName2)).thenReturn(matchOdds);

        assertThat(testExpectedValueCalculationService.calculateExpectedValue(teamName1, teamName2))
                .containsKeys(teamName1,teamName2).containsValues(-2.6, -1.2000000000000002);

    }
}
