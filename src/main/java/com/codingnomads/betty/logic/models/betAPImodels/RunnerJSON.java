package com.codingnomads.betty.logic.models.betAPImodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RunnerJSON {

    @JsonProperty("name")
    private String name;

    @JsonProperty("prices")
    private List<PriceJSON> prices;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PriceJSON> getPriceJSONS() {
        return prices;
    }

    public void setPriceJSONS(List<PriceJSON> priceJSONS) {
        this.prices = priceJSONS;
    }

    @Override
    public String toString() {
        return "RunnerJSON{" +
                "name='" + name + '\'' +
                ", priceJSONS=" + prices +
                '}';
    }
}
