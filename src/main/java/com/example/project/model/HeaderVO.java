package com.example.project.model;

public class HeaderVO {
    private String resultCode;
    private String resultMsg;

    public HeaderVO(){

    }

    public HeaderVO(String resultCode, String resultMsg){
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
    public String getResultMsg() {
        return resultMsg;
    }
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String toString(){
        return "HeaderVo {resultCode=" + resultCode
                + ", resultMsg=" + resultMsg
                + ", getResultCode()=" + getResultCode()
                + ", getResultMsg()=" + getResultMsg()
                + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "}";
    }

}
