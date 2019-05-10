package com.codingnomads.betty.data.batch.batchwriters;

import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.logic.interfaces.FootballMatchesInfoJpaRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class FootballMatchRdsWriterTests {

    private FootballMatchesInfoJpaRepository mockFootballMatchesInfoJpaRepository;
    private FootballMatchRdsWriter testFootballMatchRdsWriter;
    private FootballMatchInfo mockFootballMatchInfo;
    private List<FootballMatchInfo> matchList;
    private List<List<FootballMatchInfo>> items;

    @Before
    public void setUp(){

        mockFootballMatchesInfoJpaRepository = mock(FootballMatchesInfoJpaRepository.class);
        testFootballMatchRdsWriter = new FootballMatchRdsWriter(mockFootballMatchesInfoJpaRepository);

        mockFootballMatchInfo = mock(FootballMatchInfo.class);

        matchList = Arrays.asList(mockFootballMatchInfo);
        items = Arrays.asList(matchList);
    }

    @Test
    public void whenWriteRun_shouldCallMatchesInfoJpaRepository() throws Exception {

        testFootballMatchRdsWriter.write(items);

        verify(mockFootballMatchesInfoJpaRepository, times(1)).saveAll(matchList);


    }
}
