package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrivateManageList {
    private Integer id;

    private String name;

    private String peopleName;

    private Integer registAddr;

    private Integer registCity;

    private String registNum;

    private Date createDate;

    private Date registDate;

    private Integer type;

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

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName == null ? null : peopleName.trim();
    }

    public Integer getRegistAddr() {
        return registAddr;
    }

    public void setRegistAddr(Integer registAddr) {
        this.registAddr = registAddr;
    }

    public Integer getRegistCity() {
        return registCity;
    }

    public void setRegistCity(Integer registCity) {
        this.registCity = registCity;
    }

    public String getRegistNum() {
        return registNum;
    }

    public void setRegistNum(String registNum) {
        this.registNum = registNum == null ? null : registNum.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.createDate = simpleDateFormat.parse(createDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(String registDate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.registDate = simpleDateFormat.parse(registDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}