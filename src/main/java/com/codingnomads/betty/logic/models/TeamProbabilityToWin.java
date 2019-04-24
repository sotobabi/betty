package com.codingnomads.betty.logic.models;

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
}
