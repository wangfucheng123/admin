package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BrokerEmpSituation {
    private Integer id;

    private String name;

    private Integer gender;

    private String education;

    private String certificateNum;

    private String practiceInstitute;

    private String practicePost;

    private Date getDate;

    private Date endDate;

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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum == null ? null : certificateNum.trim();
    }

    public String getPracticeInstitute() {
        return practiceInstitute;
    }

    public void setPracticeInstitute(String practiceInstitute) {
        this.practiceInstitute = practiceInstitute == null ? null : practiceInstitute.trim();
    }

    public String getPracticePost() {
        return practicePost;
    }

    public void setPracticePost(String practicePost) {
        this.practicePost = practicePost == null ? null : practicePost.trim();
    }

    public Date getGetDate() {
        return getDate;
    }

    public void setGetDate(String getDate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.getDate = simpleDateFormat.parse(getDate);
		} catch (ParseException e) {
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