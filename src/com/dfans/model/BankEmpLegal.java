package com.dfans.model;

public class BankEmpLegal {
    private Integer id;

    private Integer year;

    private String org;

    private Integer empNum;

    private Integer legalNum;

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

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org == null ? null : org.trim();
    }

    public Integer getEmpNum() {
        return empNum;
    }

    public void setEmpNum(Integer empNum) {
        this.empNum = empNum;
    }

    public Integer getLegalNum() {
        return legalNum;
    }

    public void setLegalNum(Integer legalNum) {
        this.legalNum = legalNum;
    }
}