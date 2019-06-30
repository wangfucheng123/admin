package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FuturesExec {
    private Integer id;

    private Integer baseId;

    private String name;

    private Integer sex;

    private Integer dicPosition;

    private String zgpzwh;

    private Date cdate;

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

    public Integer getDicPosition() {
        return dicPosition;
    }

    public void setDicPosition(Integer dicPosition) {
        this.dicPosition = dicPosition;
    }

    public String getZgpzwh() {
        return zgpzwh;
    }

    public void setZgpzwh(String zgpzwh) {
        this.zgpzwh = zgpzwh == null ? null : zgpzwh.trim();
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
}