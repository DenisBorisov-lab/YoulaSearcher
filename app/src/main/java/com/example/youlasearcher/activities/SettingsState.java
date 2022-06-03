package com.example.youlasearcher.activities;

public class SettingsState {
    private String title;
    private String subTitle;
    private boolean isChecked;

    public SettingsState(String title, String subTitle, boolean isChecked) {
        this.title = title;
        this.subTitle = subTitle;
        this.isChecked = isChecked;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public boolean isChecked() {
        return isChecked;
    }
}
