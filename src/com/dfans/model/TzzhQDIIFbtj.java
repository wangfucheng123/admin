package com.dfans.model;

public class TzzhQDIIFbtj {
    private Integer id;

    private Integer year;

    private Integer quarter;

    private String country;

    private Float tzed;

    private Double jzbl;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public Float getTzed() {
        return tzed;
    }

    public void setTzed(Float tzed) {
        this.tzed = tzed;
    }

    public Double getJzbl() {
        return jzbl;
    }

    public void setJzbl(Double jzbl) {
        this.jzbl = jzbl;
    }
}