package com.dfans.model;

public class BankMainQuota {
    private Integer id;

    private Integer year;

    private Integer quarter;

    private String project;

    private Float quota;
    
    private Float quota1;
    
    private Float quota2;
    
    private Float quota3;
    
    private Float quota4;

    private Integer type;

    public Float getQuota1() {
		return quota1;
	}

	public void setQuota1(Float quota1) {
		this.quota1 = quota1;
	}

	public Float getQuota2() {
		return quota2;
	}

	public void setQuota2(Float quota2) {
		this.quota2 = quota2;
	}

	public Float getQuota3() {
		return quota3;
	}

	public void setQuota3(Float quota3) {
		this.quota3 = quota3;
	}

	public Float getQuota4() {
		return quota4;
	}

	public void setQuota4(Float quota4) {
		this.quota4 = quota4;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project == null ? null : project.trim();
    }

    public Float getQuota() {
        return quota;
    }

    public void setQuota(Float quota) {
        this.quota = quota;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}