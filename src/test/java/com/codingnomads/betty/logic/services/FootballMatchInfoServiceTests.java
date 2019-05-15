package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.logic.interfaces.FootballMatchesInfoJpaRepository;
import com.codingnomads.betty.logic.interfaces.GameInformationRepository;
import com.codingnomads.betty.logic.models.betAPImodels.EventJSON;
import com.codingnomads.betty.logic.models.betAPImodels.GameInformationJSON;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FootballMatchInfoServiceTests {

    private GameInformationRepository mockGameInformationRepository;
    private FootballMatchesInfoJpaRepository mockFootballMatchesInfoJpaRepository;
    private FootballMatchInfoService testFootballMatchInfoService;
    private GameInformationJSON gameInformationJSON;
    private EventJSON eventJSON;
    private static final String matchName = "home vs away";
    private static final String date = "2019-05-17T02:00:00.000Z";
    private static final String id = "1111111";

    @Before
    public void setUp(){

        mockGameInformationRepository = mock(GameInformationRepository.class);
        mockFootballMatchesInfoJpaRepository = mock(FootballMatchesInfoJpaRepository.class);
        testFootballMatchInfoService = new FootballMatchInfoService(mockGameInformationRepository, mockFootballMatchesInfoJpaRepository);

        eventJSON = new EventJSON();
        eventJSON.setName(matchName);
        eventJSON.setStart(date);
        eventJSON.setId(id);

        gameInformationJSON = new GameInformationJSON();
        gameInformationJSON.setEventJSONS(Arrays.asList(eventJSON));
    }

    @Test
    public void whenGetFootballMatchListFromApiRun_shouldReturnFootballMatchInfoList() throws ParseException {

        when(mockGameInformationRepository.getGameInformation()).thenReturn(gameInformationJSON);

        assertThat(testFootballMatchInfoService.getFootballMatchListFromApi()).isInstanceOf(List.class);

    }
}
