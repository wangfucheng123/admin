package com.dfans.model;

public class AssetSpecialScale {
    private Integer id;

    private Integer year;

    private Integer quarter;

    private String productType;

    private Integer productNum;

    private Float assetScale;

    private Integer comType;

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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
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

    public Integer getComType() {
        return comType;
    }

    public void setComType(Integer comType) {
        this.comType = comType;
    }
}