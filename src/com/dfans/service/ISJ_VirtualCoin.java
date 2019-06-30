package com.dfans.service;

import javax.servlet.http.HttpServletRequest;

public interface ISJ_VirtualCoin {
	public String getOrderId(HttpServletRequest req);
	public int updateChannelLimit(String orderId);
}
