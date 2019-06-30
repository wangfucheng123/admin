package com.dfans.model;

import java.util.Date;

public class CscFunction {
    private Integer functionId;

    private String functionName;

    private Integer functionStatus;

    private Date ctime;

    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName == null ? null : functionName.trim();
    }

    public Integer getFunctionStatus() {
        return functionStatus;
    }

    public void setFunctionStatus(Integer functionStatus) {
        this.functionStatus = functionStatus;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}