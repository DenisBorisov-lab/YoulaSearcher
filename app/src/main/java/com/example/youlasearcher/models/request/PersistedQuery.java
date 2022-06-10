package com.example.youlasearcher.models.request;

import com.fasterxml.jackson.annotation.*;

public class PersistedQuery {
    private Long version;
    private String sha256Hash;

    @JsonProperty("version")
    public Long getVersion() { return version; }
    @JsonProperty("version")
    public void setVersion(Long value) { this.version = value; }

    @JsonProperty("sha256Hash")
    public String getSha256Hash() { return sha256Hash; }
    @JsonProperty("sha256Hash")
    public void setSha256Hash(String value) { this.sha256Hash = value; }
}