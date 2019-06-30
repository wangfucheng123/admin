package com.dfans.model;

import java.util.Date;
import java.util.List;

public class RightTree {
	private Integer id;
	private String name;
	private Integer parentId;
	private Float price;
	private String code;
	private Integer type;
	private String flag;
	private String title;
	private String edate;
	 private String startDate;
	private String img;
	private String isTrail; 
	public String getImg() {
		return img;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}
	public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
	public String getIsTrail() {
		return isTrail;
	}

	public void setIsTrail(String isTrail) {
		this.isTrail = isTrail;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private String content;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	private String isFree;
	private String freeLimit;

	public String getFreeLimit() {
		return freeLimit;
	}

	public void setFreeLimit(String freeLimit) {
		this.freeLimit = freeLimit;
	}

	public String getIsFree() {
		return isFree;
	}

	public List<SJChannelPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<SJChannelPrice> prices) {
		this.prices = prices;
	}

	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}

	private List<SJChannelPrice> prices;

}