package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BrokerAgent {
    private Integer id;

    private String name;

    private Integer gender;

    private String certificateNum;

    private String agency;

    private String certificateCategory;

    private String practiceCategory;

    private Date effectiveDate;

    private String changeRecord;

    private String creditRecord;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum == null ? null : certificateNum.trim();
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency == null ? null : agency.trim();
    }

    public String getCertificateCategory() {
        return certificateCategory;
    }

    public void setCertificateCategory(String certificateCategory) {
        this.certificateCategory = certificateCategory == null ? null : certificateCategory.trim();
    }

    public String getPracticeCategory() {
        return practiceCategory;
    }

    public void setPracticeCategory(String practiceCategory) {
        this.practiceCategory = practiceCategory == null ? null : practiceCategory.trim();
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.effectiveDate = simpleDateFormat.parse(effectiveDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

    public String getChangeRecord() {
        return changeRecord;
    }

    public void setChangeRecord(String changeRecord) {
        this.changeRecord = changeRecord == null ? null : changeRecord.trim();
    }

    public String getCreditRecord() {
        return creditRecord;
    }

    public void setCreditRecord(String creditRecord) {
        this.creditRecord = creditRecord == null ? null : creditRecord.trim();
    }
}