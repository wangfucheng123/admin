package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FundGmjjgstj {
    private Integer id;

    private Date jdate;

    private Integer comcount;

    private Integer zwhzgs;

    private Integer zqjzggs;

    private Integer bxzggs;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getJdate() {
        return jdate;
    }

    public void setJdate(String jdate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.jdate = simpleDateFormat.parse(jdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

    public Integer getComcount() {
        return comcount;
    }

    public void setComcount(Integer comcount) {
        this.comcount = comcount;
    }

    public Integer getZwhzgs() {
        return zwhzgs;
    }

    public void setZwhzgs(Integer zwhzgs) {
        this.zwhzgs = zwhzgs;
    }

    public Integer getZqjzggs() {
        return zqjzggs;
    }

    public void setZqjzggs(Integer zqjzggs) {
        this.zqjzggs = zqjzggs;
    }

    public Integer getBxzggs() {
        return bxzggs;
    }

    public void setBxzggs(Integer bxzggs) {
        this.bxzggs = bxzggs;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}