package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FuturesIntegrity {
    private Integer id;

    private Integer baseId;

    private Date cdate;

    private Integer dicLb;

    private String ssjg;

    private String wh;

    private String qkjj;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public Integer getDicLb() {
        return dicLb;
    }

    public void setDicLb(Integer dicLb) {
        this.dicLb = dicLb;
    }

    public String getSsjg() {
        return ssjg;
    }

    public void setSsjg(String ssjg) {
        this.ssjg = ssjg == null ? null : ssjg.trim();
    }

    public String getWh() {
        return wh;
    }

    public void setWh(String wh) {
        this.wh = wh == null ? null : wh.trim();
    }

    public String getQkjj() {
        return qkjj;
    }

    public void setQkjj(String qkjj) {
        this.qkjj = qkjj == null ? null : qkjj.trim();
    }
}