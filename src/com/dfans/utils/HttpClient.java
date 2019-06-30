package com.dfans.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;  

public class HttpClient {  
	private static HttpClient httpClientInstance = new HttpClient();;
	private HttpClient(){}
	public static HttpClient getInstance(){
		return httpClientInstance;
	}
	private DefaultHttpClient httpClient = null;
	private HttpResponse res = null;
	public String httpGetUrl(HttpClientEntity httpEntity) throws Exception{
		String result = "";
		try {
			String url = httpEntity.getUrl();
			if(!StringUtil.isEmpty(httpEntity.getCode())){
				url += httpEntity.getCode();
			}
			if(!StringUtil.isEmpty(httpEntity.getData())){
				url += "?"+httpEntity.getData();
			}
			HttpGet httpGet = new HttpGet(url);
			httpClient = GetHttpClinet.getHttpClient();
			res = httpClient.execute(httpGet);
			result = responseResult(res);
		} catch(Exception e){
			e.printStackTrace(); 
	  		throw e;
	  	}
		return result;
	}
	
	public String httpPostUrl(HttpClientEntity httpEntity) throws Exception{
		String result = "";
		try {
			HttpPost httpPost = new HttpPost(httpEntity.getUrl()+httpEntity.getCode());
			StringEntity entity = new StringEntity(httpEntity.getData(), "utf-8");// 解决中文乱码
			entity.setContentEncoding("UTF-8");
			
			if(!StringUtil.isEmpty(httpEntity.getContentType())){
				entity.setContentType(httpEntity.getContentType());
			}else{
				entity.setContentType("application/x-www-form-urlencoded");
			}
			httpPost.setEntity(entity);
			
			httpClient = GetHttpClinet.getHttpClient();
			res = httpClient.execute(httpPost);
			result = responseResult(res);
		} catch(Exception e){
			e.printStackTrace(); 
			throw e;
		}
		return result;
	}
	
	public  String responseResult(HttpResponse res) throws Exception{
		String jsonResult = "";
		try {
			jsonResult = EntityUtils.toString(res.getEntity());
			//JSONObject result = JSONObject.fromObject(jsonResult);
			System.out.println(jsonResult);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); 
	  		throw e;
		}
		return jsonResult;
	}
	
	public static void main(String[] args) throws Exception {    	
      
    }  
}
