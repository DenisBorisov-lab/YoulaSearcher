package com.denisbrisov.youlasearcher.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    private String typename;
    private Product product;
    private ProductAnalytics productAnalytics;

    @JsonProperty("__typename")
    public String getTypename() {
        return typename;
    }

    @JsonProperty("__typename")
    public void setTypename(String value) {
        this.typename = value;
    }

    @JsonProperty("product")
    public Product getProduct() {
        return product;
    }

    @JsonProperty("product")
    public void setProduct(Product value) {
        this.product = value;
    }

    @JsonProperty("productAnalytics")
    public ProductAnalytics getProductAnalytics() {
        return productAnalytics;
    }

    @JsonProperty("productAnalytics")
    public void setProductAnalytics(ProductAnalytics value) {
        this.productAnalytics = value;
    }
}
