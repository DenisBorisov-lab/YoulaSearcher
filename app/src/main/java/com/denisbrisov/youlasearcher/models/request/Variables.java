package com.denisbrisov.youlasearcher.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Variables {
    private String sort;
    private Attribute[] attributes;
    private DatePublished datePublished;
    private Location location;
    private String search;
    private String cursor;

    @JsonProperty("sort")
    public String getSort() {
        return sort;
    }

    @JsonProperty("sort")
    public void setSort(String value) {
        this.sort = value;
    }

    @JsonProperty("attributes")
    public Attribute[] getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(Attribute[] value) {
        this.attributes = value;
    }

    @JsonProperty("datePublished")
    public DatePublished getDatePublished() {
        return datePublished;
    }

    @JsonProperty("datePublished")
    public void setDatePublished(DatePublished value) {
        this.datePublished = value;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location value) {
        this.location = value;
    }

    @JsonProperty("search")
    public String getSearch() {
        return search;
    }

    @JsonProperty("search")
    public void setSearch(String value) {
        this.search = value;
    }

    @JsonProperty("cursor")
    public String getCursor() {
        return cursor;
    }

    @JsonProperty("cursor")
    public void setCursor(String value) {
        this.cursor = value;
    }
}
