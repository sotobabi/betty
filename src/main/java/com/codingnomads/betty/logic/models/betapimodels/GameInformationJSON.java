package com.codingnomads.betty.logic.models.betapimodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class GameInformationJSON {

    @JsonProperty("offset")
    private int offset;

    @JsonProperty("events")
    private List<EventJSON> events;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<EventJSON> getEventJSONS() {
        return events;
    }

    public void setEventJSONS(List<EventJSON> eventJSONS) {
        this.events = eventJSONS;
    }

    @Override
    public String toString() {
        return "GameInformationJSON{" +
                "offset=" + offset +
                ", eventJSONS=" + events +
                '}';
    }
}
