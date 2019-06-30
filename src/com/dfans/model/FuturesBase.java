package com.dfans.model;

public class FuturesBase {
    private Integer id;

    private String name;

    private String xkzh;

    private String dicJyfw;

    private Integer zglb;

    private String zgjysmc;

    private String zczb;

    private String gszs;

    private String fddbr;

    private String bgdz;

    private String khfwtsdh;

    private String webUrl;

    private String email;

    private String logo;

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

    public String getXkzh() {
        return xkzh;
    }

    public void setXkzh(String xkzh) {
        this.xkzh = xkzh == null ? null : xkzh.trim();
    }

    public String getDicJyfw() {
        return dicJyfw;
    }

    public void setDicJyfw(String dicJyfw) {
        this.dicJyfw = dicJyfw == null ? null : dicJyfw.trim();
    }

    public Integer getZglb() {
        return zglb;
    }

    public void setZglb(Integer zglb) {
        this.zglb = zglb;
    }

    public String getZgjysmc() {
        return zgjysmc;
    }

    public void setZgjysmc(String zgjysmc) {
        this.zgjysmc = zgjysmc == null ? null : zgjysmc.trim();
    }

    public String getZczb() {
        return zczb;
    }

    public void setZczb(String zczb) {
        this.zczb = zczb == null ? null : zczb.trim();
    }

    public String getGszs() {
        return gszs;
    }

    public void setGszs(String gszs) {
        this.gszs = gszs == null ? null : gszs.trim();
    }

    public String getFddbr() {
        return fddbr;
    }

    public void setFddbr(String fddbr) {
        this.fddbr = fddbr == null ? null : fddbr.trim();
    }

    public String getBgdz() {
        return bgdz;
    }

    public void setBgdz(String bgdz) {
        this.bgdz = bgdz == null ? null : bgdz.trim();
    }

    public String getKhfwtsdh() {
        return khfwtsdh;
    }

    public void setKhfwtsdh(String khfwtsdh) {
        this.khfwtsdh = khfwtsdh == null ? null : khfwtsdh.trim();
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl == null ? null : webUrl.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }
}