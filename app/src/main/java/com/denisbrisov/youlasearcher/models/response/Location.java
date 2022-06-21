package com.denisbrisov.youlasearcher.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    private String cityName;
    private String city;
    private String addressText;
    private String description;
    private Long latitude;
    private Long longitude;
    private String typename;

    @JsonProperty("cityName")
    public String getCityName() {
        return cityName;
    }

    @JsonProperty("cityName")
    public void setCityName(String value) {
        this.cityName = value;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String value) {
        this.city = value;
    }

    @JsonProperty("addressText")
    public String getAddressText() {
        return addressText;
    }

    @JsonProperty("addressText")
    public void setAddressText(String value) {
        this.addressText = value;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String value) {
        this.description = value;
    }

    @JsonProperty("latitude")
    public Long getLatitude() {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(Long value) {
        this.latitude = value;
    }

    @JsonProperty("longitude")
    public Long getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(Long value) {
        this.longitude = value;
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
