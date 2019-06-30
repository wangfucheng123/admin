package com.dfans.model;

public class BankOwnerEquity {
    private Integer id;

    private Integer year;

    private Float equity;

    private String type;

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

    public Float getEquity() {
        return equity;
    }

    public void setEquity(Float equity) {
        this.equity = equity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}