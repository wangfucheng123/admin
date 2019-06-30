package com.dfans.model;

public class FundGmtjQuarter {
    private Integer id;

    private Integer year;

    private Integer quarter;

    private String fundtype;

    private Float jjzs;

    private Float qmzfe;

    private Float zcjz;

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

    public String getFundtype() {
        return fundtype;
    }

    public void setFundtype(String fundtype) {
        this.fundtype = fundtype == null ? null : fundtype.trim();
    }

    public Float getJjzs() {
        return jjzs;
    }

    public void setJjzs(Float jjzs) {
        this.jjzs = jjzs;
    }

    public Float getQmzfe() {
        return qmzfe;
    }

    public void setQmzfe(Float qmzfe) {
        this.qmzfe = qmzfe;
    }

    public Float getZcjz() {
        return zcjz;
    }

    public void setZcjz(Float zcjz) {
        this.zcjz = zcjz;
    }
}