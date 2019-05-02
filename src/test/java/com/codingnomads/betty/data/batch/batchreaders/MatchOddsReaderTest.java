//package com.codingnomads.betty.data.batch.batchreaders;
//
//import com.codingnomads.betty.data.models.MatchOdds;
//import com.codingnomads.betty.logic.services.GameInformationService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class MatchOddsReaderTest {
//
//    private GameInformationService mockGameInformationService;
//    private MatchOddsReader testMatchOddsReader;
//
//    @Before
//    public void setUp(){
//        mockGameInformationService = mock(GameInformationService.class);
//        testMatchOddsReader = new MatchOddsReader(mockGameInformationService);
//        testMatchOddsReader.setBatchJobState(false);
//    }
//
//    @Test
//    public void whenRunReadMethod_shouldReturnMatchOdds(){
//
//        MatchOdds matchOdds = new MatchOdds();
//        matchOdds.setHomeTeam("home");
//        matchOdds.setAwayTeam("away");
//
//        when(mockGameInformationService.getMatchOdds("home", "away"))
//                .thenReturn(matchOdds);
//
//        assertThat(testMatchOddsReader.read()).isEqualTo(matchOdds);
//    }
//}
