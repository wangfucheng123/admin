package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FundHolderStructure {
    private Integer id;

    private Integer baseId;

    private String comname;

    private Date gdate;

    private Float orgProportion;

    private Float personalProportion;

    private Float insideProportion;

    private Float totalNet;

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

    public Date getGdate() {
        return gdate;
    }

    public void setGdate(String gdate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.gdate = simpleDateFormat.parse(gdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public Float getOrgProportion() {
        return orgProportion;
    }

    public void setOrgProportion(Float orgProportion) {
        this.orgProportion = orgProportion;
    }

    public Float getPersonalProportion() {
        return personalProportion;
    }

    public void setPersonalProportion(Float personalProportion) {
        this.personalProportion = personalProportion;
    }

    public Float getInsideProportion() {
        return insideProportion;
    }

    public void setInsideProportion(Float insideProportion) {
        this.insideProportion = insideProportion;
    }

    public Float getTotalNet() {
        return totalNet;
    }

    public void setTotalNet(Float totalNet) {
        this.totalNet = totalNet;
    }
}