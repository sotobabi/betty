//package com.codingnomads.betty.data.batch.batchreaders;
//
//import com.codingnomads.betty.data.models.MatchOdds;
//import com.codingnomads.betty.logic.services.GameInformationService;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class FootballMatchJpaReaderTests {
//    private GameInformationService mockGameInformationService;
//    private FootballMatchJpaReader testFootballMatchJpaReader;
//    private MatchOdds matchOdds;
//
//    @Before
//    public void setUp(){
//
//        mockGameInformationService = mock(GameInformationService.class);
//        testFootballMatchJpaReader = new FootballMatchJpaReader(mockGameInformationService);
//        matchOdds = new MatchOdds();
//        matchOdds.setHomeTeam(testFootballMatchJpaReader.getHomeTeam());
//        matchOdds.setAwayTeam(testFootballMatchJpaReader.getAwayTeam());
//    }
//
//    @Test
//    public void whenReadRun_shouldReturnMatchOdds(){
//
//        when(mockGameInformationService.getMatchOdds(testFootballMatchJpaReader.getHomeTeam(), testFootballMatchJpaReader.getAwayTeam()))
//                .thenReturn(matchOdds);
//
//        assertThat(testFootballMatchJpaReader.read()).isEqualTo(matchOdds);
//    }
//}
