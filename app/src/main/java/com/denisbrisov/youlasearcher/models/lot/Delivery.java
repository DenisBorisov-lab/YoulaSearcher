package com.denisbrisov.youlasearcher.models.lot;

import com.fasterxml.jackson.annotation.*;

public class Delivery {
    private String type;
    private String name;
    private long mode;
    private boolean isDefault;
    private Field[] fields;

    @JsonProperty("type")
    public String getType() { return type; }
    @JsonProperty("type")
    public void setType(String value) { this.type = value; }

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("mode")
    public long getMode() { return mode; }
    @JsonProperty("mode")
    public void setMode(long value) { this.mode = value; }

    @JsonProperty("is_default")
    public boolean getIsDefault() { return isDefault; }
    @JsonProperty("is_default")
    public void setIsDefault(boolean value) { this.isDefault = value; }

    @JsonProperty("fields")
    public Field[] getFields() { return fields; }
    @JsonProperty("fields")
    public void setFields(Field[] value) { this.fields = value; }
}
