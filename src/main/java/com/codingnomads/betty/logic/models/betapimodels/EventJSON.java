package com.codingnomads.betty.logic.models.betapimodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventJSON {

    @JsonProperty("id")
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<MarketJSON> getMarkets() {
        return markets;
    }

    public void setMarkets(List<MarketJSON> markets) {
        this.markets = markets;
    }

    @Override
    public String toString() {
        return "EventJSON{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", start='" + start + '\'' +
                ", markets=" + markets +
                '}';
    }
}
