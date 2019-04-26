package com.codingnomads.betty.logic.models.betAPImodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    @JsonProperty("name")
    private String name;

    @JsonProperty("start")
    private String start;

    @JsonProperty("markets")
    private List<Market> markets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public List<Market> getMarkets() {
        return markets;
    }

    public void setMarkets(List<Market> markets) {
        this.markets = markets;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", start='" + start + '\'' +
                ", markets=" + markets +
                '}';
    }
}
