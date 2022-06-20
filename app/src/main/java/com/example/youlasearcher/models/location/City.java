package com.example.youlasearcher.models.location;

import com.fasterxml.jackson.annotation.JsonProperty;

public class City {
    private Coords coords;
    private String id;
    private long level;
    private String name;
    private String parentID;
    private long productsCnt;
    private String slug;
    private String namePp;
    private long codeRb;
    private String parentName;

    @JsonProperty("coords")
    public Coords getCoords() {
        return coords;
    }

    @JsonProperty("coords")
    public void setCoords(Coords value) {
        this.coords = value;
    }

    @JsonProperty("id")
    public String getID() {
        return id;
    }

    @JsonProperty("id")
    public void setID(String value) {
        this.id = value;
    }

    @JsonProperty("level")
    public long getLevel() {
        return level;
    }

    @JsonProperty("level")
    public void setLevel(long value) {
        this.level = value;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.name = value;
    }

    @JsonProperty("parentId")
    public String getParentID() {
        return parentID;
    }

    @JsonProperty("parentId")
    public void setParentID(String value) {
        this.parentID = value;
    }

    @JsonProperty("productsCnt")
    public long getProductsCnt() {
        return productsCnt;
    }

    @JsonProperty("productsCnt")
    public void setProductsCnt(long value) {
        this.productsCnt = value;
    }

    @JsonProperty("slug")
    public String getSlug() {
        return slug;
    }

    @JsonProperty("slug")
    public void setSlug(String value) {
        this.slug = value;
    }

    @JsonProperty("namePp")
    public String getNamePp() {
        return namePp;
    }

    @JsonProperty("namePp")
    public void setNamePp(String value) {
        this.namePp = value;
    }

    @JsonProperty("codeRb")
    public long getCodeRb() {
        return codeRb;
    }

    @JsonProperty("codeRb")
    public void setCodeRb(long value) {
        this.codeRb = value;
    }

    @JsonProperty("parentName")
    public String getParentName() {
        return parentName;
    }

    @JsonProperty("parentName")
    public void setParentName(String value) {
        this.parentName = value;
    }
}
