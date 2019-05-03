package com.codingnomads.betty.data.batch.batchprocessors;

import com.codingnomads.betty.data.models.MatchOdds;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

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
