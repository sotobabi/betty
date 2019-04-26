package com.codingnomads.betty.logic.models.betAPImodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameInformationJSON {

    @JsonProperty("offset")
    private int offset;

    @JsonProperty("events")
    private List<Event> events;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "GameInformationJSON{" +
                "offset=" + offset +
                ", events=" + events +
                '}';
    }
}
