package com.denisbrisov.youlasearcher.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductsAnalytics {
    private String searchID;
    private String typename;

    @JsonProperty("searchId")
    public String getSearchID() {
        return searchID;
    }

    @JsonProperty("searchId")
    public void setSearchID(String value) {
        this.searchID = value;
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
