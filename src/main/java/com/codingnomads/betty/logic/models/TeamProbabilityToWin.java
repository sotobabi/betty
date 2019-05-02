package com.codingnomads.betty.logic.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;

public class TeamProbabilityToWin {

    private String teamName;
    private Double probabilityToWin;
    private LocalDateTime matchDateTime;
    private Boolean isPlayingHome;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Double getProbabilityToWin() {
        return probabilityToWin;
    }

    public void setProbabilityToWin(Double probabilityToWin) {
        this.probabilityToWin = probabilityToWin;
    }

    public LocalDateTime getMatchDateTime() {
        return matchDateTime;
    }

    public void setMatchDateTime(LocalDateTime matchDateTime) {
        this.matchDateTime = matchDateTime;
    }

    public Boolean getPlayingHome() {
        return isPlayingHome;
    }

    public void setPlayingHome(Boolean playingHome) {
        isPlayingHome = playingHome;
    }

    @Override
    public String toString() {
        return "TeamProbabilityToWin{" +
                "teamName='" + teamName + '\'' +
                ", probabilityToWin=" + probabilityToWin +
                ", matchDateTime=" + matchDateTime +
                ", isPlayingHome=" + isPlayingHome +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TeamProbabilityToWin that = (TeamProbabilityToWin) o;

        return new EqualsBuilder()
                .append(teamName, that.teamName)
                .append(probabilityToWin, that.probabilityToWin)
                .append(matchDateTime, that.matchDateTime)
                .append(isPlayingHome, that.isPlayingHome)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(teamName)
                .append(probabilityToWin)
                .append(matchDateTime)
                .append(isPlayingHome)
                .toHashCode();
    }
}
