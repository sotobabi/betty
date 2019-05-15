package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.data.models.MatchOdds;
import com.codingnomads.betty.logic.interfaces.GameInformationRepository;
import com.codingnomads.betty.logic.interfaces.MatchOddsJpaRepository;
import com.codingnomads.betty.logic.models.betAPImodels.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MatchOddsServiceTests {

    private GameInformationRepository mockGameInformationRepository;
    private MatchOddsJpaRepository mockMatchOddsJpaRepository;
    private MatchOddsService testMatchOddsService;
    private static final String matchName = "home vs away";
    private static final String homeTeam = "home";
    private static final String awayTeam = "away";
    private static final String dateTime = "2019-05-16T00:00:00.000Z";
    private static final double homeOdd = 1.0;
    private static final double awayOdd = 2.0;
    private GameInformationJSON gameInformationJSON;
    private EventJSON eventJSON;
    private MarketJSON marketJSON;
    private RunnerJSON homeRunnerJSON;
    private RunnerJSON awayRunnerJSON;
    private PriceJSON homePriceJSON;
    private PriceJSON awayPriceJSON;
    private List<MatchOdds> list;
    private MatchOdds mockMatchOdds;


    @Before
    public void setUp(){

        mockGameInformationRepository = mock(GameInformationRepository.class);
        mockMatchOddsJpaRepository = mock(MatchOddsJpaRepository.class);
        testMatchOddsService = new MatchOddsService(mockGameInformationRepository,mockMatchOddsJpaRepository);

        homePriceJSON = new PriceJSON();
        homePriceJSON.setOdds(homeOdd);

        awayPriceJSON = new PriceJSON();
        awayPriceJSON.setOdds(awayOdd);

        homeRunnerJSON = new RunnerJSON();
        homeRunnerJSON.setName(homeTeam);
        homeRunnerJSON.setPriceJSONS(Arrays.asList(homePriceJSON));

        awayRunnerJSON = new RunnerJSON();
        awayRunnerJSON.setName(awayTeam);
        awayRunnerJSON.setPriceJSONS(Arrays.asList(awayPriceJSON));

        marketJSON = new MarketJSON();
        marketJSON.setName(matchName);
        marketJSON.setRunnerJSONS(Arrays.asList(homeRunnerJSON, awayRunnerJSON));

        eventJSON = new EventJSON();
        eventJSON.setName(matchName);
        eventJSON.setMarketJSONS(Arrays.asList(marketJSON));
        eventJSON.setStart(dateTime);

        gameInformationJSON = new GameInformationJSON();
        gameInformationJSON.setEventJSONS(Arrays.asList(eventJSON));

        mockMatchOdds = mock(MatchOdds.class);
        list = Arrays.asList(mockMatchOdds);

    }

    @Test
    public void whenCreateMatchOddsRun_shouldCallGameInformationRepositoryMethods(){

        when(mockGameInformationRepository.getGameInformation()).thenReturn(gameInformationJSON);
        when(mockGameInformationRepository.getEventJSONforMatch(matchName,gameInformationJSON)).thenReturn(eventJSON);
        when(mockGameInformationRepository.getMarketJSONforMatch(eventJSON)).thenReturn(marketJSON);
        when(mockGameInformationRepository.getRunnerJSONforMarket(marketJSON,homeTeam)).thenReturn(homeRunnerJSON);
        when(mockGameInformationRepository.getRunnerJSONforMarket(marketJSON, awayTeam)).thenReturn(awayRunnerJSON);

        assertThat(testMatchOddsService.createMatchOdds(matchName)).isInstanceOf(MatchOdds.class);
    }

    @Test
    public void whenSaveMatchOddsListRun_shouldCallFindLatestInstanceInMatchOddsTableMethod(){

        when(mockMatchOddsJpaRepository.saveAll(list)).thenReturn(list);

        testMatchOddsService.saveMatchOddsList(list);

        verify(mockMatchOddsJpaRepository, times(1)).saveAll(list);

    }

    @Test
    public void whenFindMostRecentMatchesAndOddsRun_shouldReturnList(){

        when(mockMatchOddsJpaRepository.findAll()).thenReturn(list);

        assertThat(testMatchOddsService.findMostRecentMatchesAndOdds()).isEqualTo(list);
    }
}
