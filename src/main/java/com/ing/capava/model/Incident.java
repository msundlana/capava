package com.ing.capava.model;


import java.time.LocalDateTime;

public class Incident {
    private String assetName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int severity;

    public Incident() {
    }

    public Incident(String assetName, LocalDateTime startTime, LocalDateTime endTime, int severity) {
        this.assetName = assetName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.severity = severity;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }
}
