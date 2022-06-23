package com.denisbrisov.youlasearcher.models.lot;

import com.fasterxml.jackson.annotation.*;

public class Contractor {
    private String id;
    private boolean isUserContact;
    private String name;
    private ImageElement image;
    private long dateRegistered;
    private ContractorSettings settings;

    @JsonProperty("id")
    public String getID() { return id; }
    @JsonProperty("id")
    public void setID(String value) { this.id = value; }

    @JsonProperty("is_user_contact")
    public boolean getIsUserContact() { return isUserContact; }
    @JsonProperty("is_user_contact")
    public void setIsUserContact(boolean value) { this.isUserContact = value; }

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("image")
    public ImageElement getImage() { return image; }
    @JsonProperty("image")
    public void setImage(ImageElement value) { this.image = value; }

    @JsonProperty("date_registered")
    public long getDateRegistered() { return dateRegistered; }
    @JsonProperty("date_registered")
    public void setDateRegistered(long value) { this.dateRegistered = value; }

    @JsonProperty("settings")
    public ContractorSettings getSettings() { return settings; }
    @JsonProperty("settings")
    public void setSettings(ContractorSettings value) { this.settings = value; }
}
