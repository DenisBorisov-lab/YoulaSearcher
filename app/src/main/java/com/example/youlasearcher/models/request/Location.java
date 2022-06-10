package com.example.youlasearcher.models.request;

import com.fasterxml.jackson.annotation.*;

public class Location {
    private Object latitude;
    private Object longitude;
    private String city;
    private Object distanceMax;

    @JsonProperty("latitude")
    public Object getLatitude() { return latitude; }
    @JsonProperty("latitude")
    public void setLatitude(Object value) { this.latitude = value; }

    @JsonProperty("longitude")
    public Object getLongitude() { return longitude; }
    @JsonProperty("longitude")
    public void setLongitude(Object value) { this.longitude = value; }

    @JsonProperty("city")
    public String getCity() { return city; }
    @JsonProperty("city")
    public void setCity(String value) { this.city = value; }

    @JsonProperty("distanceMax")
    public Object getDistanceMax() { return distanceMax; }
    @JsonProperty("distanceMax")
    public void setDistanceMax(Object value) { this.distanceMax = value; }
}
