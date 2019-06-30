package com.dfans.utils;

import java.io.Serializable;

public class HttpClientEntity implements Serializable {
	private static HttpClientEntity httpClientEntityInstance = new HttpClientEntity();;
	private HttpClientEntity(){}
	public static HttpClientEntity getInstance(){
		return httpClientEntityInstance;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1962667690681955964L;
	private String url = "";
	private String code = "";
	private String data = "";
	private String callback = "";
	private String contentType = "";
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
