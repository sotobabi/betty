package com.codingnomads.betty.data.batch.batchprocessors;

import com.codingnomads.betty.data.batch.matchoddsjob.MatchToOddsProcessor;
import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.data.models.MatchOdds;
import com.codingnomads.betty.logic.services.MatchOddsService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MatchToOddsProcessorTests {


    private MatchOddsService mockMatchOddsService;
    private MatchToOddsProcessor testMatchToOddsProcessor;
    private MatchOdds mockMatchOdds;
    private FootballMatchInfo mockFootballMatchInfo;
    private List<FootballMatchInfo> matchInfoList;
    private List<MatchOdds> matchOddsList;
    private String matchName = "match";

    @Before
    public void setUp(){
        mockMatchOddsService = mock(MatchOddsService.class);
        testMatchToOddsProcessor = new MatchToOddsProcessor(mockMatchOddsService);

        mockMatchOdds = mock(MatchOdds.class);
        mockFootballMatchInfo = mock(FootballMatchInfo.class);
        mockFootballMatchInfo.setName(matchName);

        matchInfoList = Arrays.asList(mockFootballMatchInfo);
        matchOddsList = Arrays.asList(mockMatchOdds);
    }

    @Test
    public void whenRunProcessMethodWithMatchOddsItem_shouldReturnMatchOddsItem() throws Exception {

        when(mockMatchOddsService.createMatchOdds(mockFootballMatchInfo.getName())).thenReturn(mockMatchOdds);

        assertThat(testMatchToOddsProcessor.process(matchInfoList)).isEqualTo(matchOddsList);
    }
}
