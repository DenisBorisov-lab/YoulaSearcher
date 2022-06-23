package com.denisbrisov.youlasearcher.models.lot;

import com.fasterxml.jackson.annotation.*;

public class OwnerSettings {
    private boolean displayPhone;
    private boolean displayPhoneToAnon;
    private boolean displayChat;
    private Object p2PCallEnabled;
    private Object p2PVideoCallEnabled;
    private Object disabledCallAlert;
    private SettingsLocation location;
    private CallSettings callSettings;

    @JsonProperty("display_phone")
    public boolean getDisplayPhone() { return displayPhone; }
    @JsonProperty("display_phone")
    public void setDisplayPhone(boolean value) { this.displayPhone = value; }

    @JsonProperty("display_phone_to_anon")
    public boolean getDisplayPhoneToAnon() { return displayPhoneToAnon; }
    @JsonProperty("display_phone_to_anon")
    public void setDisplayPhoneToAnon(boolean value) { this.displayPhoneToAnon = value; }

    @JsonProperty("display_chat")
    public boolean getDisplayChat() { return displayChat; }
    @JsonProperty("display_chat")
    public void setDisplayChat(boolean value) { this.displayChat = value; }

    @JsonProperty("p2p_call_enabled")
    public Object getP2PCallEnabled() { return p2PCallEnabled; }
    @JsonProperty("p2p_call_enabled")
    public void setP2PCallEnabled(Object value) { this.p2PCallEnabled = value; }

    @JsonProperty("p2p_video_call_enabled")
    public Object getP2PVideoCallEnabled() { return p2PVideoCallEnabled; }
    @JsonProperty("p2p_video_call_enabled")
    public void setP2PVideoCallEnabled(Object value) { this.p2PVideoCallEnabled = value; }

    @JsonProperty("disabled_call_alert")
    public Object getDisabledCallAlert() { return disabledCallAlert; }
    @JsonProperty("disabled_call_alert")
    public void setDisabledCallAlert(Object value) { this.disabledCallAlert = value; }

    @JsonProperty("location")
    public SettingsLocation getLocation() { return location; }
    @JsonProperty("location")
    public void setLocation(SettingsLocation value) { this.location = value; }

    @JsonProperty("call_settings")
    public CallSettings getCallSettings() { return callSettings; }
    @JsonProperty("call_settings")
    public void setCallSettings(CallSettings value) { this.callSettings = value; }
}
