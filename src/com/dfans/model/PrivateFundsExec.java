package com.dfans.model;

public class PrivateFundsExec {
    private Integer id;

    private Integer baseId;

    private String name;

    private Integer zw;

    private Integer cyzg;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getZw() {
        return zw;
    }

    public void setZw(Integer zw) {
        this.zw = zw;
    }

    public Integer getCyzg() {
        return cyzg;
    }

    public void setCyzg(Integer cyzg) {
        this.cyzg = cyzg;
    }
}