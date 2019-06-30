package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class BaseZxpjjg {
    private Integer id;

    private String chName;

    private String sChName;

    
    private Date createDate;

    private String enName;

    private String sEnName;
    
    private String hydm;
    
    private String hysx;
    
	private String hyzsdm;

    private String ywzgxkz;

    private String fddbr;

    private String zjl;

    private String zczb;

    private String sszb;

    private String jzc;

    private String xxzcdz;

    private String zcdyzbm;

    private Integer zcs;

    private String bgdz;

    private String bgdzyzbm;

    private String khfwtsdh;

    private String webUrl;

    private String email;

    private String phone;

    private String fax;

    private String zzjg;

    private String ygrs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName == null ? null : chName.trim();
    }

    public String getsChName() {
        return sChName;
    }

    public void setsChName(String sChName) {
        this.sChName = sChName == null ? null : sChName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.createDate = simpleDateFormat.parse(createDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    public String getsEnName() {
        return sEnName;
    }

    public void setsEnName(String sEnName) {
        this.sEnName = sEnName == null ? null : sEnName.trim();
    }

    public String getHydm() {
		return hydm;
	}

	public void setHydm(String hydm) {
		this.hydm = hydm;
	}

	public String getHysx() {
		return hysx;
	}

	public void setHysx(String hysx) {
		this.hysx = hysx;
	}

	public String getHyzsdm() {
		return hyzsdm;
	}

	public void setHyzsdm(String hyzsdm) {
		this.hyzsdm = hyzsdm;
	}
	
    public String getYwzgxkz() {
        return ywzgxkz;
    }

    public void setYwzgxkz(String ywzgxkz) {
        this.ywzgxkz = ywzgxkz == null ? null : ywzgxkz.trim();
    }

    public String getFddbr() {
        return fddbr;
    }

    public void setFddbr(String fddbr) {
        this.fddbr = fddbr == null ? null : fddbr.trim();
    }

    public String getZjl() {
        return zjl;
    }

    public void setZjl(String zjl) {
        this.zjl = zjl == null ? null : zjl.trim();
    }

    public String getZczb() {
        return zczb;
    }

    public void setZczb(String zczb) {
        this.zczb = zczb == null ? null : zczb.trim();
    }

    public String getSszb() {
        return sszb;
    }

    public void setSszb(String sszb) {
        this.sszb = sszb == null ? null : sszb.trim();
    }

    public String getJzc() {
        return jzc;
    }

    public void setJzc(String jzc) {
        this.jzc = jzc == null ? null : jzc.trim();
    }

    public String getXxzcdz() {
        return xxzcdz;
    }

    public void setXxzcdz(String xxzcdz) {
        this.xxzcdz = xxzcdz == null ? null : xxzcdz.trim();
    }

    public String getZcdyzbm() {
        return zcdyzbm;
    }

    public void setZcdyzbm(String zcdyzbm) {
        this.zcdyzbm = zcdyzbm == null ? null : zcdyzbm.trim();
    }

    public Integer getZcs() {
        return zcs;
    }

    public void setZcs(Integer zcs) {
        this.zcs = zcs;
    }

    public String getBgdz() {
        return bgdz;
    }

    public void setBgdz(String bgdz) {
        this.bgdz = bgdz == null ? null : bgdz.trim();
    }

    public String getBgdzyzbm() {
        return bgdzyzbm;
    }

    public void setBgdzyzbm(String bgdzyzbm) {
        this.bgdzyzbm = bgdzyzbm == null ? null : bgdzyzbm.trim();
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getZzjg() {
        return zzjg;
    }

    public void setZzjg(String zzjg) {
        this.zzjg = zzjg == null ? null : zzjg.trim();
    }

    public String getYgrs() {
        return ygrs;
    }

    public void setYgrs(String ygrs) {
        this.ygrs = ygrs == null ? null : ygrs.trim();
    }
}