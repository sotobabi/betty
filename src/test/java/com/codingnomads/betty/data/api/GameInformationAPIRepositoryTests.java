package com.codingnomads.betty.data.api;

import com.codingnomads.betty.logic.exceptions.JSONNotFoundException;
import com.codingnomads.betty.logic.models.betAPImodels.EventJSON;
import com.codingnomads.betty.logic.models.betAPImodels.GameInformationJSON;
import com.codingnomads.betty.logic.models.betAPImodels.MarketJSON;
import com.codingnomads.betty.logic.models.betAPImodels.RunnerJSON;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GameInformationAPIRepositoryTests {

    private RestTemplate mockRestTemplate;
    private GameInformationAPIRepository testGameInformationAPIRepository;
    private static final String url = "https://api.matchbook.com/edge/rest/events?tag-url-names=united-states-of-america&sport-ids=15&states=open&exchange-type=back-lay&odds-type=DECIMAL&include-prices=false&price-depth=1&price-mode=aggregated&side=lay&currency=GBP&minimum-liquidity=10&include-event-participants=true&market-types=one_x_two&per-page=500";
    private static final String teamName = "team";
    private static final String matchName = "match";
    private static final String oddType = "Match Odds";
    private GameInformationJSON gameInformationJSON;
    private EventJSON eventJSON;
    private MarketJSON marketJSON;
    private RunnerJSON runnerJSON;


    @Before
    public void setUp(){

        mockRestTemplate = mock(RestTemplate.class);
        testGameInformationAPIRepository = new GameInformationAPIRepository(mockRestTemplate);

        gameInformationJSON = new GameInformationJSON();
        eventJSON = new EventJSON();
        marketJSON = new MarketJSON();
        runnerJSON = new RunnerJSON();

        runnerJSON.setName(teamName);

        marketJSON.setRunnerJSONS(Arrays.asList(runnerJSON));
        marketJSON.setName(oddType);

        eventJSON.setName(matchName);
        eventJSON.setMarketJSONS(Arrays.asList(marketJSON));

        gameInformationJSON.setEventJSONS(Arrays.asList(eventJSON));

    }

    @Test
    public void whenGetGameInformationRun_shouldReturnGameInformationJson(){

        testGameInformationAPIRepository.getGameInformation();

        verify(mockRestTemplate, times(1)).getForObject(url, GameInformationJSON.class);

    }

    @Test
    public void whenGetEventJSONforMatchRun_shouldReturnEventJSON(){

        assertThat(testGameInformationAPIRepository.getEventJSONforMatch(matchName, gameInformationJSON)).isEqualTo(eventJSON);
    }

    @Test
    public void whenGetMarketJSONforMatchRun_shouldReturnMarketJSON(){

        assertThat(testGameInformationAPIRepository.getMarketJSONforMatch(eventJSON)).isEqualTo(marketJSON);
    }

    @Test
    public void whenGetRunnerJSONforMarketRun_shouldReturnRunnerJSON(){

        assertThat(testGameInformationAPIRepository.getRunnerJSONforMarket(marketJSON, teamName));
    }

    @Test(expected = JSONNotFoundException.class)
    public void whenInvalidMatchNamePassToGetEventJSONforMatchMethod_shouldThrownException(){

        testGameInformationAPIRepository.getEventJSONforMatch("Invalid Match", gameInformationJSON);
    }

    @Test(expected = JSONNotFoundException.class)
    public void whenInvalidEventJSONPassToGetMarketJSONforMatchMethod_shouldThrownException(){

        testGameInformationAPIRepository.getMarketJSONforMatch(mock(EventJSON.class));
    }

    @Test(expected = JSONNotFoundException.class)
    public void whenInvalidTeamNamePassToGetRunnerJSONforMarketMethod_shouldThrownException(){

        testGameInformationAPIRepository.getRunnerJSONforMarket(marketJSON, "Invalid Name");
    }
}
