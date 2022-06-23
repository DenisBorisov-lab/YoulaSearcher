package com.denisbrisov.youlasearcher.models.lot;

import com.fasterxml.jackson.annotation.*;

public class DeliveryTexts {
    private String linkDeliveryEnabled;
    private String linkDeliveryEnabledName;
    private String linkDeliveryDisabled;
    private String linkDeliveryDisabledName;
    private String textDeliveryEnabled;
    private String textDeliveryDisabled;
    private String textDeliveryUnavailable;

    @JsonProperty("link_delivery_enabled")
    public String getLinkDeliveryEnabled() { return linkDeliveryEnabled; }
    @JsonProperty("link_delivery_enabled")
    public void setLinkDeliveryEnabled(String value) { this.linkDeliveryEnabled = value; }

    @JsonProperty("link_delivery_enabled_name")
    public String getLinkDeliveryEnabledName() { return linkDeliveryEnabledName; }
    @JsonProperty("link_delivery_enabled_name")
    public void setLinkDeliveryEnabledName(String value) { this.linkDeliveryEnabledName = value; }

    @JsonProperty("link_delivery_disabled")
    public String getLinkDeliveryDisabled() { return linkDeliveryDisabled; }
    @JsonProperty("link_delivery_disabled")
    public void setLinkDeliveryDisabled(String value) { this.linkDeliveryDisabled = value; }

    @JsonProperty("link_delivery_disabled_name")
    public String getLinkDeliveryDisabledName() { return linkDeliveryDisabledName; }
    @JsonProperty("link_delivery_disabled_name")
    public void setLinkDeliveryDisabledName(String value) { this.linkDeliveryDisabledName = value; }

    @JsonProperty("text_delivery_enabled")
    public String getTextDeliveryEnabled() { return textDeliveryEnabled; }
    @JsonProperty("text_delivery_enabled")
    public void setTextDeliveryEnabled(String value) { this.textDeliveryEnabled = value; }

    @JsonProperty("text_delivery_disabled")
    public String getTextDeliveryDisabled() { return textDeliveryDisabled; }
    @JsonProperty("text_delivery_disabled")
    public void setTextDeliveryDisabled(String value) { this.textDeliveryDisabled = value; }

    @JsonProperty("text_delivery_unavailable")
    public String getTextDeliveryUnavailable() { return textDeliveryUnavailable; }
    @JsonProperty("text_delivery_unavailable")
    public void setTextDeliveryUnavailable(String value) { this.textDeliveryUnavailable = value; }
}
