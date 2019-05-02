package com.codingnomads.betty.data.batch.batchprocessors;

import com.codingnomads.betty.data.batch.processors.MatchOddsBatchProcessor;
import com.codingnomads.betty.data.models.MatchOdds;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchOddsBatchProcessorTest {

    private MatchOddsBatchProcessor testMatchOddsBatchProcessor;

    @Before
    public void setUp(){

        testMatchOddsBatchProcessor = new MatchOddsBatchProcessor();
    }

    @Test
    public void whenRunProcessMethodWithMatchOddsItem_shouldReturnMatchOddsItem() {

        MatchOdds matchOdds = new MatchOdds();
        matchOdds.setHomeTeam("team");

        assertThat(testMatchOddsBatchProcessor.process(matchOdds)).isEqualTo(matchOdds);

    }


}
