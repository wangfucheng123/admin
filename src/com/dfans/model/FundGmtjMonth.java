package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FundGmtjMonth {
    private Integer id;

    private Date tdate;

    private String lb;

    private Float jjsl;

    private Float fe;

    private Float jz;

    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTdate() {
        return tdate;
    }

    public void setTdate(String tdate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.tdate = simpleDateFormat.parse(tdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

    public String getLb() {
        return lb;
    }

    public void setLb(String lb) {
        this.lb = lb == null ? null : lb.trim();
    }

    public Float getJjsl() {
        return jjsl;
    }

    public void setJjsl(Float jjsl) {
        this.jjsl = jjsl;
    }

    public Float getFe() {
        return fe;
    }

    public void setFe(Float fe) {
        this.fe = fe;
    }

    public Float getJz() {
        return jz;
    }

    public void setJz(Float jz) {
        this.jz = jz;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}