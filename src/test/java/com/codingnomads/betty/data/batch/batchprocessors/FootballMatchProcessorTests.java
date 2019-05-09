package com.codingnomads.betty.data.batch.batchprocessors;

import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.logic.interfaces.FootballMatchesInfoJpaRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FootballMatchProcessorTests {

    private FootballMatchesInfoJpaRepository mockMatchesInfoJpaRepository;
    private FootballMatchProcessor testFootballMatchProcessor;
    private FootballMatchInfo mockFootballMatchInfo1;
    private FootballMatchInfo mockFootballMatchInfo2;
    private FootballMatchInfo mockFootballMatchInfo3;
    private List<FootballMatchInfo> apiList;
    private List<FootballMatchInfo> dbList;
    private List<FootballMatchInfo> newList;


    @Before
    public void setUp(){

        mockMatchesInfoJpaRepository = mock(FootballMatchesInfoJpaRepository.class);
        testFootballMatchProcessor = new FootballMatchProcessor(mockMatchesInfoJpaRepository);

        mockFootballMatchInfo1 = new FootballMatchInfo();
        mockFootballMatchInfo2 = new FootballMatchInfo();
        mockFootballMatchInfo3 = new FootballMatchInfo();

        mockFootballMatchInfo1.setApi_id("1");

        mockFootballMatchInfo2.setApi_id("2");

        mockFootballMatchInfo3.setApi_id("3");

        apiList = Arrays.asList(mockFootballMatchInfo1, mockFootballMatchInfo2,mockFootballMatchInfo3);
        dbList = Arrays.asList(mockFootballMatchInfo1, mockFootballMatchInfo2);
        newList = Arrays.asList(mockFootballMatchInfo3);
    }

    @Test
    public void whenProcessRun_shouldReturnUniqueList() throws Exception {

        when(mockMatchesInfoJpaRepository.findByMatch_Date()).thenReturn(dbList);

        assertThat(testFootballMatchProcessor.process(apiList)).isEqualTo(newList);
    }
}
