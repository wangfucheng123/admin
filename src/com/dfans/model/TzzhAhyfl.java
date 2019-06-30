package com.dfans.model;

public class TzzhAhyfl {
    private Integer id;

    private Integer year;

    private Integer quarter;

    private String fundType;

    private Float shares;

    private Float blend;

    private Float wholeMarket;

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

    public Float getShares() {
        return shares;
    }

    public void setShares(Float shares) {
        this.shares = shares;
    }

    public Float getBlend() {
        return blend;
    }

    public void setBlend(Float blend) {
        this.blend = blend;
    }

    public Float getWholeMarket() {
        return wholeMarket;
    }

    public void setWholeMarket(Float wholeMarket) {
        this.wholeMarket = wholeMarket;
    }
}