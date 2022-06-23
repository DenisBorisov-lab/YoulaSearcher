package com.denisbrisov.youlasearcher.models.lot;

import com.fasterxml.jackson.annotation.*;

public class CallSettings {
    private boolean anyCallEnabled;
    private boolean systemCallEnabled;
    private boolean p2PCallEnabled;

    @JsonProperty("any_call_enabled")
    public boolean getAnyCallEnabled() { return anyCallEnabled; }
    @JsonProperty("any_call_enabled")
    public void setAnyCallEnabled(boolean value) { this.anyCallEnabled = value; }

    @JsonProperty("system_call_enabled")
    public boolean getSystemCallEnabled() { return systemCallEnabled; }
    @JsonProperty("system_call_enabled")
    public void setSystemCallEnabled(boolean value) { this.systemCallEnabled = value; }

    @JsonProperty("p2p_call_enabled")
    public boolean getP2PCallEnabled() { return p2PCallEnabled; }
    @JsonProperty("p2p_call_enabled")
    public void setP2PCallEnabled(boolean value) { this.p2PCallEnabled = value; }
}
