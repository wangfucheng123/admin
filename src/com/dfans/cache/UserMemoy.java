package com.dfans.cache;

import java.util.HashMap;
import java.util.Map;

public class UserMemoy {
	
	public static Map<String,Map> userMap;

	
	public void init()
	{
		userMap=new HashMap<String,Map>();
	}
}
