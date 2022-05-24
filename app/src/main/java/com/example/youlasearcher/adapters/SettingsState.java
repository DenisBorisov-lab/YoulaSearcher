package com.example.youlasearcher.adapters;

import android.widget.Switch;

public class SettingsState {
    private String title;
    private String subTitle;
    private Switch checkbox;

    public SettingsState(String title, String subTitle, Switch checkbox){
        this.title = title;
        this.subTitle = subTitle;
        this.checkbox = checkbox;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setCheckbox(Switch checkbox) {
        this.checkbox = checkbox;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public Switch getCheckbox() {
        return checkbox;
    }
}
