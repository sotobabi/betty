package com.codingnomads.betty.data.batch.batchreaders;

import com.codingnomads.betty.data.models.MatchOdds;
import com.codingnomads.betty.logic.services.GameInformationService;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MatchOddsReaderTests {
    private GameInformationService mockGameInformationService;
    private MatchOddsReader testMatchOddsReader;
    private MatchOdds matchOdds;

    @Before
    public void setUp(){

        mockGameInformationService = mock(GameInformationService.class);
        testMatchOddsReader = new MatchOddsReader(mockGameInformationService);
        matchOdds = new MatchOdds();
        matchOdds.setHomeTeam(testMatchOddsReader.getHomeTeam());
        matchOdds.setAwayTeam(testMatchOddsReader.getAwayTeam());
    }

    @Test
    public void whenReadRun_shouldReturnMatchOdds(){

        when(mockGameInformationService.getMatchOdds(testMatchOddsReader.getHomeTeam(), testMatchOddsReader.getAwayTeam()))
                .thenReturn(matchOdds);

        assertThat(testMatchOddsReader.read()).isEqualTo(matchOdds);
    }
}
