package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FuturesPartner {
    private Integer id;

    private Integer baseId;

    private String name;

    private Float cgbl;

    private Date cdate;

    private String fddbr;

    private String zczb;

    private String bgdz;

    private String webUrl;

    private Integer dicSshy;

    private Integer dicJjlx;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Float getCgbl() {
        return cgbl;
    }

    public void setCgbl(Float cgbl) {
        this.cgbl = cgbl;
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

    public String getFddbr() {
        return fddbr;
    }

    public void setFddbr(String fddbr) {
        this.fddbr = fddbr == null ? null : fddbr.trim();
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

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl == null ? null : webUrl.trim();
    }

    public Integer getDicSshy() {
        return dicSshy;
    }

    public void setDicSshy(Integer dicSshy) {
        this.dicSshy = dicSshy;
    }

    public Integer getDicJjlx() {
        return dicJjlx;
    }

    public void setDicJjlx(Integer dicJjlx) {
        this.dicJjlx = dicJjlx;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}