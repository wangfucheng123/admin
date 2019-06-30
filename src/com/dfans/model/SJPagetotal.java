package com.dfans.model;

public class SJPagetotal {
	private Integer id;

	private String cDate;

	private String userid;

	private String ip;

	private String count;

	private String xAxis;

	private String source;
	
	private String pid;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid == null ? null : userid.trim();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip == null ? null : ip.trim();
	}
	
	public String getCount() {
		return count;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}
	
	public void setCount(String count) {
		this.count = count == null ? null : count.trim();
	}
	
	public String getXAxis() {
		return xAxis;
	}

	public void setXAxis(String xAxis) {
		this.xAxis = xAxis == null ? null : xAxis.trim();
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source == null ? null : source.trim();
	}
}