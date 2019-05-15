package com.codingnomads.betty.data.batch.batchreaders;

import com.codingnomads.betty.data.batch.matchoddsjob.FootballMatchJpaReader;
import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.logic.services.FootballMatchInfoService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FootballMatchJpaReaderTests {

    private FootballMatchInfoService mockFootballMatchInfoService;
    private FootballMatchJpaReader testFootballMatchJpaReader;
    private FootballMatchInfo matchInfo;
    private List<FootballMatchInfo> matchInfoList;

    @Before
    public void setUp(){

        mockFootballMatchInfoService = mock(FootballMatchInfoService.class);
        testFootballMatchJpaReader = new FootballMatchJpaReader(mockFootballMatchInfoService);
        matchInfo = mock(FootballMatchInfo.class);
        matchInfoList = Arrays.asList(matchInfo);

    }

    @Test
    public void whenReadRun_shouldReturnFootballMatchInfoList(){

        when(mockFootballMatchInfoService.findLatestFootballMatchesFromDb()).thenReturn(matchInfoList);

        assertThat(testFootballMatchJpaReader.read()).isEqualTo(matchInfoList);
    }
}
