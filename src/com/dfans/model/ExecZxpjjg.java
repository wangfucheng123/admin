package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExecZxpjjg {
    private Integer id;

    private Integer baseId;

    private String name;

    private Integer sex;

    private Integer xrzw;

    private Date startDate;

    private String cyzshm;

    private String zkhm;

    private Integer flag;

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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getXrzw() {
        return xrzw;
    }

    public void setXrzw(Integer xrzw) {
        this.xrzw = xrzw;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.startDate = simpleDateFormat.parse(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

    public String getCyzshm() {
        return cyzshm;
    }

    public void setCyzshm(String cyzshm) {
        this.cyzshm = cyzshm == null ? null : cyzshm.trim();
    }

    public String getZkhm() {
        return zkhm;
    }

    public void setZkhm(String zkhm) {
        this.zkhm = zkhm == null ? null : zkhm.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}