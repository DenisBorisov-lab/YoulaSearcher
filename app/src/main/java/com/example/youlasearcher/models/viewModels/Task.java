package com.example.youlasearcher.models.viewModels;

public class Task {
    private int minuteTime;
    private String time;
    private String url;

    public Task(int minuteTime, String time, String url) {
        this.minuteTime = minuteTime;
        this.time = time;
        this.url = url;
    }

    public int getMinuteTime() {
        return minuteTime;
    }

    public void setMinuteTime(int minuteTime) {
        this.minuteTime = minuteTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
