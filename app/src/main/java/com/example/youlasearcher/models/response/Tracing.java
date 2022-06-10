package com.example.youlasearcher.models.response;

import com.fasterxml.jackson.annotation.*;
import java.time.OffsetDateTime;

public class Tracing {
    private Long version;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private Long duration;
    private Execution execution;

    @JsonProperty("version")
    public Long getVersion() { return version; }
    @JsonProperty("version")
    public void setVersion(Long value) { this.version = value; }

    @JsonProperty("startTime")
    public OffsetDateTime getStartTime() { return startTime; }
    @JsonProperty("startTime")
    public void setStartTime(OffsetDateTime value) { this.startTime = value; }

    @JsonProperty("endTime")
    public OffsetDateTime getEndTime() { return endTime; }
    @JsonProperty("endTime")
    public void setEndTime(OffsetDateTime value) { this.endTime = value; }

    @JsonProperty("duration")
    public Long getDuration() { return duration; }
    @JsonProperty("duration")
    public void setDuration(Long value) { this.duration = value; }

    @JsonProperty("execution")
    public Execution getExecution() { return execution; }
    @JsonProperty("execution")
    public void setExecution(Execution value) { this.execution = value; }
}
