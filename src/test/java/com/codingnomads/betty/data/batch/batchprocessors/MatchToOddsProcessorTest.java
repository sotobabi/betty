package com.codingnomads.betty.data.batch.batchprocessors;

import com.codingnomads.betty.data.models.MatchOdds;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class MatchToOddsProcessorTest {

    private MatchToOddsProcessor testMatchToOddsProcessor;

    @Before
    public void setUp(){

        testMatchToOddsProcessor = new MatchToOddsProcessor();
    }

    @Test
    public void whenRunProcessMethodWithMatchOddsItem_shouldReturnMatchOddsItem() {

        MatchOdds matchOdds = new MatchOdds();
        matchOdds.setHomeTeam("team");

        assertThat(testMatchToOddsProcessor.process(matchOdds)).isEqualTo(matchOdds);
    }
}
