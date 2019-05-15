package com.codingnomads.betty.data.batch.batchreaders;

import com.codingnomads.betty.data.batch.footballmatchjob.FootballMatchApiReader;
import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.logic.services.FootballMatchInfoService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FootballMatchApireaderTests {

    private FootballMatchInfoService mockFootballMatchInfoService;
    private FootballMatchApiReader testFootballMatchApiReader;
    private FootballMatchInfo mockFootballMatchInfo;
    private List<FootballMatchInfo> list;


    @Before
    public void setUp(){

        mockFootballMatchInfoService = mock(FootballMatchInfoService.class);
        mockFootballMatchInfo = mock(FootballMatchInfo.class);
        testFootballMatchApiReader = new FootballMatchApiReader(mockFootballMatchInfoService);

        list = Arrays.asList(mockFootballMatchInfo);
    }

    @Test
    public void whenReadRun_shouldReturnFootballMatchInfoList() throws Exception {

        when(mockFootballMatchInfoService.getFootballMatchListFromApi()).thenReturn(list);

        assertThat(testFootballMatchApiReader.read()).isEqualTo(list);
    }
}
