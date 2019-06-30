package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FuturesHistory {
    private Integer id;

    private Integer baseId;

    private Date cdate;

    private String sjjc;

    private String sjlr;

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

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.cdate = simpleDateFormat.parse(cdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

    public String getSjjc() {
        return sjjc;
    }

    public void setSjjc(String sjjc) {
        this.sjjc = sjjc == null ? null : sjjc.trim();
    }

    public String getSjlr() {
        return sjlr;
    }

    public void setSjlr(String sjlr) {
        this.sjlr = sjlr == null ? null : sjlr.trim();
    }
}