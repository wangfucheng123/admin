package com.dfans.model;

public class FuturesFinancial {
    private Integer id;

    private Integer baseId;

    private Integer year;

    private Float jzb;

    private Float jzc;

    private Float qyze;

    private Float sxfsr;

    private Float jlr;

    private String zskjsws;

    private Integer dicSjyj;

    private String remark;

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Float getJzb() {
        return jzb;
    }

    public void setJzb(Float jzb) {
        this.jzb = jzb;
    }

    public Float getJzc() {
        return jzc;
    }

    public void setJzc(Float jzc) {
        this.jzc = jzc;
    }

    public Float getQyze() {
        return qyze;
    }

    public void setQyze(Float qyze) {
        this.qyze = qyze;
    }

    public Float getSxfsr() {
        return sxfsr;
    }

    public void setSxfsr(Float sxfsr) {
        this.sxfsr = sxfsr;
    }

    public Float getJlr() {
        return jlr;
    }

    public void setJlr(Float jlr) {
        this.jlr = jlr;
    }

    public String getZskjsws() {
        return zskjsws;
    }

    public void setZskjsws(String zskjsws) {
        this.zskjsws = zskjsws == null ? null : zskjsws.trim();
    }

    public Integer getDicSjyj() {
        return dicSjyj;
    }

    public void setDicSjyj(Integer dicSjyj) {
        this.dicSjyj = dicSjyj;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}