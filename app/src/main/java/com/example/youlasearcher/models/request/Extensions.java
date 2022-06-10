package com.example.youlasearcher.models.request;

import com.fasterxml.jackson.annotation.*;

public class Extensions {
    private PersistedQuery persistedQuery;

    @JsonProperty("persistedQuery")
    public PersistedQuery getPersistedQuery() { return persistedQuery; }
    @JsonProperty("persistedQuery")
    public void setPersistedQuery(PersistedQuery value) { this.persistedQuery = value; }
}
