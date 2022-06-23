package com.denisbrisov.youlasearcher.models.lot;

import com.fasterxml.jackson.annotation.*;

public class Info {
    private String deliveryInfoTitle;
    private String deliveryInfoDescription;
    private String deliveryInfoPopupDescription;
    private String paymentInfoTitle;
    private String paymentInfoDescription;
    private String paymentInfoPopupDescription;
    private String deliveryPeriodText;
    private String deliveryInfoDescriptionSaleWeb;
    private boolean isProductSale;

    @JsonProperty("delivery_info_title")
    public String getDeliveryInfoTitle() { return deliveryInfoTitle; }
    @JsonProperty("delivery_info_title")
    public void setDeliveryInfoTitle(String value) { this.deliveryInfoTitle = value; }

    @JsonProperty("delivery_info_description")
    public String getDeliveryInfoDescription() { return deliveryInfoDescription; }
    @JsonProperty("delivery_info_description")
    public void setDeliveryInfoDescription(String value) { this.deliveryInfoDescription = value; }

    @JsonProperty("delivery_info_popup_description")
    public String getDeliveryInfoPopupDescription() { return deliveryInfoPopupDescription; }
    @JsonProperty("delivery_info_popup_description")
    public void setDeliveryInfoPopupDescription(String value) { this.deliveryInfoPopupDescription = value; }

    @JsonProperty("payment_info_title")
    public String getPaymentInfoTitle() { return paymentInfoTitle; }
    @JsonProperty("payment_info_title")
    public void setPaymentInfoTitle(String value) { this.paymentInfoTitle = value; }

    @JsonProperty("payment_info_description")
    public String getPaymentInfoDescription() { return paymentInfoDescription; }
    @JsonProperty("payment_info_description")
    public void setPaymentInfoDescription(String value) { this.paymentInfoDescription = value; }

    @JsonProperty("payment_info_popup_description")
    public String getPaymentInfoPopupDescription() { return paymentInfoPopupDescription; }
    @JsonProperty("payment_info_popup_description")
    public void setPaymentInfoPopupDescription(String value) { this.paymentInfoPopupDescription = value; }

    @JsonProperty("delivery_period_text")
    public String getDeliveryPeriodText() { return deliveryPeriodText; }
    @JsonProperty("delivery_period_text")
    public void setDeliveryPeriodText(String value) { this.deliveryPeriodText = value; }

    @JsonProperty("delivery_info_description_sale_web")
    public String getDeliveryInfoDescriptionSaleWeb() { return deliveryInfoDescriptionSaleWeb; }
    @JsonProperty("delivery_info_description_sale_web")
    public void setDeliveryInfoDescriptionSaleWeb(String value) { this.deliveryInfoDescriptionSaleWeb = value; }

    @JsonProperty("is_product_sale")
    public boolean getIsProductSale() { return isProductSale; }
    @JsonProperty("is_product_sale")
    public void setIsProductSale(boolean value) { this.isProductSale = value; }
}
