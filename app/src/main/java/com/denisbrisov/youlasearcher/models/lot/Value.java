package com.denisbrisov.youlasearcher.models.lot;

import com.fasterxml.jackson.annotation.*;

public class Value {
    private String value;
    private long order;
    private Long id;
    private String seoSlug;

    @JsonProperty("value")
    public String getValue() { return value; }
    @JsonProperty("value")
    public void setValue(String value) { this.value = value; }

    @JsonProperty("order")
    public long getOrder() { return order; }
    @JsonProperty("order")
    public void setOrder(long value) { this.order = value; }

    @JsonProperty("id")
    public Long getID() { return id; }
    @JsonProperty("id")
    public void setID(Long value) { this.id = value; }

    @JsonProperty("seo_slug")
    public String getSEOSlug() { return seoSlug; }
    @JsonProperty("seo_slug")
    public void setSEOSlug(String value) { this.seoSlug = value; }
}
