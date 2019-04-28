package com.codingnomads.betty.logic.models;

import java.time.LocalDateTime;

public class TeamSentimentScore {

    private LocalDateTime analysisDateTime;
    private LocalDateTime matchDateTime;
    private String teamName;
    private Double score;
    private Boolean isPlayingHome;

    public LocalDateTime getAnalysisDateTime() {
        return analysisDateTime;
    }

    public void setAnalysisDateTime(LocalDateTime analysisDateTime) {
        this.analysisDateTime = analysisDateTime;
    }

    public LocalDateTime getMatchDateTime() {
        return matchDateTime;
    }

    public void setMatchDateTime(LocalDateTime matchDateTime) {
        this.matchDateTime = matchDateTime;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Boolean getPlayingHome() {
        return isPlayingHome;
    }

    public void setPlayingHome(Boolean playingHome) {
        isPlayingHome = playingHome;
    }

    @Override
    public String toString() {
        return "TeamSentimentScore{" +
                "analysisDateTime=" + analysisDateTime +
                ", matchDateTime=" + matchDateTime +
                ", teamName='" + teamName + '\'' +
                ", score=" + score +
                ", isPlayingHome=" + isPlayingHome +
                '}';
    }
}
