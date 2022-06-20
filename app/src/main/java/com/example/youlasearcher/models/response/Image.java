package com.example.youlasearcher.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {
    private String id;
    private Long num;
    private String url;
    private String typename;

    @JsonProperty("id")
    public String getID() {
        return id;
    }

    @JsonProperty("id")
    public void setID(String value) {
        this.id = value;
    }

    @JsonProperty("num")
    public Long getNum() {
        return num;
    }

    @JsonProperty("num")
    public void setNum(Long value) {
        this.num = value;
    }

    @JsonProperty("url")
    public String getURL() {
        return url;
    }

    @JsonProperty("url")
    public void setURL(String value) {
        this.url = value;
    }

    @JsonProperty("__typename")
    public String getTypename() {
        return typename;
    }

    @JsonProperty("__typename")
    public void setTypename(String value) {
        this.typename = value;
    }
}
