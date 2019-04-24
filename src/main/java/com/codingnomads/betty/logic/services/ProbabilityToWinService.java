package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.logic.exceptions.InvalidScoreException;
import com.codingnomads.betty.logic.models.TeamProbabilityToWin;
import com.codingnomads.betty.logic.models.TeamSentimentScore;
import org.springframework.stereotype.Service;

@Service
public class ProbabilityToWinService {

    public TeamProbabilityToWin getProbabilityToWinFromSentimentAnalysis(TeamSentimentScore teamSentimentScore) {
        if (teamSentimentScore.getScore() == null) {
            throw new NullPointerException("Team Score is null");
        }

        return createTeamProbability(teamSentimentScore);
    }

    private TeamProbabilityToWin createTeamProbability(TeamSentimentScore teamSentimentScore) {

        Integer teamScore = teamSentimentScore.getScore();

        TeamProbabilityToWin teamProbabilityToWin = new TeamProbabilityToWin();
        teamProbabilityToWin.setTeamName(teamSentimentScore.getTeamName());
        teamProbabilityToWin.setMatchDateTime(teamSentimentScore.getMatchDateTime());
        teamProbabilityToWin.setPlayingHome(teamSentimentScore.getPlayingHome());
        teamProbabilityToWin.setProbabilityToWin(transformScoreToProbability(teamScore));

        return teamProbabilityToWin;
    }

    private Double transformScoreToProbability(Integer teamScore) {
        if (teamScore <= 0) {
            throw new InvalidScoreException("Team Score cannot be negative");
        }

        return (double) (teamScore/100);
    }



}
