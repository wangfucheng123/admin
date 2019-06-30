package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FundScaleChange {
    private Integer id;

    private Integer baseId;

    private String comeName;

    private Date bdate;

    private Float durationBuy;

    private Float durationRedeem;

    private Float finalTotal;

    private Float finalNetAsset;

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

    public String getComeName() {
        return comeName;
    }

    public void setComeName(String comeName) {
        this.comeName = comeName == null ? null : comeName.trim();
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.bdate = simpleDateFormat.parse(bdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public Float getDurationBuy() {
        return durationBuy;
    }

    public void setDurationBuy(Float durationBuy) {
        this.durationBuy = durationBuy;
    }

    public Float getDurationRedeem() {
        return durationRedeem;
    }

    public void setDurationRedeem(Float durationRedeem) {
        this.durationRedeem = durationRedeem;
    }

    public Float getFinalTotal() {
        return finalTotal;
    }

    public void setFinalTotal(Float finalTotal) {
        this.finalTotal = finalTotal;
    }

    public Float getFinalNetAsset() {
        return finalNetAsset;
    }

    public void setFinalNetAsset(Float finalNetAsset) {
        this.finalNetAsset = finalNetAsset;
    }
}