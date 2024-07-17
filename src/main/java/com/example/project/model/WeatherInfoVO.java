package com.example.project.model;

public class WeatherInfoVO {
    private String baseDate;
    private String baseTime;
    private String category;
    private String fcstDate;
    private String fcstTime;
    private String fcstValue;
    private int nx;
    private int ny;

    public String getBaseDate() {
        return baseDate;
    }
    public void setBaseDate(String baseDate) {
        this.baseDate = baseDate;
    }
    public String getBaseTime() {
        return baseTime;
    }
    public void setBaseTime(String baseTime) {
        this.baseTime = baseTime;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getFcstDate() {
        return fcstDate;
    }
    public void setFcstDate(String fcstDate) {
        this.fcstDate = fcstDate;
    }
    public String getFcstTime() {
        return fcstTime;
    }
    public void setFcstTime(String fcstTime) {
        this.fcstTime = fcstTime;
    }
    public String getFcstValue() {
        return fcstValue;
    }
    public void setFcstValue(String fcstValue) {
        this.fcstValue = fcstValue;
    }
    public int getNx() {
        return nx;
    }
    public void setNx(int nx) {
        this.nx = nx;
    }
    public int getNy() {
        return ny;
    }
    public void setNy(int ny) {
        this.ny = ny;
    }

    public String toString() {
        return "WeatherInfoVO [baseDate=" + baseDate
                + ", baseTime=" + baseTime
                + ", category=" + category
                + ", nx=" + nx
                + ", ny=" + ny
                + ", fcstDate=" + fcstDate
                + ", fcstTime=" + fcstTime
                + ", fcstValue=" + fcstValue
                + ", getBaseDate()=" + getBaseDate()
                + ", getBaseTime()=" + getBaseTime()
                + ", getCategory()=" + getCategory()
                + ", getNx()=" + getNx()
                + ", getNy()=" + getNy()
                + ", getFcstDate()=" + getFcstDate()
                + ", getFcstTime()=" + getFcstTime()
                + ", getFcstValue()=" + getFcstValue()
                + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";

    }
}