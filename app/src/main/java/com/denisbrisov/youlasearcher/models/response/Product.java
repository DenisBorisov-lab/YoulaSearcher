package com.denisbrisov.youlasearcher.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    private String id;
    private Long categoryID;
    private Long subcategoryID;
    private Price price;
    private String url;
    private Image[] images;
    private String name;
    private Location location;
    private String distanceText;
    private Boolean isPromoted;
    private Favorite favorite;
    private Boolean deliveryAvailable;
    private Boolean paymentAvailable;
    private Object branding;
    private String salaryText;
    private String typename;

    @JsonProperty("id")
    public String getID() {
        return id;
    }

    @JsonProperty("id")
    public void setID(String value) {
        this.id = value;
    }

    @JsonProperty("categoryId")
    public Long getCategoryID() {
        return categoryID;
    }

    @JsonProperty("categoryId")
    public void setCategoryID(Long value) {
        this.categoryID = value;
    }

    @JsonProperty("subcategoryId")
    public Long getSubcategoryID() {
        return subcategoryID;
    }

    @JsonProperty("subcategoryId")
    public void setSubcategoryID(Long value) {
        this.subcategoryID = value;
    }

    @JsonProperty("price")
    public Price getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Price value) {
        this.price = value;
    }

    @JsonProperty("url")
    public String getURL() {
        return url;
    }

    @JsonProperty("url")
    public void setURL(String value) {
        this.url = value;
    }

    @JsonProperty("images")
    public Image[] getImages() {
        return images;
    }

    @JsonProperty("images")
    public void setImages(Image[] value) {
        this.images = value;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.name = value;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location value) {
        this.location = value;
    }

    @JsonProperty("distanceText")
    public String getDistanceText() {
        return distanceText;
    }

    @JsonProperty("distanceText")
    public void setDistanceText(String value) {
        this.distanceText = value;
    }

    @JsonProperty("isPromoted")
    public Boolean getIsPromoted() {
        return isPromoted;
    }

    @JsonProperty("isPromoted")
    public void setIsPromoted(Boolean value) {
        this.isPromoted = value;
    }

    @JsonProperty("favorite")
    public Favorite getFavorite() {
        return favorite;
    }

    @JsonProperty("favorite")
    public void setFavorite(Favorite value) {
        this.favorite = value;
    }

    @JsonProperty("deliveryAvailable")
    public Boolean getDeliveryAvailable() {
        return deliveryAvailable;
    }

    @JsonProperty("deliveryAvailable")
    public void setDeliveryAvailable(Boolean value) {
        this.deliveryAvailable = value;
    }

    @JsonProperty("paymentAvailable")
    public Boolean getPaymentAvailable() {
        return paymentAvailable;
    }

    @JsonProperty("paymentAvailable")
    public void setPaymentAvailable(Boolean value) {
        this.paymentAvailable = value;
    }

    @JsonProperty("branding")
    public Object getBranding() {
        return branding;
    }

    @JsonProperty("branding")
    public void setBranding(Object value) {
        this.branding = value;
    }

    @JsonProperty("salaryText")
    public String getSalaryText() {
        return salaryText;
    }

    @JsonProperty("salaryText")
    public void setSalaryText(String value) {
        this.salaryText = value;
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
