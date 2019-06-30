package com.dfans.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfans.dao.UserMapper;
import com.dfans.service.PushService;
import com.dfans.utils.DateUtils;
import com.dfans.utils.PushUtils;
import com.google.gson.JsonObject;

@Service("pushService")
public class PushServiceImpl implements PushService {

	@Autowired
	private UserMapper userDao;
	
	public Integer sendSystemInfo(List<String> aliases,String alertContent, JsonObject jsonExtra) {
		// TODO Auto-generated method stub
		aliases = userDao.selectAllUserId(null);
		int number = 1000;
		int size = (int) Math.ceil(new BigDecimal(aliases.size()).divide(new BigDecimal(number)).doubleValue());
		List<String> temp = null;
		String androidTitle = "系统消息";
		jsonExtra.addProperty("type", "1");  //消息类型
		jsonExtra.addProperty("sendTime", DateUtils.getTime());  //发送时间
		int status = 1;
		for (int i = 0; i < size; i++) {
			if(i == size-1){
				temp = new ArrayList<String>();
				temp.addAll(aliases.subList(i*number, aliases.size()));//可以先按下标截取，再添加
			}else{
				temp = new ArrayList<String>();
				temp.addAll(aliases.subList(i*number, (i+1)*number));//可以先按下标截取，再添加
			}
			status = PushUtils.sendPushAndroid_And_Ios(temp, alertContent, androidTitle, jsonExtra);
		}
		
		return status;
	}
	
	public Integer sendUserOrderInfo(List<String> aliases,String alertContent, JsonObject jsonExtra) {
		// TODO Auto-generated method stub
		String androidTitle = "数据定制订单消息";
		jsonExtra.addProperty("type", "2");  //消息类型
		jsonExtra.addProperty("sendTime", DateUtils.getTime());  //发送时间
		int status = PushUtils.sendPushAndroid_And_Ios(aliases, alertContent, androidTitle, jsonExtra);
		
		return status;
	}

}
