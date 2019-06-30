package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FundCpslpm {
    private Integer id;

    private Integer rank;

    private Date fdate;

    private String name;

    private Float fxcpsl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Date getFdate() {
        return fdate;
    }

    public void setFdate(String fdate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        try {
			this.fdate = simpleDateFormat.parse(fdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Float getFxcpsl() {
        return fxcpsl;
    }

    public void setFxcpsl(Float fxcpsl) {
        this.fxcpsl = fxcpsl;
    }
}