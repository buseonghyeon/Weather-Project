package com.example.project.model;

import java.util.List;

public class ItemsVO {
    private List<WeatherInfoVO> item;

    public ItemsVO () {

    }

    public ItemsVO(List<WeatherInfoVO> item) {
        this.item = item;
    }

    public List<WeatherInfoVO> getItem() {
        return item;
    }
    public void setItem(List<WeatherInfoVO> item) {
        this.item = item;
    }

    public String toString() {
        return "ItemsVO {item=" + item
                + ", getItem()=" + getItem()
                + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "}";
    }

}