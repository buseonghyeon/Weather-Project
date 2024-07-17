package com.example.project.model;

public class WeatherSearchResultVO {
    private ResponseVO response;

    public ResponseVO getResponse() {
        return response;
    }

    public void setResponse(ResponseVO response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "ResponseVO{" + "response=" + response
                + ", getResponse()=" + getResponse()
                + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "}";
    }
}
