package com.codingnomads.betty.logic.models.betAPImodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventJSON {

    @JsonProperty("name")
    private String name;

    @JsonProperty("start")
    private String start;

    @JsonProperty("markets")
    private List<MarketJSON> markets;

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

    public List<MarketJSON> getMarketJSONS() {
        return markets;
    }

    public void setMarketJSONS(List<MarketJSON> marketJSONS) {
        this.markets = marketJSONS;
    }

    @Override
    public String toString() {
        return "EventJSON{" +
                "name='" + name + '\'' +
                ", start='" + start + '\'' +
                ", marketJSONS=" + markets +
                '}';
    }
}
