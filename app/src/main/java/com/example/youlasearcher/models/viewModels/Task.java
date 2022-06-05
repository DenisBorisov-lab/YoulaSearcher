package com.example.youlasearcher.models.viewModels;

public class Task {
    private int minuteTime;
    private String time;
    private String url;

    public void setMinuteTime(int minuteTime) {
        this.minuteTime = minuteTime;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getMinuteTime() {
        return minuteTime;
    }

    public String getTime() {
        return time;
    }

    public String getUrl() {
        return url;
    }

    public Task(int minuteTime, String time, String url) {
        this.minuteTime = minuteTime;
        this.time = time;
        this.url = url;
    }
}
