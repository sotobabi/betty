package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.logic.models.TeamProbabilityToWin;
import com.codingnomads.betty.logic.models.TeamSentimentScore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProbabilityToWinServiceTests {

    @Test(expected = NullPointerException.class)
    public void ifTeamSentimentScoreIsNull_throwANullPointerException() {
        TeamSentimentScore teamSentimentScore = new TeamSentimentScore();
        teamSentimentScore.setScore(null);

        ProbabilityToWinService service = new ProbabilityToWinService();
        service.getProbabilityToWinFromSentimentAnalysis(teamSentimentScore);

    }

    @Test
    public void givenAPositiveTeamSentimentScore_aTeamProbabilityToWinBetween0And100IsReturned() {
        TeamSentimentScore givenTeamSentiment = new TeamSentimentScore();
        givenTeamSentiment.setScore(227);

        ProbabilityToWinService service = new ProbabilityToWinService();

        assertEquals(givenTeamSentiment.getScore() / 100,
                (double) service.getProbabilityToWinFromSentimentAnalysis(givenTeamSentiment).getProbabilityToWin(),
                0.0);
    }

    @Test
    public void givenANegativeTeamSentimentScore_aTeamProbabilityOfZeroIsReturned() {
        TeamSentimentScore givenTeamSentiment = new TeamSentimentScore();
        givenTeamSentiment.setScore(-185);

        ProbabilityToWinService service = new ProbabilityToWinService();

        assertTrue(service.getProbabilityToWinFromSentimentAnalysis(givenTeamSentiment)
                .getProbabilityToWin() >= 0.0);
    }




}
