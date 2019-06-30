package com.dfans.model;

public class TCity {
    private Integer cityid;

    private String cityname;

    private Integer proid;

    private Integer citysort;

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname == null ? null : cityname.trim();
    }

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }

    public Integer getCitysort() {
        return citysort;
    }

    public void setCitysort(Integer citysort) {
        this.citysort = citysort;
    }
}