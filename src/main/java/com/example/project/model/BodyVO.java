package com.example.project.model;

public class BodyVO {
    private String dataType;
    private ItemsVO items;
    private int pageNo;
    private int numOfRows;
    private int totalCount;

    public BodyVO(){

    }

    public BodyVO (String dataType, ItemsVO items, int pageNo, int numOfRows, int totalCount){
        this.dataType = dataType;
        this.items = items;
        this.pageNo = pageNo;
        this.numOfRows = numOfRows;
        this.totalCount = totalCount;
    }

    public String getDataType() {
        return dataType;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    public ItemsVO getItems() {
        return items;
    }
    public void setItems(ItemsVO items) {
        this.items = items;
    }
    public int getPageNo() {
        return pageNo;
    }
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
    public int getNumOfRows() {
        return numOfRows;
    }
    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String toString(){
        return "BodyVo {dataType=" + dataType
                + ", items=" + items
                + ", pageNo=" + pageNo
                + ", numOfRows=" + numOfRows
                + ", totalCount=" + totalCount
                + ", getDataType()=" + getDataType()
                + ", getItems()=" + getItems()
                + ", getPageNo()=" + getPageNo()
                + ", getNumOfRows()=" + getNumOfRows()
                + ", getTotalCount()=" + getTotalCount()
                + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "}";
    }


}