package com.example.youlasearcher.models.location;

import com.fasterxml.jackson.annotation.*;

public class LocationModel {
    private boolean isConfirmed;
    private long lat;
    private long lng;
    private long r;
    private String title;
    private City city;
    private String citySlug;
    private boolean cityLocation;
    private boolean pointLocation;
    private boolean defaultRadius;

    @JsonProperty("isConfirmed")
    public boolean getIsConfirmed() { return isConfirmed; }
    @JsonProperty("isConfirmed")
    public void setIsConfirmed(boolean value) { this.isConfirmed = value; }

    @JsonProperty("lat")
    public long getLat() { return lat; }
    @JsonProperty("lat")
    public void setLat(long value) { this.lat = value; }

    @JsonProperty("lng")
    public long getLng() { return lng; }
    @JsonProperty("lng")
    public void setLng(long value) { this.lng = value; }

    @JsonProperty("r")
    public long getR() { return r; }
    @JsonProperty("r")
    public void setR(long value) { this.r = value; }

    @JsonProperty("title")
    public String getTitle() { return title; }
    @JsonProperty("title")
    public void setTitle(String value) { this.title = value; }

    @JsonProperty("city")
    public City getCity() { return city; }
    @JsonProperty("city")
    public void setCity(City value) { this.city = value; }

    @JsonProperty("citySlug")
    public String getCitySlug() { return citySlug; }
    @JsonProperty("citySlug")
    public void setCitySlug(String value) { this.citySlug = value; }

    @JsonProperty("cityLocation")
    public boolean getCityLocation() { return cityLocation; }
    @JsonProperty("cityLocation")
    public void setCityLocation(boolean value) { this.cityLocation = value; }

    @JsonProperty("pointLocation")
    public boolean getPointLocation() { return pointLocation; }
    @JsonProperty("pointLocation")
    public void setPointLocation(boolean value) { this.pointLocation = value; }

    @JsonProperty("defaultRadius")
    public boolean getDefaultRadius() { return defaultRadius; }
    @JsonProperty("defaultRadius")
    public void setDefaultRadius(boolean value) { this.defaultRadius = value; }
}
