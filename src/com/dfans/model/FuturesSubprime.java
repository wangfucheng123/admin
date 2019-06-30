package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FuturesSubprime {
    private Integer id;

    private Integer baseId;

    private String name;

    private Integer dicZwxz;

    private String zwgm;

    private Date startDate;

    private Date endDate;

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

    public Integer getDicZwxz() {
        return dicZwxz;
    }

    public void setDicZwxz(Integer dicZwxz) {
        this.dicZwxz = dicZwxz;
    }

    public String getZwgm() {
        return zwgm;
    }

    public void setZwgm(String zwgm) {
        this.zwgm = zwgm == null ? null : zwgm.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.startDate = simpleDateFormat.parse(startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.endDate = simpleDateFormat.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}