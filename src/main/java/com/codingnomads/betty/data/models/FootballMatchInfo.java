package com.codingnomads.betty.data.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="football_matches", uniqueConstraints = @UniqueConstraint(columnNames = "api_id"))
public class FootballMatchInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Temporal(TemporalType.DATE)
    private Date matchDate;

    @Column
    private String api_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public String getApi_id() {
        return api_id;
    }

    public void setApi_id(String api_id) {
        this.api_id = api_id;
    }

    @Override
    public String toString() {
        return "FootballMatchInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", matchDate=" + matchDate +
                ", api_id='" + api_id + '\'' +
                '}';
    }
}

