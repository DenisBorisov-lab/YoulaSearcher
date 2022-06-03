package com.example.youlasearcher.activities;

public class State {
    private String title;
    private String subTitle;
    private int symbolResource;

    public State(String title, String subTitle, int symbolResource) {
        this.title = title;
        this.subTitle = subTitle;
        this.symbolResource = symbolResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getSymbolResource() {
        return symbolResource;
    }

    public void setSymbolResource(int symbolResource) {
        this.symbolResource = symbolResource;
    }
}
