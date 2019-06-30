package com.dfans.utils;

import java.util.List;
import java.util.Map;

public class TreeNodes {
	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	public List<TreeNodes> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNodes> children) {
		this.children = children;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public String getPid() {
		return pId;
	}

	public void setPid(String pid) {
		this.pId = pid;
	}

	private String pId; 
	private String state = "open";
	private boolean open;
	private boolean checked;
	
	private List<TreeNodes> children;
	private Map<String, String> attributes;
}
