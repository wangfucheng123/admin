package com.wlw.utils;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

	/**     * 将json格式转换成map对象     * @param jsonStr     * @return     */    
	public static Map<?,?> jsonToMap(String jsonStr)
	{
		Gson gson = new GsonBuilder().create();
		Map<?,?> objMap=null;
		if(gson!=null)
		{
			java.lang.reflect.Type type=new com.google.gson.reflect.TypeToken<Map<?,?>>(){}.getType();
			objMap=gson.fromJson(jsonStr, type);
		}
		return objMap;
	}
	public static boolean isSucess(String code)
	{
		if(code.equals("000000") || code.equals("00000") || code.equals("0000"))
		{
			return true;
		}
		return false;
	}

}
