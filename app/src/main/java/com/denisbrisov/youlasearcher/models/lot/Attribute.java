package com.denisbrisov.youlasearcher.models.lot;

import com.fasterxml.jackson.annotation.*;

public class Attribute {
    private Long id;
    private String slug;
    private Value[] values;

    @JsonProperty("id")
    public Long getID() { return id; }
    @JsonProperty("id")
    public void setID(Long value) { this.id = value; }

    @JsonProperty("slug")
    public String getSlug() { return slug; }
    @JsonProperty("slug")
    public void setSlug(String value) { this.slug = value; }

    @JsonProperty("values")
    public Value[] getValues() { return values; }
    @JsonProperty("values")
    public void setValues(Value[] value) { this.values = value; }
}
