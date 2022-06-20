package com.example.youlasearcher.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    private Double latitude;
    private Double longitude;
    private Object city;
    private Long distanceMax;

    @JsonProperty("latitude")
    public Double getLatitude() {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(Double value) {
        this.latitude = value;
    }

    @JsonProperty("longitude")
    public Double getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(Double value) {
        this.longitude = value;
    }

    @JsonProperty("city")
    public Object getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(Object value) {
        this.city = value;
    }

    @JsonProperty("distanceMax")
    public Long getDistanceMax() {
        return distanceMax;
    }

    @JsonProperty("distanceMax")
    public void setDistanceMax(Long value) {
        this.distanceMax = value;
    }
}
