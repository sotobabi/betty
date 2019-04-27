package com.codingnomads.betty.logic.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameInformationServiceTest {

    private Map<String, Double> map = new HashMap<>();

    @Mock
    private GameInformationService mockGameInformationService;

    @Before
    public void setUp(){
        map.put("team1", 1.0);
        map.put("team2", 2.0);
    }

    @Test
    public void whenGetOddsByMatchRun_shouldReturnHashMapContainsTeamNameAndOdds(){

        when(mockGameInformationService.getOddsByMatch("team1", "team2"))
                .thenReturn(map);

        assertThat(mockGameInformationService.getOddsByMatch("team1", "team2")).containsKeys("team1", "team2");
        assertThat(mockGameInformationService.getOddsByMatch("team1", "team2")).containsValues(1.0,2.0);

    }
}
