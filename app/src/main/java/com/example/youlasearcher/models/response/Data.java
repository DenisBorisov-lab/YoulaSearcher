package com.example.youlasearcher.models.response;

import com.fasterxml.jackson.annotation.*;

public class Data {
    private Feed feed;

    @JsonProperty("feed")
    public Feed getFeed() { return feed; }
    @JsonProperty("feed")
    public void setFeed(Feed value) { this.feed = value; }
}
