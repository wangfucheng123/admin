package com.dfans.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsurancePension {
    private Integer id;

    private Date statisticalTime;

    private String companyName;

    private BigDecimal compEntrAsse;

    private BigDecimal compInveAsse;

    private BigDecimal pensionOtherPay;

    private BigDecimal compEntrProp;

    private BigDecimal compInveProp;

    private BigDecimal pensOtherProp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStatisticalTime() {
        return statisticalTime;
    }

    public void setStatisticalTime(String statisticalTime) {
    	  
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        try {
			this.statisticalTime = simpleDateFormat.parse(statisticalTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
    
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public BigDecimal getCompEntrAsse() {
        return compEntrAsse;
    }

    public void setCompEntrAsse(BigDecimal compEntrAsse) {
        this.compEntrAsse = compEntrAsse;
    }

    public BigDecimal getCompInveAsse() {
        return compInveAsse;
    }

    public void setCompInveAsse(BigDecimal compInveAsse) {
        this.compInveAsse = compInveAsse;
    }

    public BigDecimal getPensionOtherPay() {
        return pensionOtherPay;
    }

    public void setPensionOtherPay(BigDecimal pensionOtherPay) {
        this.pensionOtherPay = pensionOtherPay;
    }

    public BigDecimal getCompEntrProp() {
        return compEntrProp;
    }

    public void setCompEntrProp(BigDecimal compEntrProp) {
        this.compEntrProp = compEntrProp;
    }

    public BigDecimal getCompInveProp() {
        return compInveProp;
    }

    public void setCompInveProp(BigDecimal compInveProp) {
        this.compInveProp = compInveProp;
    }

    public BigDecimal getPensOtherProp() {
        return pensOtherProp;
    }

    public void setPensOtherProp(BigDecimal pensOtherProp) {
        this.pensOtherProp = pensOtherProp;
    }
}