package com.dfans.model;

public class AssetPrivateManage {
    private Integer id;

    private Integer year;

    private Integer quarter;

    private String manageType;

    private Integer registNum;

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

    public String getManageType() {
        return manageType;
    }

    public void setManageType(String manageType) {
        this.manageType = manageType == null ? null : manageType.trim();
    }

    public Integer getRegistNum() {
        return registNum;
    }

    public void setRegistNum(Integer registNum) {
        this.registNum = registNum;
    }
}