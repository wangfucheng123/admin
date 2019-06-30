package com.dfans.model;

public class BankDebtYear {
    private Integer id;

    private Integer year;

    private Float totalAsset;

    private Float totalDebt;

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

    public Float getTotalAsset() {
        return totalAsset;
    }

    public void setTotalAsset(Float totalAsset) {
        this.totalAsset = totalAsset;
    }

    public Float getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(Float totalDebt) {
        this.totalDebt = totalDebt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}