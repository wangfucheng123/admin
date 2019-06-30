package com.dfans.model;

public class BrokerBase {
    private Integer id;

    private String name;

    private Integer province;

    private Integer city;

    private String zcdz;

    private String frdb;

    private String xkzbh;

    private String zczb;

    private String bgdz;

    private String bgdyb;

    private String tsdh;

    private String webUrl;

    private String logo;

    private String zgxx;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getZcdz() {
        return zcdz;
    }

    public void setZcdz(String zcdz) {
        this.zcdz = zcdz == null ? null : zcdz.trim();
    }

    public String getFrdb() {
        return frdb;
    }

    public void setFrdb(String frdb) {
        this.frdb = frdb == null ? null : frdb.trim();
    }

    public String getXkzbh() {
        return xkzbh;
    }

    public void setXkzbh(String xkzbh) {
        this.xkzbh = xkzbh == null ? null : xkzbh.trim();
    }

    public String getZczb() {
        return zczb;
    }

    public void setZczb(String zczb) {
        this.zczb = zczb == null ? null : zczb.trim();
    }

    public String getBgdz() {
        return bgdz;
    }

    public void setBgdz(String bgdz) {
        this.bgdz = bgdz == null ? null : bgdz.trim();
    }

    public String getBgdyb() {
        return bgdyb;
    }

    public void setBgdyb(String bgdyb) {
        this.bgdyb = bgdyb == null ? null : bgdyb.trim();
    }

    public String getTsdh() {
        return tsdh;
    }

    public void setTsdh(String tsdh) {
        this.tsdh = tsdh == null ? null : tsdh.trim();
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl == null ? null : webUrl.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getZgxx() {
        return zgxx;
    }

    public void setZgxx(String zgxx) {
        this.zgxx = zgxx == null ? null : zgxx.trim();
    }
}