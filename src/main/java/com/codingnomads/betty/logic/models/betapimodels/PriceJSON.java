package com.codingnomads.betty.logic.models.betapimodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceJSON {

    @JsonProperty("odds")
    private double odds;

    public double getOdds() {
        return odds;
    }

    public void setOdds(double odds) {
        this.odds = odds;
    }

    @Override
    public String toString() {
        return "PriceJSON{" +
                "odds=" + odds +
                '}';
    }
}
