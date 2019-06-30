package com.dfans.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsuranceBusiness {
    private Integer id;

    private Date statisticalTime;

    private BigDecimal insuPremInco;

    private BigDecimal propInsuInco;

    private BigDecimal persInsuInco;

    private BigDecimal persLifeInco;

    private BigDecimal persHealInco;

    private BigDecimal persAcciInco;

    private BigDecimal insuAddePay;

    private BigDecimal segrAddePay;

    private BigDecimal endoCompPay;

    private BigDecimal insuPremPaid;

    private BigDecimal propInsuPaid;

    private BigDecimal persInsuPaid;

    private BigDecimal persLifePaid;

    private BigDecimal persHealPaid;

    private BigDecimal persAcciPaid;

    private BigDecimal operAdmiExpe;

    private BigDecimal depositBank;

    private BigDecimal investment;

    private BigDecimal totalAssets;

    private BigDecimal endoCompEntrAsse;

    private BigDecimal endoCompInveAsse;

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

    public BigDecimal getInsuPremInco() {
        return insuPremInco;
    }

    public void setInsuPremInco(BigDecimal insuPremInco) {
        this.insuPremInco = insuPremInco;
    }

    public BigDecimal getPropInsuInco() {
        return propInsuInco;
    }

    public void setPropInsuInco(BigDecimal propInsuInco) {
        this.propInsuInco = propInsuInco;
    }

    public BigDecimal getPersInsuInco() {
        return persInsuInco;
    }

    public void setPersInsuInco(BigDecimal persInsuInco) {
        this.persInsuInco = persInsuInco;
    }

    public BigDecimal getPersLifeInco() {
        return persLifeInco;
    }

    public void setPersLifeInco(BigDecimal persLifeInco) {
        this.persLifeInco = persLifeInco;
    }

    public BigDecimal getPersHealInco() {
        return persHealInco;
    }

    public void setPersHealInco(BigDecimal persHealInco) {
        this.persHealInco = persHealInco;
    }

    public BigDecimal getPersAcciInco() {
        return persAcciInco;
    }

    public void setPersAcciInco(BigDecimal persAcciInco) {
        this.persAcciInco = persAcciInco;
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

    public BigDecimal getEndoCompPay() {
        return endoCompPay;
    }

    public void setEndoCompPay(BigDecimal endoCompPay) {
        this.endoCompPay = endoCompPay;
    }

    public BigDecimal getInsuPremPaid() {
        return insuPremPaid;
    }

    public void setInsuPremPaid(BigDecimal insuPremPaid) {
        this.insuPremPaid = insuPremPaid;
    }

    public BigDecimal getPropInsuPaid() {
        return propInsuPaid;
    }

    public void setPropInsuPaid(BigDecimal propInsuPaid) {
        this.propInsuPaid = propInsuPaid;
    }

    public BigDecimal getPersInsuPaid() {
        return persInsuPaid;
    }

    public void setPersInsuPaid(BigDecimal persInsuPaid) {
        this.persInsuPaid = persInsuPaid;
    }

    public BigDecimal getPersLifePaid() {
        return persLifePaid;
    }

    public void setPersLifePaid(BigDecimal persLifePaid) {
        this.persLifePaid = persLifePaid;
    }

    public BigDecimal getPersHealPaid() {
        return persHealPaid;
    }

    public void setPersHealPaid(BigDecimal persHealPaid) {
        this.persHealPaid = persHealPaid;
    }

    public BigDecimal getPersAcciPaid() {
        return persAcciPaid;
    }

    public void setPersAcciPaid(BigDecimal persAcciPaid) {
        this.persAcciPaid = persAcciPaid;
    }

    public BigDecimal getOperAdmiExpe() {
        return operAdmiExpe;
    }

    public void setOperAdmiExpe(BigDecimal operAdmiExpe) {
        this.operAdmiExpe = operAdmiExpe;
    }

    public BigDecimal getDepositBank() {
        return depositBank;
    }

    public void setDepositBank(BigDecimal depositBank) {
        this.depositBank = depositBank;
    }

    public BigDecimal getInvestment() {
        return investment;
    }

    public void setInvestment(BigDecimal investment) {
        this.investment = investment;
    }

    public BigDecimal getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(BigDecimal totalAssets) {
        this.totalAssets = totalAssets;
    }

    public BigDecimal getEndoCompEntrAsse() {
        return endoCompEntrAsse;
    }

    public void setEndoCompEntrAsse(BigDecimal endoCompEntrAsse) {
        this.endoCompEntrAsse = endoCompEntrAsse;
    }

    public BigDecimal getEndoCompInveAsse() {
        return endoCompInveAsse;
    }

    public void setEndoCompInveAsse(BigDecimal endoCompInveAsse) {
        this.endoCompInveAsse = endoCompInveAsse;
    }
}