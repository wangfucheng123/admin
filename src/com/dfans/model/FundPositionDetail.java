package com.dfans.model;

public class FundPositionDetail {
    private Integer id;

    private Integer baseId;

    private String comname;

    private Integer year;

    private Integer quarter;

    private String stockCode;

    private String stockName;

    private Integer holdNum;

    private Float totalProportion;

    private Float holdShares;

    private Float positionValue;

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

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName == null ? null : stockName.trim();
    }

    public Integer getHoldNum() {
        return holdNum;
    }

    public void setHoldNum(Integer holdNum) {
        this.holdNum = holdNum;
    }

    public Float getTotalProportion() {
        return totalProportion;
    }

    public void setTotalProportion(Float totalProportion) {
        this.totalProportion = totalProportion;
    }

    public Float getHoldShares() {
        return holdShares;
    }

    public void setHoldShares(Float holdShares) {
        this.holdShares = holdShares;
    }

    public Float getPositionValue() {
        return positionValue;
    }

    public void setPositionValue(Float positionValue) {
        this.positionValue = positionValue;
    }
}