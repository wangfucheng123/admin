package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JjmlXsjg {
    private Integer id;

    private String name;

    private Date hdate;

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

    public Date getHdate() {
        return hdate;
    }

    public void setHdate(String hdate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.hdate = simpleDateFormat.parse(hdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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