package com.dfans.model;

public class AssetPrivateScale {
    private Integer id;

    private Integer year;

    private Integer quarter;

    private String fundType;

    private Integer fundNum;

    private Float subscribeScale;

    private Float realSclle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType == null ? null : fundType.trim();
    }

    public Integer getFundNum() {
        return fundNum;
    }

    public void setFundNum(Integer fundNum) {
        this.fundNum = fundNum;
    }

    public Float getSubscribeScale() {
        return subscribeScale;
    }

    public void setSubscribeScale(Float subscribeScale) {
        this.subscribeScale = subscribeScale;
    }

    public Float getRealSclle() {
        return realSclle;
    }

    public void setRealSclle(Float realSclle) {
        this.realSclle = realSclle;
    }
}