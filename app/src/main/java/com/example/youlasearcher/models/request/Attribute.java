package com.example.youlasearcher.models.request;

import com.fasterxml.jackson.annotation.*;

public class Attribute {
    private String slug;
    private String[] value;
    private Long from;
    private Long to;

    @JsonProperty("slug")
    public String getSlug() { return slug; }
    @JsonProperty("slug")
    public void setSlug(String value) { this.slug = value; }

    @JsonProperty("value")
    public String[] getValue() { return value; }
    @JsonProperty("value")
    public void setValue(String[] value) { this.value = value; }

    @JsonProperty("from")
    public Long getFrom() { return from; }
    @JsonProperty("from")
    public void setFrom(Long value) { this.from = value; }

    @JsonProperty("to")
    public Long getTo() { return to; }
    @JsonProperty("to")
    public void setTo(Long value) { this.to = value; }
}
