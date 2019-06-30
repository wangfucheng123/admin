package com.dfans.model;

public class BrokerFgs {
    private Integer id;

    private Integer baseId;

    private String name;

    private String bgdz;

    private String ywfw;

    private String fzr;

    private String khtsdh;

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

    public String getYwfw() {
        return ywfw;
    }

    public void setYwfw(String ywfw) {
        this.ywfw = ywfw == null ? null : ywfw.trim();
    }

    public String getFzr() {
        return fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr == null ? null : fzr.trim();
    }

    public String getKhtsdh() {
        return khtsdh;
    }

    public void setKhtsdh(String khtsdh) {
        this.khtsdh = khtsdh == null ? null : khtsdh.trim();
    }
}