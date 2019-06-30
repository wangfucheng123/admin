package com.wlw.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;


public class FlowRechargeUtils {
	public static String getRecharge(String product_code,String phone_no,String partner_order_id,String partner_user_id)
	{
		StringBuilder sb = new StringBuilder();   
        InputStream ins = null; 
        PostMethod method=null;
		try
		{
			String key="jiaxuewebpro";	
			String callback_url="http://partner.cnrllb.com/webpro/frt/front/order/newNotify";
			String sync_callback_url="http://partner.cnrllb.com/webpro/frt/front/order/newNotify";
			Map<String,String> params=new HashMap<String, String>();
			params.put("key",key);
			params.put("product_code",product_code);
			params.put("phone_no",phone_no);
			params.put("partner_order_id",partner_order_id);
			params.put("partner_user_id",partner_user_id);
			params.put("callback_url",callback_url);
			params.put("sync_callback_url",sync_callback_url);
			String sign="";//SignUtils.sign(params, "B272AE9C845F635F0F6B39BA0782A190","UTF-8");
			HttpClient client = new HttpClient();
			method = new PostMethod("http://app.chongb.cn/jiaxuetraffic/flow/recharge.do");
			NameValuePair[] param =
		    {   
				new NameValuePair("key",key),
				new NameValuePair("product_code",product_code),
		        new NameValuePair("phone_no", phone_no),
		        new NameValuePair("callback_url", callback_url),
		        new NameValuePair("sync_callback_url",sync_callback_url),
		        new NameValuePair("partner_order_id",partner_order_id),
		        new NameValuePair("partner_user_id",partner_user_id),
		        new NameValuePair("sign",sign),
		     };
		     method.setRequestBody(param); 
		     int statusCode = client.executeMethod(method);
		     if(statusCode==200)
		     {
			     ins = method.getResponseBodyAsStream(); 
		         byte[] b = new byte[1024];   
		         int r_len = 0;   
		         while ((r_len = ins.read(b)) > 0) {   
		             sb.append(new String(b, 0, r_len, method.getResponseCharSet()));   
		         }   
		     }
		     else
		     {
		    	 sb.append("#"+statusCode);
		     }
		} catch (Exception e) {
			System.out.println(e);
			//throw new RuntimeException(e);
			return "#9999";
		}
		return sb.toString();
	}

}
