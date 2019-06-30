package com.dfans.model;

public class BrokerZqgsml {
    private Integer id;

    private Integer xh;

    private String name;

    private Integer xq1;

    private Integer xq2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getXq1() {
        return xq1;
    }

    public void setXq1(Integer xq1) {
        this.xq1 = xq1;
    }

    public Integer getXq2() {
        return xq2;
    }

    public void setXq2(Integer xq2) {
        this.xq2 = xq2;
    }
}