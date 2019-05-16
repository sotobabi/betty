package com.codingnomads.betty.logic.models.betapimodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketJSON {

    @JsonProperty("name")
    private String name;

    @JsonProperty("runners")
    private List<RunnerJSON> runners;

    public List<RunnerJSON> getRunnerJSONS() {
        return runners;
    }

    public void setRunnerJSONS(List<RunnerJSON> runnerJSONS) {
        this.runners = runnerJSONS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MarketJSON{" +
                "name='" + name + '\'' +
                ", runnerJSONS=" + runners +
                '}';
    }
}
