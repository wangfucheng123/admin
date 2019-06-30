package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BankDebtMonth {
    private Integer id;

    private Date date;

    private Float bankTotalAsset;

    private Float bankTotalDebt;

    private Float businessTotalAsset;

    private Float businessTotalDebt;

    private Float bigTotalAsset;

    private Float bigTotalDebt;

    private Float shareTotalAsset;

    private Float shareTotalDebt;

    private Float cityTotalAsset;

    private Float cityTotalDebt;

    private Float countrysideTotalAsset;

    private Float countrysideTotalDebt;

    private Float otherTotalAsset;

    private Float otherTotalDebt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.date = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

    public Float getBankTotalAsset() {
        return bankTotalAsset;
    }

    public void setBankTotalAsset(Float bankTotalAsset) {
        this.bankTotalAsset = bankTotalAsset;
    }

    public Float getBankTotalDebt() {
        return bankTotalDebt;
    }

    public void setBankTotalDebt(Float bankTotalDebt) {
        this.bankTotalDebt = bankTotalDebt;
    }

    public Float getBusinessTotalAsset() {
        return businessTotalAsset;
    }

    public void setBusinessTotalAsset(Float businessTotalAsset) {
        this.businessTotalAsset = businessTotalAsset;
    }

    public Float getBusinessTotalDebt() {
        return businessTotalDebt;
    }

    public void setBusinessTotalDebt(Float businessTotalDebt) {
        this.businessTotalDebt = businessTotalDebt;
    }

    public Float getBigTotalAsset() {
        return bigTotalAsset;
    }

    public void setBigTotalAsset(Float bigTotalAsset) {
        this.bigTotalAsset = bigTotalAsset;
    }

    public Float getBigTotalDebt() {
        return bigTotalDebt;
    }

    public void setBigTotalDebt(Float bigTotalDebt) {
        this.bigTotalDebt = bigTotalDebt;
    }

    public Float getShareTotalAsset() {
        return shareTotalAsset;
    }

    public void setShareTotalAsset(Float shareTotalAsset) {
        this.shareTotalAsset = shareTotalAsset;
    }

    public Float getShareTotalDebt() {
        return shareTotalDebt;
    }

    public void setShareTotalDebt(Float shareTotalDebt) {
        this.shareTotalDebt = shareTotalDebt;
    }

    public Float getCityTotalAsset() {
        return cityTotalAsset;
    }

    public void setCityTotalAsset(Float cityTotalAsset) {
        this.cityTotalAsset = cityTotalAsset;
    }

    public Float getCityTotalDebt() {
        return cityTotalDebt;
    }

    public void setCityTotalDebt(Float cityTotalDebt) {
        this.cityTotalDebt = cityTotalDebt;
    }

    public Float getCountrysideTotalAsset() {
        return countrysideTotalAsset;
    }

    public void setCountrysideTotalAsset(Float countrysideTotalAsset) {
        this.countrysideTotalAsset = countrysideTotalAsset;
    }

    public Float getCountrysideTotalDebt() {
        return countrysideTotalDebt;
    }

    public void setCountrysideTotalDebt(Float countrysideTotalDebt) {
        this.countrysideTotalDebt = countrysideTotalDebt;
    }

    public Float getOtherTotalAsset() {
        return otherTotalAsset;
    }

    public void setOtherTotalAsset(Float otherTotalAsset) {
        this.otherTotalAsset = otherTotalAsset;
    }

    public Float getOtherTotalDebt() {
        return otherTotalDebt;
    }

    public void setOtherTotalDebt(Float otherTotalDebt) {
        this.otherTotalDebt = otherTotalDebt;
    }
}