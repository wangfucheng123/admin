package com.wlw.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DateUtil {
	
	/*
	 * 获取本月已经过去了几分之几
	 */
	public static float getDayOfMonth() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		int day = c.get(Calendar.DAY_OF_MONTH);		
		int last = c.getActualMaximum(Calendar.DAY_OF_MONTH); 
		return (day-1)/1f/last;
	}
	
	public static float getCurrentPriceByMonth(float price, String pid) {
		if (isNotMonthPack(pid)) {
			return price;
		}
		int iprice = (int)(price * 100 * (1-getDayOfMonth()));
		return iprice/100f;
	}
	
	public static String getCurrentTrafficByMonth(long kb) {		
		long ikb = (int)(kb * 100 * (1-getDayOfMonth()));
		return getUnitName(ikb/100);
	}

	public static String getUnitName(long kb) {
		if (kb == 1024) {
			return "1G";
		}
		if(kb / 1024  >= 1 ) {
			return ((kb * 100 / 1024)/100f) + "G";
		}
		return "" + kb + "M";
	}

	public static boolean isNotMonthPack(String pid) {
		if (Integer.parseInt(pid) < 2052 || Integer.parseInt(pid) > 2075) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String args[]) {		
		System.out.println(getCurrentTrafficByMonth(2048));
	}

}
