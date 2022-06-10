package com.example.youlasearcher.models.response;

import com.fasterxml.jackson.annotation.*;

public class PageInfo {
    private String cursor;
    private Boolean hasNextPage;
    private Object personalSearchID;
    private ProductsAnalytics productsAnalytics;
    private String typename;

    @JsonProperty("cursor")
    public String getCursor() { return cursor; }
    @JsonProperty("cursor")
    public void setCursor(String value) { this.cursor = value; }

    @JsonProperty("hasNextPage")
    public Boolean getHasNextPage() { return hasNextPage; }
    @JsonProperty("hasNextPage")
    public void setHasNextPage(Boolean value) { this.hasNextPage = value; }

    @JsonProperty("personalSearchId")
    public Object getPersonalSearchID() { return personalSearchID; }
    @JsonProperty("personalSearchId")
    public void setPersonalSearchID(Object value) { this.personalSearchID = value; }

    @JsonProperty("productsAnalytics")
    public ProductsAnalytics getProductsAnalytics() { return productsAnalytics; }
    @JsonProperty("productsAnalytics")
    public void setProductsAnalytics(ProductsAnalytics value) { this.productsAnalytics = value; }

    @JsonProperty("__typename")
    public String getTypename() { return typename; }
    @JsonProperty("__typename")
    public void setTypename(String value) { this.typename = value; }
}