package com.codingnomads.betty.data.batch.batchprocessors;

import com.codingnomads.betty.data.batch.footballmatchjob.FootballMatchProcessor;
import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.logic.services.FootballMatchInfoService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FootballMatchProcessorTests {

    private FootballMatchInfoService mockFootballMatchInfoService;
    private FootballMatchProcessor testFootballMatchProcessor;
    private FootballMatchInfo footballMatchInfo1;
    private FootballMatchInfo footballMatchInfo2;
    private FootballMatchInfo footballMatchInfo3;
    private List<FootballMatchInfo> apiList;
    private List<FootballMatchInfo> dbList;
    private List<FootballMatchInfo> newList;


    @Before
    public void setUp(){

        mockFootballMatchInfoService = mock(FootballMatchInfoService.class);
        testFootballMatchProcessor = new FootballMatchProcessor(mockFootballMatchInfoService);

        footballMatchInfo1 = new FootballMatchInfo();
        footballMatchInfo2 = new FootballMatchInfo();
        footballMatchInfo3 = new FootballMatchInfo();

        apiList = Arrays.asList(footballMatchInfo1, footballMatchInfo2, footballMatchInfo3);
        dbList = Arrays.asList(footballMatchInfo1, footballMatchInfo2);
        newList = Arrays.asList(footballMatchInfo3);
    }

    @Test
    public void whenProcessRun_shouldReturnUniqueList() throws Exception {

        footballMatchInfo1.setApi_id("1");

        footballMatchInfo2.setApi_id("2");

        footballMatchInfo3.setApi_id("3");

        when(mockFootballMatchInfoService.findLatestFootballMatchesFromDb()).thenReturn(dbList);

        assertThat(testFootballMatchProcessor.process(apiList)).isEqualTo(newList);
    }
}
