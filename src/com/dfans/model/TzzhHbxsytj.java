package com.dfans.model;

public class TzzhHbxsytj {
    private Integer id;

    private Integer year;

    private Integer quarter;

    private String dateInterval;

    private Float assetsProportion;

    private Float liabilitiesProportion;

    private Float avgTerm;

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

    public String getDateInterval() {
        return dateInterval;
    }

    public void setDateInterval(String dateInterval) {
        this.dateInterval = dateInterval == null ? null : dateInterval.trim();
    }

    public Float getAssetsProportion() {
        return assetsProportion;
    }

    public void setAssetsProportion(Float assetsProportion) {
        this.assetsProportion = assetsProportion;
    }

    public Float getLiabilitiesProportion() {
        return liabilitiesProportion;
    }

    public void setLiabilitiesProportion(Float liabilitiesProportion) {
        this.liabilitiesProportion = liabilitiesProportion;
    }

    public Float getAvgTerm() {
        return avgTerm;
    }

    public void setAvgTerm(Float avgTerm) {
        this.avgTerm = avgTerm;
    }
}