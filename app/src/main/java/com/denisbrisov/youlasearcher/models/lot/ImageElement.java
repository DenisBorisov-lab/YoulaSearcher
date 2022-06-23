package com.denisbrisov.youlasearcher.models.lot;

import com.fasterxml.jackson.annotation.*;

public class ImageElement {
    private String id;
    private long num;
    private String url;
    private long width;
    private long height;

    @JsonProperty("id")
    public String getID() { return id; }
    @JsonProperty("id")
    public void setID(String value) { this.id = value; }

    @JsonProperty("num")
    public long getNum() { return num; }
    @JsonProperty("num")
    public void setNum(long value) { this.num = value; }

    @JsonProperty("url")
    public String getURL() { return url; }
    @JsonProperty("url")
    public void setURL(String value) { this.url = value; }

    @JsonProperty("width")
    public long getWidth() { return width; }
    @JsonProperty("width")
    public void setWidth(long value) { this.width = value; }

    @JsonProperty("height")
    public long getHeight() { return height; }
    @JsonProperty("height")
    public void setHeight(long value) { this.height = value; }
}
