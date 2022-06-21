package com.denisbrisov.youlasearcher.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrigPriceClass {
    private Long price;
    private String typename;

    @JsonProperty("price")
    public Long getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Long value) {
        this.price = value;
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
