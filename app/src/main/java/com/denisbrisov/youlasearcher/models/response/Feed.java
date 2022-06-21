package com.denisbrisov.youlasearcher.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Feed {
    private Item[] items;
    private PageInfo pageInfo;
    private String typename;

    @JsonProperty("items")
    public Item[] getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(Item[] value) {
        this.items = value;
    }

    @JsonProperty("pageInfo")
    public PageInfo getPageInfo() {
        return pageInfo;
    }

    @JsonProperty("pageInfo")
    public void setPageInfo(PageInfo value) {
        this.pageInfo = value;
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
