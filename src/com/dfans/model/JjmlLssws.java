package com.dfans.model;

public class JjmlLssws {
    private Integer id;

    private String name;

    private Integer cdate;

    private String website;

    private String address;

    private Integer zcd;

    private String tel;

    private String xhhy;

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

    public Integer getCdate() {
        return cdate;
    }

    public void setCdate(Integer cdate) {
        this.cdate = cdate;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getZcd() {
        return zcd;
    }

    public void setZcd(Integer zcd) {
        this.zcd = zcd;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getXhhy() {
        return xhhy;
    }

    public void setXhhy(String xhhy) {
        this.xhhy = xhhy == null ? null : xhhy.trim();
    }
}