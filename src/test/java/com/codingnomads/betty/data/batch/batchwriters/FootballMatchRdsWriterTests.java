package com.codingnomads.betty.data.batch.batchwriters;

import com.codingnomads.betty.data.batch.footballmatchjob.FootballMatchRdsWriter;
import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.logic.services.FootballMatchInfoService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class FootballMatchRdsWriterTests {

    private FootballMatchInfoService mockFootballMatchInfoService;
    private FootballMatchRdsWriter testFootballMatchRdsWriter;
    private FootballMatchInfo mockFootballMatchInfo;
    private List<FootballMatchInfo> matchList;
    private List<List<FootballMatchInfo>> items;

    @Before
    public void setUp(){

        mockFootballMatchInfoService = mock(FootballMatchInfoService.class);
        testFootballMatchRdsWriter = new FootballMatchRdsWriter(mockFootballMatchInfoService);

        mockFootballMatchInfo = mock(FootballMatchInfo.class);

        matchList = Arrays.asList(mockFootballMatchInfo);
        items = Arrays.asList(matchList);
    }

    @Test
    public void whenWriteRun_shouldCallMatchesInfoJpaRepository() throws Exception {

        testFootballMatchRdsWriter.write(items);

        verify(mockFootballMatchInfoService, times(1)).saveFootballMatchList(matchList);


    }
}
