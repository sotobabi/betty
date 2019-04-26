package com.codingnomads.betty.logic.models.betAPImodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Market {

    @JsonProperty("runners")
    private List<Runner> runners;

    public List<Runner> getRunners() {
        return runners;
    }

    public void setRunners(List<Runner> runners) {
        this.runners = runners;
    }

    @Override
    public String toString() {
        return "Market{" +
                "runners=" + runners +
                '}';
    }
}
