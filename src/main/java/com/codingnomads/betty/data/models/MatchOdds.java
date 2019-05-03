package com.codingnomads.betty.data.models;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "match_odds")
public class MatchOdds {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date matchDate;

    @Column
    private String homeTeam;

    @Column
    private Double homeTeamOdd;

    @Column
    private String awayTeam;

    @Column
    private Double awayTeamOdd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Double getHomeTeamOdd() {
        return homeTeamOdd;
    }

    public void setHomeTeamOdd(Double homeTeamOdd) {
        this.homeTeamOdd = homeTeamOdd;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Double getAwayTeamOdd() {
        return awayTeamOdd;
    }

    public void setAwayTeamOdd(Double awayTeamOdd) {
        this.awayTeamOdd = awayTeamOdd;
    }

    public Date getMatchDateTime() {
        return matchDate;
    }

    public void setMatchDateTime(Date matchDateTime) {
        this.matchDate = matchDateTime;
    }

    @Override
    public String toString() {
        return "MatchOdds{" +
                "id=" + id +
                ", matchDate=" + matchDate +
                ", homeTeam='" + homeTeam + '\'' +
                ", homeTeamOdd=" + homeTeamOdd +
                ", awayTeam='" + awayTeam + '\'' +
                ", awayTeamOdd=" + awayTeamOdd +
                '}';
    }
}
