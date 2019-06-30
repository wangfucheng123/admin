package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrivateFundScaleTotal {
    private Integer id;

    private Date endDate;

    private Integer glrsl;

    private Integer smjjsl;

    private Float rjgm;

    private Float sjgm;

    private Float cyry;

    private Integer glgm1;

    private Integer glgm2;

    private Integer glgm3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        try {
			this.endDate = simpleDateFormat.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

    public Integer getGlrsl() {
        return glrsl;
    }

    public void setGlrsl(Integer glrsl) {
        this.glrsl = glrsl;
    }

    public Integer getSmjjsl() {
        return smjjsl;
    }

    public void setSmjjsl(Integer smjjsl) {
        this.smjjsl = smjjsl;
    }

    public Float getRjgm() {
        return rjgm;
    }

    public void setRjgm(Float rjgm) {
        this.rjgm = rjgm;
    }

    public Float getSjgm() {
        return sjgm;
    }

    public void setSjgm(Float sjgm) {
        this.sjgm = sjgm;
    }

    public Float getCyry() {
        return cyry;
    }

    public void setCyry(Float cyry) {
        this.cyry = cyry;
    }

    public Integer getGlgm1() {
        return glgm1;
    }

    public void setGlgm1(Integer glgm1) {
        this.glgm1 = glgm1;
    }

    public Integer getGlgm2() {
        return glgm2;
    }

    public void setGlgm2(Integer glgm2) {
        this.glgm2 = glgm2;
    }

    public Integer getGlgm3() {
        return glgm3;
    }

    public void setGlgm3(Integer glgm3) {
        this.glgm3 = glgm3;
    }
}