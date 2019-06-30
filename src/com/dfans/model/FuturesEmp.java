package com.dfans.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FuturesEmp {
    private Integer id;

    private Integer baseId;

    private String name;

    private Integer sex;

    private String cyzgh;

    private String cyzsh;

    private String rzbm;

    private Integer dicPosition;

    private Date cdate;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCyzgh() {
        return cyzgh;
    }

    public void setCyzgh(String cyzgh) {
        this.cyzgh = cyzgh == null ? null : cyzgh.trim();
    }

    public String getCyzsh() {
        return cyzsh;
    }

    public void setCyzsh(String cyzsh) {
        this.cyzsh = cyzsh == null ? null : cyzsh.trim();
    }

    public String getRzbm() {
        return rzbm;
    }

    public void setRzbm(String rzbm) {
        this.rzbm = rzbm == null ? null : rzbm.trim();
    }

    public Integer getDicPosition() {
        return dicPosition;
    }

    public void setDicPosition(Integer dicPosition) {
        this.dicPosition = dicPosition;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			this.cdate = simpleDateFormat.parse(cdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}