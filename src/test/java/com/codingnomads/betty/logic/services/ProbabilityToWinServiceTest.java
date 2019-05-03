package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.logic.exceptions.InvalidScoreException;
import com.codingnomads.betty.logic.models.TeamSentimentScore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProbabilityToWinServiceTest {

    private ProbabilityToWinService service;

    @Before
    public void setUp() {
        service = new ProbabilityToWinService();
    }

    @Test(expected = NullPointerException.class)
    public void ifTeamSentimentScoreIsNull_throwANullPointerException() {
        TeamSentimentScore teamSentimentScore = new TeamSentimentScore();
        teamSentimentScore.setScore(null);

        service.getProbabilityToWinFromSentimentAnalysis(teamSentimentScore);

    }

    @Test
    public void givenAPositiveTeamSentimentScore_aTeamProbabilityToWinBetween0And100IsReturned() {
        TeamSentimentScore givenTeamSentiment = new TeamSentimentScore();
        givenTeamSentiment.setScore(227.0);

        assertThat(service.getProbabilityToWinFromSentimentAnalysis(givenTeamSentiment).getProbabilityToWin())
                .isEqualTo(givenTeamSentiment.getScore()/100.00);

    }

    @Test(expected = InvalidScoreException.class)
    public void givenANegativeTeamSentimentScore_anInvalidScoreExceptionIsThrown() {
        TeamSentimentScore givenTeamSentiment = new TeamSentimentScore();
        givenTeamSentiment.setScore(-185.0);

        service.getProbabilityToWinFromSentimentAnalysis(givenTeamSentiment);

    }

}
