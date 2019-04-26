package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.data.api.GameInformationAPIRepository;
import com.codingnomads.betty.logic.exceptions.JSONNotFoundException;
import com.codingnomads.betty.logic.interfaces.GameInformationRepository;
import com.codingnomads.betty.logic.models.betAPImodels.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameInformationServiceTest {

    private GameInformationAPIRepository mockGameInformationAPIRepository;
    private GameInformationRepository mockGameInformationRepository;
    private GameInformationService testGameInformationService;
    private Map<String, Double> map = new HashMap<>();
    private static final String teamName1 = "team1";
    private static final  String teamName2 = "team2";
    private static final String eventName = "team1 vs team2";
    private static final String marketName = "Match Odds";
    private static final double oddForTeam1 = 1.0;
    private static final double oddForTeam2 = 2.0;
    private GameInformationJSON gameInformationJSON;

    @Before
    public void setUp(){
        mockGameInformationAPIRepository = mock(GameInformationAPIRepository.class);
        mockGameInformationRepository = mock(GameInformationRepository.class);
        testGameInformationService = new GameInformationService(mockGameInformationRepository);

        gameInformationJSON = createGameInformationJSON();

        map.put(teamName1, oddForTeam1);
        map.put(teamName2, oddForTeam2);
    }

    @Test
    public void whenGetOddsByMatchRun_shouldReturnHashMapContainsTeamNameAndOdds(){

        when(mockGameInformationRepository.getGameInformation()).thenReturn(gameInformationJSON);

        assertThat(testGameInformationService.getOddsByMatch(teamName1, teamName2))
                .matches((map)-> map.get(teamName1) == 1.0);

        assertThat(testGameInformationService.getOddsByMatch(teamName1, teamName2))
                .matches((map)-> map.get(teamName2) == 2.0);
    }

    @Test(expected = JSONNotFoundException.class)
    public void whenGivenInvalidTeamName_shouldThrownException(){

        when(mockGameInformationRepository.getGameInformation()).thenReturn(gameInformationJSON);

        testGameInformationService.getOddsByMatch("wrong1", "wrong2");
    }

    private GameInformationJSON createGameInformationJSON(){

        PriceJSON priceJSONforTeam1 = createPriceJSON(oddForTeam1);
        PriceJSON priceJSONforTeam2 = createPriceJSON(oddForTeam2);

        RunnerJSON runnerJSONforTeam1 = createRunnerJSON(teamName1, priceJSONforTeam1);
        RunnerJSON runnerJSONforTeam2 = createRunnerJSON(teamName2, priceJSONforTeam2);

        MarketJSON marketJSON = createMarketJSON(marketName, runnerJSONforTeam1, runnerJSONforTeam2);

        EventJSON eventJSON = createEventJSON(eventName, marketJSON);

        GameInformationJSON gameInformationJSON = new GameInformationJSON();
        gameInformationJSON.setEventJSONS(Arrays.asList(eventJSON));

        return gameInformationJSON;
    }

    private EventJSON createEventJSON(String eventName, MarketJSON marketJSON){

        EventJSON eventJSON = new EventJSON();
        eventJSON.setName(eventName);
        eventJSON.setMarketJSONS(Arrays.asList(marketJSON));

        return eventJSON;
    }

    private MarketJSON createMarketJSON(String marketName, RunnerJSON runner1, RunnerJSON runner2){

        MarketJSON marketJSON = new MarketJSON();
        marketJSON.setName(marketName);
        marketJSON.setRunnerJSONS(Arrays.asList(runner1, runner2));

        return marketJSON;
    }

    private RunnerJSON createRunnerJSON(String teamName, PriceJSON price){

        RunnerJSON runnerJSON = new RunnerJSON();

        runnerJSON.setName(teamName);
        runnerJSON.setPriceJSONS(Arrays.asList(price));

        return runnerJSON;

    }

    private PriceJSON createPriceJSON(double odds){

        PriceJSON priceJSON = new PriceJSON();
        priceJSON.setOdds(odds);

        return priceJSON;
    }
}