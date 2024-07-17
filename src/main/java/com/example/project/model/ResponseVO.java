package com.example.project.model;

public class ResponseVO {

    private HeaderVO header;
    private BodyVO body;


    public ResponseVO(){}

    public ResponseVO(BodyVO body, HeaderVO header){
        this.header = header;
        this.body = body;
    }

    public HeaderVO getHeader() {
        return header;
    }
    public void setHeader(HeaderVO header) {
        this.header = header;
    }
    public BodyVO getBody() {
        return body;
    }
    public void setBody(BodyVO body) {
        this.body = body;
    }


    public String toString() {
        return "WeatherSearchResultVO {header=" + header
                + ", body=" + body
                + ", getHeader()=" + getHeader()
                + ", getBody()=" + getBody()
                + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "}";
    }

}
