package com.dfans.cache;

import java.util.Date;

public class Info {
	public String code;
	
	public long lastDate;
	
	public Info(String code) {
		// TODO Auto-generated constructor stub
		this.code=code;
		this.lastDate=new Date().getTime();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getLastDate() {
		return lastDate;
	}

	public void setLastDate(long lastDate) {
		this.lastDate = lastDate;
	}
	
	
	public boolean isCheckLastTimes() {
		return new Date().getTime() - this.getLastDate() < 60000 ? false : true;
	}
}
