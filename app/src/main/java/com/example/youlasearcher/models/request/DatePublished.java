package com.example.youlasearcher.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatePublished {
    private Long to;
    private Long from;

    @JsonProperty("to")
    public Long getTo() {
        return to;
    }

    @JsonProperty("to")
    public void setTo(Long value) {
        this.to = value;
    }

    @JsonProperty("from")
    public Long getFrom() {
        return from;
    }

    @JsonProperty("from")
    public void setFrom(Long value) {
        this.from = value;
    }
}
