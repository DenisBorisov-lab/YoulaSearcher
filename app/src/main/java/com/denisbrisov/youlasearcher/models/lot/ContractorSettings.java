package com.denisbrisov.youlasearcher.models.lot;

import com.fasterxml.jackson.annotation.*;

public class ContractorSettings {
    private boolean displayPhone;

    @JsonProperty("display_phone")
    public boolean getDisplayPhone() { return displayPhone; }
    @JsonProperty("display_phone")
    public void setDisplayPhone(boolean value) { this.displayPhone = value; }
}
