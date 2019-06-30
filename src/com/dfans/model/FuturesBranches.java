package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FuturesBranches {
    private Integer id;

    private Integer baseId;

    private String xh;

    private String name;

    private Integer pSzd;

    private String xkzh;

    private Date cdate;

    private String fzr;

    private String khfwtsdh;

    private String xxdz;

    private String email;

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

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh == null ? null : xh.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getpSzd() {
        return pSzd;
    }

    public void setpSzd(Integer pSzd) {
        this.pSzd = pSzd;
    }

    public String getXkzh() {
        return xkzh;
    }

    public void setXkzh(String xkzh) {
        this.xkzh = xkzh == null ? null : xkzh.trim();
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.cdate = simpleDateFormat.parse(cdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public String getFzr() {
        return fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr == null ? null : fzr.trim();
    }

    public String getKhfwtsdh() {
        return khfwtsdh;
    }

    public void setKhfwtsdh(String khfwtsdh) {
        this.khfwtsdh = khfwtsdh == null ? null : khfwtsdh.trim();
    }

    public String getXxdz() {
        return xxdz;
    }

    public void setXxdz(String xxdz) {
        this.xxdz = xxdz == null ? null : xxdz.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}