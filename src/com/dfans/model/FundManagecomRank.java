package com.dfans.model;

public class FundManagecomRank {
    private Integer id;

    private Integer year;

    private Integer quarter;

    private Integer rank;

    private String name;

    private Float manageScale;

    private Float publicNum;

    private Integer type;

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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Float getManageScale() {
        return manageScale;
    }

    public void setManageScale(Float manageScale) {
        this.manageScale = manageScale;
    }

    public Float getPublicNum() {
        return publicNum;
    }

    public void setPublicNum(Float publicNum) {
        this.publicNum = publicNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}