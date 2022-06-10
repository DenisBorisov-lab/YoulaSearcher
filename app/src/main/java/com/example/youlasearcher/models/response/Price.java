package com.example.youlasearcher.models.response;

import com.fasterxml.jackson.annotation.*;

public class Price {
    private OrigPriceClass origPrice;
    private OrigPriceClass realPrice;
    private String realPriceText;
    private Long discount;
    private String typename;

    @JsonProperty("origPrice")
    public OrigPriceClass getOrigPrice() { return origPrice; }
    @JsonProperty("origPrice")
    public void setOrigPrice(OrigPriceClass value) { this.origPrice = value; }

    @JsonProperty("realPrice")
    public OrigPriceClass getRealPrice() { return realPrice; }
    @JsonProperty("realPrice")
    public void setRealPrice(OrigPriceClass value) { this.realPrice = value; }

    @JsonProperty("realPriceText")
    public String getRealPriceText() { return realPriceText; }
    @JsonProperty("realPriceText")
    public void setRealPriceText(String value) { this.realPriceText = value; }

    @JsonProperty("discount")
    public Long getDiscount() { return discount; }
    @JsonProperty("discount")
    public void setDiscount(Long value) { this.discount = value; }

    @JsonProperty("__typename")
    public String getTypename() { return typename; }
    @JsonProperty("__typename")
    public void setTypename(String value) { this.typename = value; }
}
