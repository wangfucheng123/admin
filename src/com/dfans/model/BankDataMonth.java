package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BankDataMonth {
    private Integer id;

    private Date tdate;

    private Float totalAssets;

    private Float totalLiabilities;

    private String type;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public Float getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(Float totalAssets) {
        this.totalAssets = totalAssets;
    }

    public Float getTotalLiabilities() {
        return totalLiabilities;
    }

    public void setTotalLiabilities(Float totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}