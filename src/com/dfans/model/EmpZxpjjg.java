package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmpZxpjjg {
    private Integer id;

    private Integer baseId;

    private String name;

    private Integer sex;

    private String zsbh;

    private Integer xrzw;

    private Date startDate;

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

    public String getZsbh() {
        return zsbh;
    }

    public void setZsbh(String zsbh) {
        this.zsbh = zsbh == null ? null : zsbh.trim();
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
}