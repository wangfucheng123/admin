package com.dfans.utils;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.api.push.model.notification.*;

import com.google.gson.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;

public class PushUtils {
    protected static final Logger LOG = LoggerFactory.getLogger(PushUtils.class);

    // demo App defined in resources/jpush-api.conf 
	private static final String appKey ="e63d62f6a3c6a2ac033a629a";
	private static final String masterSecret = "b03e5fb4de92a2216c0d0de2";

	public static void main(String[] args) {
		List<String> aliases = new ArrayList<String>();
		aliases.add("122");
		List<String> temp = new ArrayList<String>();
		temp.addAll(aliases.subList(0, aliases.size()));//可以先按下标截取，再添加
		String alertContent = "测试";
		String androidTitle = "测试";
		JsonObject jsonExtra = new JsonObject();
        jsonExtra.addProperty("id", 10);
        jsonExtra.addProperty("type", 1);
        
        sendPushAndroid_And_Ios(aliases, alertContent, androidTitle, jsonExtra);
      
        System.out.println(temp);
	}
	
	/**
	 * 官网文档https://docs.jiguang.cn/
	 * 详细见https://docs.jiguang.cn/jpush/server/3rd/java_sdk/
	 * @param aliases
	 * @param alertContent
	 * @param androidTitle
	 * @param jsonExtra
	 * @return  status 0:成功，1:失败
	 */
	public static Integer sendPushAndroid_And_Ios(List<String> aliases,String alertContent,String androidTitle,JsonObject jsonExtra) {
		int status = 1;
        ClientConfig config = ClientConfig.getInstance();
        // Setup the custom hostname
        config.setPushHostName("https://api.jpush.cn");

        JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, config);

        // For push, all you need do is to build PushPayload object.
        PushPayload payload = buildPushObject_android_and_ios(aliases, alertContent, androidTitle, jsonExtra);

        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);
            status = 0;
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            status = 1;
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
            status = 1;
        }
        return status ;
    }
	
	public static PushPayload buildPushObject_android_and_ios(List<String> aliases,String alertContent,String androidTitle,JsonObject jsonExtra) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(aliases))
                /**
                 * https://docs.jiguang.cn/jpush/server/push/rest_api_v3_push/#options
                 * 正式环境后台修改推送的环境参数apns_production为true
                 */
                .setOptions(Options.newBuilder().setApnsProduction(true).build())
                .setNotification(Notification.newBuilder()
                		.setAlert(alertContent)
                		.addPlatformNotification(AndroidNotification.newBuilder()
                				.setTitle(androidTitle)
                				.addExtra("jsonExtra",jsonExtra).build())
                		.addPlatformNotification(IosNotification.newBuilder()
                				.incrBadge(1)
                				.addExtra("jsonExtra",jsonExtra).build())
                		.build())
                .build();
    }

}