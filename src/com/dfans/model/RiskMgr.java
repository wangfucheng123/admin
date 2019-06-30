package com.dfans.model;


/*风控管理实体*/
public class RiskMgr {
    private Integer id;

    private String account;

    private Integer singleLimit;

    private Integer totalLimit;

    private Integer totalMoney;

    private Integer trustSpeed;

    private Integer cancelCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Integer getSingleLimit() {
        return singleLimit;
    }

    public void setSingleLimit(Integer singleLimit) {
        this.singleLimit = singleLimit;
    }

    public Integer getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(Integer totalLimit) {
        this.totalLimit = totalLimit;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getTrustSpeed() {
        return trustSpeed;
    }

    public void setTrustSpeed(Integer trustSpeed) {
        this.trustSpeed = trustSpeed;
    }

    public Integer getCancelCount() {
        return cancelCount;
    }

    public void setCancelCount(Integer cancelCount) {
        this.cancelCount = cancelCount;
    }
}