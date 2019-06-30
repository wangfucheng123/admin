package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FundManager {
    private Integer id;

    private Integer baseId;

    private String comname;

    private String managerName;

    private String fundCode;

    private String fundName;

    private String relevantInfo;

    private Integer fundType;

    private Date startDate;

    private Date endDate;

    private String tenure;

    private Float jobReturn;

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

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode == null ? null : fundCode.trim();
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName == null ? null : fundName.trim();
    }

    public String getRelevantInfo() {
        return relevantInfo;
    }

    public void setRelevantInfo(String relevantInfo) {
        this.relevantInfo = relevantInfo == null ? null : relevantInfo.trim();
    }

    public Integer getFundType() {
        return fundType;
    }

    public void setFundType(Integer fundType) {
        this.fundType = fundType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.startDate = simpleDateFormat.parse(startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.endDate = simpleDateFormat.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure == null ? null : tenure.trim();
    }

    public Float getJobReturn() {
        return jobReturn;
    }

    public void setJobReturn(Float jobReturn) {
        this.jobReturn = jobReturn;
    }
}