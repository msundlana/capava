package com.ing.capava.model;

public class Asset {
    private String name;
    private Integer totalIncidents;
    private long upTime;
    private Integer rating;

    public Asset() {
    }

    public Asset(String name, Integer totalIncidents, long upTime, Integer rating) {
        this.name = name;
        this.totalIncidents = totalIncidents;
        this.upTime = upTime;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalIncidents() {
        return totalIncidents;
    }

    public void setTotalIncidents(Integer totalIncidents) {
        this.totalIncidents = totalIncidents;
    }

    public long getUpTime() {
        return upTime;
    }

    public void setUpTime(long upTime) {
        this.upTime = upTime;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
