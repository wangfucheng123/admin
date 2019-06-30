package com.dfans.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsuranceProperty {
    private Integer id;

    private Date statisticalTime;

    private String companyName;

    private BigDecimal premiumIncome;

    private Integer capitalStructure;

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

    public BigDecimal getPremiumIncome() {
        return premiumIncome;
    }

    public void setPremiumIncome(BigDecimal premiumIncome) {
        this.premiumIncome = premiumIncome;
    }

    public Integer getCapitalStructure() {
        return capitalStructure;
    }

    public void setCapitalStructure(Integer capitalStructure) {
        this.capitalStructure = capitalStructure;
    }
}