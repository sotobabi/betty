package com.codingnomads.betty.data.batch.batchreaders;

import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.logic.services.GameInformationService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FootballMatchApireaderTests {

    private GameInformationService mockGameInformationService;
    private FootballMatchApiReader testFootballMatchApiReader;
    private FootballMatchInfo mockFootballMatchInfo;
    private List<FootballMatchInfo> list;


    @Before
    public void setUp(){

        mockGameInformationService = mock(GameInformationService.class);
        mockFootballMatchInfo = mock(FootballMatchInfo.class);
        testFootballMatchApiReader = new FootballMatchApiReader(mockGameInformationService);

        list = Arrays.asList(mockFootballMatchInfo);
    }

    @Test
    public void whenReadRun_shouldReturnFootballMatchInfoList() throws Exception {

        when(mockGameInformationService.getFootballMatchListFromApi()).thenReturn(list);

        assertThat(testFootballMatchApiReader.read()).isEqualTo(list);
    }
}
