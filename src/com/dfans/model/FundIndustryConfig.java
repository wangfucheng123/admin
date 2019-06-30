package com.dfans.model;

public class FundIndustryConfig {
    private Integer id;

    private Integer baseId;

    private String comname;

    private Integer year;

    private Integer quarter;

    private String industryType;

    private Integer holdNum;

    private Float netProportion;

    private Float marketValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBaseId() {
        return baseId;
    }

    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname == null ? null : comname.trim();
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

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType == null ? null : industryType.trim();
    }

    public Integer getHoldNum() {
        return holdNum;
    }

    public void setHoldNum(Integer holdNum) {
        this.holdNum = holdNum;
    }

    public Float getNetProportion() {
        return netProportion;
    }

    public void setNetProportion(Float netProportion) {
        this.netProportion = netProportion;
    }

    public Float getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Float marketValue) {
        this.marketValue = marketValue;
    }
}