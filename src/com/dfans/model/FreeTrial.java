package com.dfans.model;

import java.util.Date;

public class FreeTrial {
    private Integer id;

    private Integer userid;

    private Integer treeid;
    
    private Date sdate;

    private Date edate;

    private String isTrail;
    
    public String getIsTrail() {
		return isTrail;
	}

	public Integer getTreeid() {
		return treeid;
	}

	public void setTreeid(Integer treeid) {
		this.treeid = treeid;
	}

	public void setIsTrail(String isTrail) {
		this.isTrail = isTrail;
	}
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }
}