package com.codingnomads.betty.data.batch.batchprocessors;

import com.codingnomads.betty.data.batch.matchoddsjob.MatchToOddsProcessor;
import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.data.models.MatchOdds;
import com.codingnomads.betty.logic.services.GameInformationService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MatchToOddsProcessorTests {


    private GameInformationService mockGameInformationService;
    private MatchToOddsProcessor testMatchToOddsProcessor;
    private MatchOdds mockMatchOdds;
    private FootballMatchInfo mockFootballMatchInfo;
    private List<FootballMatchInfo> matchInfoList;
    private List<MatchOdds> matchOddsList;

    @Before
    public void setUp(){
        mockGameInformationService = mock(GameInformationService.class);
        testMatchToOddsProcessor = new MatchToOddsProcessor(mockGameInformationService);

        mockMatchOdds = mock(MatchOdds.class);
        mockFootballMatchInfo = mock(FootballMatchInfo.class);

        matchInfoList = Arrays.asList(mockFootballMatchInfo);
        matchOddsList = Arrays.asList(mockMatchOdds);
    }

    @Test
    public void whenRunProcessMethodWithMatchOddsItem_shouldReturnMatchOddsItem() throws Exception {

        when(mockGameInformationService.createOddsListFromFootballList(matchInfoList)).thenReturn(matchOddsList);

        assertThat(testMatchToOddsProcessor.process(matchInfoList)).isEqualTo(matchOddsList);
    }
}
