package com.dfans.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dfans.cache.Info;
import com.dfans.cache.Memory;
import com.dfans.utils.DateUtils;
import com.dfans.utils.SendEmail;
import com.dfans.utils.shortmsgutils.SingletonClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.emay.Example;
import cn.emay.eucp.inter.framework.dto.CustomSmsIdAndMobile;


@Controller 
@RequestMapping("/shortMsg")
public class MessageController {

	
	private static Gson gson = new GsonBuilder().create();

	
	@RequestMapping("getMsg")
	@ResponseBody
	public String getMsg(HttpServletRequest request) {
		Map ret=new HashMap();
		String phone=request.getParameter("phone");
		if(DateUtils.isPhone(phone))
		{
			Info info = Memory.CodeMap.get(phone);
			if(info==null)
			{
				ret=getRetMap(phone);
			}
			else
			{
				if(info.isCheckLastTimes())
				{
					ret=getRetMap(phone);
				}
				else
				{
					ret.put("status",1);
					ret.put("msg","请1分钟后在获取验证码！");
				}
			}
		}
		else
		{
			ret.put("status",0);
			ret.put("msg","请输入正确的手机号！");
		}
		return gson.toJson(ret);
	}
	
	
	
	private Map getRetMap(String phone)
	{
		Map ret=new HashMap();
		String code=""+(int) (Math.random()*9000+1000);
		int k=-1;
		try {
			k=SingletonClient.getClient().sendSMS(new String[] { phone }, "【数金】您的验证码为"+code, "",5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(k==0)
		{
			ret.put("status",1);
			ret.put("code",code);
			Memory.CodeMap.put(phone,new Info(code));
		}
		else
		{
			ret.put("status",0);
			ret.put("msg","系统忙！请重试");
		}
		return ret;
	}
	
	@RequestMapping("getEmailMsg")
	@ResponseBody
	public String getEmailMsg(String email) {
		Map ret=new HashMap();
		
		if(DateUtils.isEmail(email))
		{
			Info info = Memory.CodeMap.get(email);
			if(info==null)
			{
				ret=getEmailMap(email);
			}
			else
			{
				if(info.isCheckLastTimes())
				{
					ret=getEmailMap(email);
				}
				else
				{
					ret.put("status",1);
					ret.put("msg","请1分钟后在获取验证码！");
				}
			}
		}
		else
		{
			ret.put("status",0);
			ret.put("msg","请输入正确的邮箱！");
		}
		return gson.toJson(ret);
	}
	
	private Map getEmailMap(String email)
	{
		Map ret=new HashMap();
		String code=""+(int) (Math.random()*9000+1000);
		int k=-1;
		try {
			k=SendEmail.sendEmail(email, "【数金】您的验证码为"+code, "【数金】验证码邮件");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(k==0)
		{
			ret.put("status",1);
			ret.put("code",code);
			Memory.CodeMap.put(email,new Info(code));
		}
		else
		{
			ret.put("status",0);
			ret.put("msg","系统忙！请重试");
		}
		return ret;
	}
	
	
	@RequestMapping("getNoticeMsg")
	@ResponseBody
	public static String getNoticeMsg(String phone1,String msg) {
		Map ret=new HashMap();
		String phone[] = phone1.split(",");
	 
		//String msg=request.getParameter("msg");
		CustomSmsIdAndMobile[] csiam=new CustomSmsIdAndMobile[phone.length];
		for(int i=0;i<phone.length;i++)
		{
			csiam[i]=new CustomSmsIdAndMobile(""+(i+1),phone[i]);
			Example.setSingleSms(msg, null,null,phone[i]);
		}
		
		
		ret.put("status",1);
		ret.put("msg"," 成功！");
		
		return gson.toJson(ret);
	}
	
	
	public static void main(String args[])
	{
		int k=-1;
		String mobile = ""; //手机号
		String content = "内容";
		try {
			k = SingletonClient.getClient().sendSMS(new String[] { mobile }, content, "",5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(k);
	}
	
}
