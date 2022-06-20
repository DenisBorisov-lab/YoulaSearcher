package com.example.youlasearcher.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductAnalytics {
    private Long promotionType;
    private String typename;

    @JsonProperty("promotionType")
    public Long getPromotionType() {
        return promotionType;
    }

    @JsonProperty("promotionType")
    public void setPromotionType(Long value) {
        this.promotionType = value;
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
