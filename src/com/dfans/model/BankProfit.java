package com.dfans.model;

public class BankProfit {
    private Integer id;

    private Integer year;

    private Float assetProfit;

    private Float capitalProfit;

    private Integer flag;

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

    public Float getAssetProfit() {
        return assetProfit;
    }

    public void setAssetProfit(Float assetProfit) {
        this.assetProfit = assetProfit;
    }

    public Float getCapitalProfit() {
        return capitalProfit;
    }

    public void setCapitalProfit(Float capitalProfit) {
        this.capitalProfit = capitalProfit;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}