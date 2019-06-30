package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FundAssetAllocation {
    private Integer id;

    private Integer baseId;

    private String comname;

    private Date reportDate;

    private Float sharesRatio;

    private Float bondRatio;

    private Float cashRatio;

    private Float netAssets;

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

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname == null ? null : comname.trim();
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.reportDate = simpleDateFormat.parse(reportDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public Float getSharesRatio() {
        return sharesRatio;
    }

    public void setSharesRatio(Float sharesRatio) {
        this.sharesRatio = sharesRatio;
    }

    public Float getBondRatio() {
        return bondRatio;
    }

    public void setBondRatio(Float bondRatio) {
        this.bondRatio = bondRatio;
    }

    public Float getCashRatio() {
        return cashRatio;
    }

    public void setCashRatio(Float cashRatio) {
        this.cashRatio = cashRatio;
    }

    public Float getNetAssets() {
        return netAssets;
    }

    public void setNetAssets(Float netAssets) {
        this.netAssets = netAssets;
    }
}