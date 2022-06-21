package com.denisbrisov.youlasearcher.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Tracing {
    private Long version;
    private Date startTime;
    private Date endTime;
    private Long duration;
    private Execution execution;

    @JsonProperty("version")
    public Long getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Long value) {
        this.version = value;
    }

    @JsonProperty("startTime")
    public Date getStartTime() {
        return startTime;
    }

    @JsonProperty("startTime")
    public void setStartTime(Date value) {
        this.startTime = value;
    }

    @JsonProperty("endTime")
    public Date getEndTime() {
        return endTime;
    }

    @JsonProperty("endTime")
    public void setEndTime(Date value) {
        this.endTime = value;
    }

    @JsonProperty("duration")
    public Long getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(Long value) {
        this.duration = value;
    }

    @JsonProperty("execution")
    public Execution getExecution() {
        return execution;
    }

    @JsonProperty("execution")
    public void setExecution(Execution value) {
        this.execution = value;
    }
}
