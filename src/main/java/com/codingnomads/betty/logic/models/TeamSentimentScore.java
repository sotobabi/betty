package com.codingnomads.betty.logic.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TeamSentimentScore that = (TeamSentimentScore) o;

        return new EqualsBuilder()
                .append(analysisDateTime, that.analysisDateTime)
                .append(matchDateTime, that.matchDateTime)
                .append(teamName, that.teamName)
                .append(score, that.score)
                .append(isPlayingHome, that.isPlayingHome)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(analysisDateTime)
                .append(matchDateTime)
                .append(teamName)
                .append(score)
                .append(isPlayingHome)
                .toHashCode();
    }
}
