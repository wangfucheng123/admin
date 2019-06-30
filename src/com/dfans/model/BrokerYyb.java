package com.dfans.model;

public class BrokerYyb {
    private Integer id;

    private Integer baseId;

    private String name;

    private String bgdz;

    private String yybfzr;

    private String zcdz;

    private String khfwtsdh;

    private String zjjtsdh;

    private Double lng;

    private Double lat;

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

    public String getBgdz() {
        return bgdz;
    }

    public void setBgdz(String bgdz) {
        this.bgdz = bgdz == null ? null : bgdz.trim();
    }

    public String getYybfzr() {
        return yybfzr;
    }

    public void setYybfzr(String yybfzr) {
        this.yybfzr = yybfzr == null ? null : yybfzr.trim();
    }

    public String getZcdz() {
        return zcdz;
    }

    public void setZcdz(String zcdz) {
        this.zcdz = zcdz == null ? null : zcdz.trim();
    }

    public String getKhfwtsdh() {
        return khfwtsdh;
    }

    public void setKhfwtsdh(String khfwtsdh) {
        this.khfwtsdh = khfwtsdh == null ? null : khfwtsdh.trim();
    }

    public String getZjjtsdh() {
        return zjjtsdh;
    }

    public void setZjjtsdh(String zjjtsdh) {
        this.zjjtsdh = zjjtsdh == null ? null : zjjtsdh.trim();
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}