package com.dfans.model;

public class FuturesExecWithBLOBs extends FuturesExec {
    private String rzjl;

    private String remark;

    public String getRzjl() {
        return rzjl;
    }

    public void setRzjl(String rzjl) {
        this.rzjl = rzjl == null ? null : rzjl.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}