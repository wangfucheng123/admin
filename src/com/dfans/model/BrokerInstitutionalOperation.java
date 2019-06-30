package com.dfans.model;

public class BrokerInstitutionalOperation {
    private Integer id;

    private Integer baseId;

    private Integer year;

    private String totalAssets;

    private String netAssets;

    private String netCapital;

    private String businessIncome;

    private String netProfit;

    private String brokerageIncome;

    private String assetsManageIncome;

    private String proprietaryIncome;

    private String bankIncome;

    private String securitiesMargin;

    private String sharesIncome;

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(String totalAssets) {
        this.totalAssets = totalAssets == null ? null : totalAssets.trim();
    }

    public String getNetAssets() {
        return netAssets;
    }

    public void setNetAssets(String netAssets) {
        this.netAssets = netAssets == null ? null : netAssets.trim();
    }

    public String getNetCapital() {
        return netCapital;
    }

    public void setNetCapital(String netCapital) {
        this.netCapital = netCapital == null ? null : netCapital.trim();
    }

    public String getBusinessIncome() {
        return businessIncome;
    }

    public void setBusinessIncome(String businessIncome) {
        this.businessIncome = businessIncome == null ? null : businessIncome.trim();
    }

    public String getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(String netProfit) {
        this.netProfit = netProfit == null ? null : netProfit.trim();
    }

    public String getBrokerageIncome() {
        return brokerageIncome;
    }

    public void setBrokerageIncome(String brokerageIncome) {
        this.brokerageIncome = brokerageIncome == null ? null : brokerageIncome.trim();
    }

    public String getAssetsManageIncome() {
        return assetsManageIncome;
    }

    public void setAssetsManageIncome(String assetsManageIncome) {
        this.assetsManageIncome = assetsManageIncome == null ? null : assetsManageIncome.trim();
    }

    public String getProprietaryIncome() {
        return proprietaryIncome;
    }

    public void setProprietaryIncome(String proprietaryIncome) {
        this.proprietaryIncome = proprietaryIncome == null ? null : proprietaryIncome.trim();
    }

    public String getBankIncome() {
        return bankIncome;
    }

    public void setBankIncome(String bankIncome) {
        this.bankIncome = bankIncome == null ? null : bankIncome.trim();
    }

    public String getSecuritiesMargin() {
        return securitiesMargin;
    }

    public void setSecuritiesMargin(String securitiesMargin) {
        this.securitiesMargin = securitiesMargin == null ? null : securitiesMargin.trim();
    }

    public String getSharesIncome() {
        return sharesIncome;
    }

    public void setSharesIncome(String sharesIncome) {
        this.sharesIncome = sharesIncome == null ? null : sharesIncome.trim();
    }
}