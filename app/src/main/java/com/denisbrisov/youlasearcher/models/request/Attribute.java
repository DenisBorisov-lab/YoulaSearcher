package com.denisbrisov.youlasearcher.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Attribute {
    private String slug;
    private String[] value;
    private Object from;
    private Object to;

    @JsonProperty("slug")
    public String getSlug() {
        return slug;
    }

    @JsonProperty("slug")
    public void setSlug(String value) {
        this.slug = value;
    }

    @JsonProperty("value")
    public String[] getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String[] value) {
        this.value = value;
    }

    @JsonProperty("from")
    public Object getFrom() {
        return from;
    }

    @JsonProperty("from")
    public void setFrom(Object value) {
        this.from = value;
    }

    @JsonProperty("to")
    public Object getTo() {
        return to;
    }

    @JsonProperty("to")
    public void setTo(Object value) {
        this.to = value;
    }
}
