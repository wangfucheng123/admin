package com.dfans.model;

public class AssetSecuritiesScale {
    private Integer id;

    private Integer year;

    private Integer quarter;

    private String type;

    private Integer productNum;

    private Float assetScale;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Float getAssetScale() {
        return assetScale;
    }

    public void setAssetScale(Float assetScale) {
        this.assetScale = assetScale;
    }
}