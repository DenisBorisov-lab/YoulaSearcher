package com.denisbrisov.youlasearcher.models.lot;

import com.fasterxml.jackson.annotation.*;

public class Verification {
    private long type;
    private long vkFriendsCount;
    private long requiredType;

    @JsonProperty("type")
    public long getType() { return type; }
    @JsonProperty("type")
    public void setType(long value) { this.type = value; }

    @JsonProperty("vk_friends_count")
    public long getVkFriendsCount() { return vkFriendsCount; }
    @JsonProperty("vk_friends_count")
    public void setVkFriendsCount(long value) { this.vkFriendsCount = value; }

    @JsonProperty("required_type")
    public long getRequiredType() { return requiredType; }
    @JsonProperty("required_type")
    public void setRequiredType(long value) { this.requiredType = value; }
}
