package com.dfans.model;

public class TProvince {
    private Integer proid;

    private String proname;

    private Integer prosort;

    private String proremark;

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname == null ? null : proname.trim();
    }

    public Integer getProsort() {
        return prosort;
    }

    public void setProsort(Integer prosort) {
        this.prosort = prosort;
    }

    public String getProremark() {
        return proremark;
    }

    public void setProremark(String proremark) {
        this.proremark = proremark == null ? null : proremark.trim();
    }
}