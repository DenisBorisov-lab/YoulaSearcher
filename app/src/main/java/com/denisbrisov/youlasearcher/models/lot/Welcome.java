package com.denisbrisov.youlasearcher.models.lot;

import com.fasterxml.jackson.annotation.*;

public class Welcome {
    private Data data;
    private long status;
    private String detail;
    private String uri;

    @JsonProperty("data")
    public Data getData() { return data; }
    @JsonProperty("data")
    public void setData(Data value) { this.data = value; }

    @JsonProperty("status")
    public long getStatus() { return status; }
    @JsonProperty("status")
    public void setStatus(long value) { this.status = value; }

    @JsonProperty("detail")
    public String getDetail() { return detail; }
    @JsonProperty("detail")
    public void setDetail(String value) { this.detail = value; }

    @JsonProperty("uri")
    public String getURI() { return uri; }
    @JsonProperty("uri")
    public void setURI(String value) { this.uri = value; }
}
