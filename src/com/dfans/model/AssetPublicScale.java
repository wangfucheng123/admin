package com.dfans.model;

public class AssetPublicScale {
    private Integer id;

    private Integer year;

    private Integer quarter;

    private String fundType;

    private Integer fundNum;

    private Float fundShare;

    private Float fundNet;

    private Integer sonType;

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

    public Float getFundShare() {
        return fundShare;
    }

    public void setFundShare(Float fundShare) {
        this.fundShare = fundShare;
    }

    public Float getFundNet() {
        return fundNet;
    }

    public void setFundNet(Float fundNet) {
        this.fundNet = fundNet;
    }

    public Integer getSonType() {
        return sonType;
    }

    public void setSonType(Integer sonType) {
        this.sonType = sonType;
    }
}