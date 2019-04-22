package com.codingnomads.betty.logic.services;

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

        return calculateTeamProbabilityToWin(teamScore, teamProbabilityToWin);
    }

    private TeamProbabilityToWin calculateTeamProbabilityToWin(Integer teamScore, TeamProbabilityToWin teamProbabilityToWin) {
        if (teamScore <= 0) {
            return calculateTeamProbabilityWithNegativeScore(teamScore, teamProbabilityToWin);
        }
        teamProbabilityToWin.setProbabilityToWin((double) (teamScore/100));
        return teamProbabilityToWin;
    }

    private TeamProbabilityToWin calculateTeamProbabilityWithNegativeScore(Integer teamScore, TeamProbabilityToWin teamProbabilityToWin) {
        teamProbabilityToWin.setProbabilityToWin(0.00);
        return teamProbabilityToWin;
    }


}
