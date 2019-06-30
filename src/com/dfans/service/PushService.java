package com.dfans.service;

import java.util.List;

import com.google.gson.JsonObject;

public interface PushService {
	
	public Integer sendSystemInfo(List<String> aliases,String alertContent,JsonObject jsonExtra);
	
	public Integer sendUserOrderInfo(List<String> aliases,String alertContent,JsonObject jsonExtra);
}
