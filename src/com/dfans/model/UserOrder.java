package com.dfans.model;

import java.util.Date;
import java.util.List;

public class UserOrder {
	private String id; 
	private String priceid;
	private String title; 
	private String name;
	private String price;
	private String phone;
	private String fromwhere;
	private String email;
	private String remark;
	private String mtype; 
	private String qq;
	private Integer userId;
	private String content;
	private List<SJConsumerData> pageList;
	private List<SJConsumerData> dataList;
	public String getPriceid() {
		return priceid;
	}

	public void setPriceid(String priceid) {
		this.priceid = priceid;
	}
	public String getFromwhere() {
		return fromwhere;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<SJConsumerData> getPageList() {
		return pageList;
	}

	public void setPageList(List<SJConsumerData> pageList) {
		this.pageList = pageList;
	}

	public List<SJConsumerData> getDataList() {
		return dataList;
	}

	public void setDataList(List<SJConsumerData> dataList) {
		this.dataList = dataList;
	}

	public String getRemark() {
		return remark;
	}

	public void setFromwhere(String fromwhere) {
		this.fromwhere = fromwhere;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getrRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq == null ? null : qq.trim();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	private Integer statusid;
	private String status;
	private String stat;
	private String descb;
	private Date cdate;
	public String getDescb() {
		return descb;
	}
	public void setDescb(String descb) {
		this.descb = descb == null ? null : descb.trim();
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat == null ? null : stat.trim();
	}
	public String getStatus() {
		return status;
	}

	private String endDate;

	private String startDate;

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getStatusid() {
		return statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

}