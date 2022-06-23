package com.denisbrisov.youlasearcher.models.lot;

import com.fasterxml.jackson.annotation.*;

public class Owner {
    private String id;
    private String name;
    private String type;
    private String linkedID;
    private boolean isShop;
    private boolean isVerified;
    private boolean isBlocked;
    private OwnerImage image;
    private long dateRegistered;
    private Object displayPhoneNum;
    private boolean isOnline;
    private String onlineText;
    private String onlineTextDetailed;
    private String answerTimeText;
    private OwnerSettings settings;
    private long blacklistStatus;
    private Verification verification;
    private boolean b2BWithManager;
    private double ratingMark;
    private long ratingMarkCnt;
    private long prodsActiveCnt;
    private long prodsSoldCnt;
    private long ordersBuyerCnt;
    private long ordersCnt;
    private long ordersSellerCnt;
    private long contactsActiveCnt;
    private boolean experiencedSeller;

    @JsonProperty("id")
    public String getID() { return id; }
    @JsonProperty("id")
    public void setID(String value) { this.id = value; }

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("type")
    public String getType() { return type; }
    @JsonProperty("type")
    public void setType(String value) { this.type = value; }

    @JsonProperty("linked_id")
    public String getLinkedID() { return linkedID; }
    @JsonProperty("linked_id")
    public void setLinkedID(String value) { this.linkedID = value; }

    @JsonProperty("is_shop")
    public boolean getIsShop() { return isShop; }
    @JsonProperty("is_shop")
    public void setIsShop(boolean value) { this.isShop = value; }

    @JsonProperty("is_verified")
    public boolean getIsVerified() { return isVerified; }
    @JsonProperty("is_verified")
    public void setIsVerified(boolean value) { this.isVerified = value; }

    @JsonProperty("is_blocked")
    public boolean getIsBlocked() { return isBlocked; }
    @JsonProperty("is_blocked")
    public void setIsBlocked(boolean value) { this.isBlocked = value; }

    @JsonProperty("image")
    public OwnerImage getImage() { return image; }
    @JsonProperty("image")
    public void setImage(OwnerImage value) { this.image = value; }

    @JsonProperty("date_registered")
    public long getDateRegistered() { return dateRegistered; }
    @JsonProperty("date_registered")
    public void setDateRegistered(long value) { this.dateRegistered = value; }

    @JsonProperty("display_phone_num")
    public Object getDisplayPhoneNum() { return displayPhoneNum; }
    @JsonProperty("display_phone_num")
    public void setDisplayPhoneNum(Object value) { this.displayPhoneNum = value; }

    @JsonProperty("isOnline")
    public boolean getIsOnline() { return isOnline; }
    @JsonProperty("isOnline")
    public void setIsOnline(boolean value) { this.isOnline = value; }

    @JsonProperty("onlineText")
    public String getOnlineText() { return onlineText; }
    @JsonProperty("onlineText")
    public void setOnlineText(String value) { this.onlineText = value; }

    @JsonProperty("online_text_detailed")
    public String getOnlineTextDetailed() { return onlineTextDetailed; }
    @JsonProperty("online_text_detailed")
    public void setOnlineTextDetailed(String value) { this.onlineTextDetailed = value; }

    @JsonProperty("answerTimeText")
    public String getAnswerTimeText() { return answerTimeText; }
    @JsonProperty("answerTimeText")
    public void setAnswerTimeText(String value) { this.answerTimeText = value; }

    @JsonProperty("settings")
    public OwnerSettings getSettings() { return settings; }
    @JsonProperty("settings")
    public void setSettings(OwnerSettings value) { this.settings = value; }

    @JsonProperty("blacklist_status")
    public long getBlacklistStatus() { return blacklistStatus; }
    @JsonProperty("blacklist_status")
    public void setBlacklistStatus(long value) { this.blacklistStatus = value; }

    @JsonProperty("verification")
    public Verification getVerification() { return verification; }
    @JsonProperty("verification")
    public void setVerification(Verification value) { this.verification = value; }

    @JsonProperty("b2b_with_manager")
    public boolean getB2BWithManager() { return b2BWithManager; }
    @JsonProperty("b2b_with_manager")
    public void setB2BWithManager(boolean value) { this.b2BWithManager = value; }

    @JsonProperty("rating_mark")
    public double getRatingMark() { return ratingMark; }
    @JsonProperty("rating_mark")
    public void setRatingMark(double value) { this.ratingMark = value; }

    @JsonProperty("rating_mark_cnt")
    public long getRatingMarkCnt() { return ratingMarkCnt; }
    @JsonProperty("rating_mark_cnt")
    public void setRatingMarkCnt(long value) { this.ratingMarkCnt = value; }

    @JsonProperty("prods_active_cnt")
    public long getProdsActiveCnt() { return prodsActiveCnt; }
    @JsonProperty("prods_active_cnt")
    public void setProdsActiveCnt(long value) { this.prodsActiveCnt = value; }

    @JsonProperty("prods_sold_cnt")
    public long getProdsSoldCnt() { return prodsSoldCnt; }
    @JsonProperty("prods_sold_cnt")
    public void setProdsSoldCnt(long value) { this.prodsSoldCnt = value; }

    @JsonProperty("orders_buyer_cnt")
    public long getOrdersBuyerCnt() { return ordersBuyerCnt; }
    @JsonProperty("orders_buyer_cnt")
    public void setOrdersBuyerCnt(long value) { this.ordersBuyerCnt = value; }

    @JsonProperty("orders_cnt")
    public long getOrdersCnt() { return ordersCnt; }
    @JsonProperty("orders_cnt")
    public void setOrdersCnt(long value) { this.ordersCnt = value; }

    @JsonProperty("orders_seller_cnt")
    public long getOrdersSellerCnt() { return ordersSellerCnt; }
    @JsonProperty("orders_seller_cnt")
    public void setOrdersSellerCnt(long value) { this.ordersSellerCnt = value; }

    @JsonProperty("contacts_active_cnt")
    public long getContactsActiveCnt() { return contactsActiveCnt; }
    @JsonProperty("contacts_active_cnt")
    public void setContactsActiveCnt(long value) { this.contactsActiveCnt = value; }

    @JsonProperty("experienced_seller")
    public boolean getExperiencedSeller() { return experiencedSeller; }
    @JsonProperty("experienced_seller")
    public void setExperiencedSeller(boolean value) { this.experiencedSeller = value; }
}
