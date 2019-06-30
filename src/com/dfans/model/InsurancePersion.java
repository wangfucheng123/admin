package com.dfans.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsurancePersion {
    private Integer id;

    private Date statisticalTime;

    private Integer capitalStructure;

    private String companyName;

    private BigDecimal insuPremInco;

    private BigDecimal insuAddePay;

    private BigDecimal segrAddePay;

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
    
    
    public Integer getCapitalStructure() {
        return capitalStructure;
    }

    public void setCapitalStructure(Integer capitalStructure) {
        this.capitalStructure = capitalStructure;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public BigDecimal getInsuPremInco() {
        return insuPremInco;
    }

    public void setInsuPremInco(BigDecimal insuPremInco) {
        this.insuPremInco = insuPremInco;
    }

    public BigDecimal getInsuAddePay() {
        return insuAddePay;
    }

    public void setInsuAddePay(BigDecimal insuAddePay) {
        this.insuAddePay = insuAddePay;
    }

    public BigDecimal getSegrAddePay() {
        return segrAddePay;
    }

    public void setSegrAddePay(BigDecimal segrAddePay) {
        this.segrAddePay = segrAddePay;
    }
}