package com.dfans.model;

public class BrokerOrgQualification {
    private Integer id;

    private Integer baseId;

    private String orgQualification;

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

    public String getOrgQualification() {
        return orgQualification;
    }

    public void setOrgQualification(String orgQualification) {
        this.orgQualification = orgQualification == null ? null : orgQualification.trim();
    }
}