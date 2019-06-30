package com.wlw.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class DXUtils
{

	private static String dx_url="http://api.ct10649.com:9001/m2m_ec/query.do";
	
	private static String dx_c_url="http://api.ct10649.com:9001/m2m_ec/app/serviceAccept.do";
	
	//private static String user="YCSXtt001";
	
	//private static String password="dever!1";
	
	//private static String[] key={"6d8","TXH","4AL"};
	
	
	private static String user="sblyx123";//"bjdfscjs01";
	
	private static String password="sblyx123A";//"m2m@zjgdx";
	
	private static String[] key={"E74","Fk3","5EW"};//{"X46","rUz","9wd"};
	
	public static Map getJHCard(String card) throws HttpException, IOException//活卡激活
	{
		Map ret=new HashMap();
		String passWord=DesUtils.strEnc(password,key[0],key[1],key[2]);
		String[] temp1Sign={card,user,password,"requestServActive"};
		String temp2Sign=DesUtils.naturalOrdering(temp1Sign);
		String sign=DesUtils.strEnc(temp2Sign,key[0],key[1],key[2]);
		NameValuePair[] param =
		{
			new NameValuePair("method","requestServActive"),
			new NameValuePair("access_number",card),
			new NameValuePair("user_id",user),
			new NameValuePair("passWord",passWord),
			new NameValuePair("sign",sign),
		};
		String staStr=getReturn(param,dx_url);
		//String staStr="{\"RESULT\":\"0\",\"SMSG\":\"/*成功*/\",\"GROUP_TRANSACTIONID\":\"1000000252201704153417534653\"}";
		Map temp= JsonUtil.jsonToMap(staStr);
		System.out.println(temp.get("RESULT"));
		if(temp.get("RESULT").toString().equals("0"))
		{
			ret.put("status","1");
		}
		else
		{
			ret.put("status","0");
			ret.put("msg",temp.get("SMSG"));
		}
		return ret;
	}
	
	
	public static Map getCardStatus(String card) throws HttpException, IOException
	{
		Map ret=new HashMap();
		String passWord=DesUtils.strEnc(password,key[0],key[1],key[2]);
		String[] temp1Sign={card,user,password,"getTelephone"};
		String temp2Sign=DesUtils.naturalOrdering(temp1Sign);
		String sign=DesUtils.strEnc(temp2Sign,key[0],key[1],key[2]);
		NameValuePair[] param =
		{
			new NameValuePair("method","getTelephone"),
			new NameValuePair("iccid",card),
			new NameValuePair("user_id",user),
			new NameValuePair("passWord",passWord),
			new NameValuePair("sign",sign),
		};
		String staStr=getReturn(param,dx_url);
		System.out.println(staStr);
		
		return ret;
	}
	
	
	public static Map getUnsubscribe(String card,String product) throws HttpException, IOException
	{
		Map ret=new HashMap();
		String passWord=DesUtils.strEnc(password,key[0],key[1],key[2]);
		String[] temp1Sign={card,user,password,product,"unsubScribe"};
		String temp2Sign=DesUtils.naturalOrdering(temp1Sign);
		String sign=DesUtils.strEnc(temp2Sign,key[0],key[1],key[2]);
		NameValuePair[] param =
		{
			new NameValuePair("method","unsubScribe"),
			new NameValuePair("iccid",card),
			new NameValuePair("user_id",user),
			new NameValuePair("passWord",passWord),
			new NameValuePair("sign",sign),
			new NameValuePair("flowValue",product),
		};
		String staStr=getReturn(param,dx_c_url);
		System.out.println(staStr);
		//String tt="{\"checkout\":\"成功接收消息\",\"GROUP_TRANSACTIONID\":\"1000000252201704223709685689\",\"messg\":\"\",\"info\":\"success\"}";
		JSONObject json_test = JSONObject.fromObject(staStr);
		//System.out.println(json_test.get("info"));
		if(json_test.get("info").equals("success"))
		{
			ret.put("status","1");
			ret.put("msg",json_test.get("GROUP_TRANSACTIONID"));
		}
		else
		{
			ret.put("status","0");
			ret.put("msg",json_test.get("messg"));
		}
		return ret;
	}
	
	public static Map getRechange(String card,String product) throws HttpException, IOException
	{
		Map ret=new HashMap();
		String passWord=DesUtils.strEnc(password,key[0],key[1],key[2]);
		String[] temp1Sign={card,user,password,product,"orderFlow"};
		String temp2Sign=DesUtils.naturalOrdering(temp1Sign);
		String sign=DesUtils.strEnc(temp2Sign,key[0],key[1],key[2]);
		NameValuePair[] param =
		{
			new NameValuePair("method","orderFlow"),
			new NameValuePair("iccid",card),
			new NameValuePair("user_id",user),
			new NameValuePair("passWord",passWord),
			new NameValuePair("sign",sign),
			new NameValuePair("flowValue",product),
		};
		String staStr=getReturn(param,dx_c_url);
		//System.out.println(staStr);
		//String tt="{\"checkout\":\"成功接收消息\",\"GROUP_TRANSACTIONID\":\"1000000252201704223709685689\",\"messg\":\"\",\"info\":\"success\"}";
		JSONObject json_test = JSONObject.fromObject(staStr);
		//System.out.println(json_test.get("info"));
		if(json_test.get("info").equals("success"))
		{
			ret.put("status","1");
			ret.put("msg",json_test.get("GROUP_TRANSACTIONID"));
		}
		else
		{
			ret.put("status","0");
			ret.put("msg",json_test.get("messg"));
		}
		return ret;
	}
	
	public static String getCardMsg(String card,String month) throws HttpException, IOException, DocumentException
	{
		//Map ret=new HashMap();
		String passWord=DesUtils.strEnc(password,key[0],key[1],key[2]);
		String[] temp1Sign={card,user,password,"queryPakage"};
		String temp2Sign=DesUtils.naturalOrdering(temp1Sign);
		String sign=DesUtils.strEnc(temp2Sign,key[0],key[1],key[2]);
		NameValuePair[] param =
		{
			new NameValuePair("method","queryPakage"),
			new NameValuePair("iccid",card),
			new NameValuePair("user_id",user),
			new NameValuePair("passWord",passWord),
			new NameValuePair("sign",sign),
			new NameValuePair("monthDate",month),
		};
		String staStr=getReturn(param,dx_url);
		System.out.println(staStr);
//		Document document   = DocumentHelper.parseText(staStr);
//		Element root = document.getRootElement();
//		Iterator it = root.elementIterator();
//        while (it.hasNext()) {
//        	Element element = (Element) it.next();
//        	System.out.println("title: " + element.elementText("RspCode"));
//        }
		
		return staStr;
	}
	
	
	public static String getCardDetail(String card) throws HttpException, IOException, DocumentException
	{
		//Map ret=new HashMap();
		String passWord=DesUtils.strEnc(password,key[0],key[1],key[2]);
		String[] temp1Sign={card,user,password,"queryTraffic"};
		String temp2Sign=DesUtils.naturalOrdering(temp1Sign);
		String sign=DesUtils.strEnc(temp2Sign,key[0],key[1],key[2]);
		NameValuePair[] param =
		{
			new NameValuePair("method","queryTraffic"),
			new NameValuePair("iccid",card),
			new NameValuePair("user_id",user),
			new NameValuePair("passWord",passWord),
			new NameValuePair("sign",sign),
			new NameValuePair("needDtl","1"),
		};
		String staStr=getReturn(param,dx_url);
		System.out.println(staStr);
//		Document document   = DocumentHelper.parseText(staStr);
//		Element root = document.getRootElement();
//		Iterator it = root.elementIterator();
//        while (it.hasNext()) {
//        	Element element = (Element) it.next();
//        	System.out.println("title: " + element.elementText("RspCode"));
//        }
		
		return staStr;
	}
	
	
	public static String getProductStatus(String card) throws HttpException, IOException, DocumentException
	{
		String ret="";
		String passWord=DesUtils.strEnc(password,key[0],key[1],key[2]);
		String[] temp1Sign={card,user,password,"prodInstQuery"};
		String temp2Sign=DesUtils.naturalOrdering(temp1Sign);
		String sign=DesUtils.strEnc(temp2Sign,key[0],key[1],key[2]);
		NameValuePair[] param =
		{
			new NameValuePair("method","prodInstQuery"),
			new NameValuePair("access_number",card),
			new NameValuePair("user_id",user),
			new NameValuePair("passWord",passWord),
			new NameValuePair("sign",sign),
		};
		String staStr=getReturn(param,dx_url);
		System.out.println(staStr);
//		
		Map<String, Object> map = XMLUtils.Dom2Map(staStr);
		List<Map> l=(List<Map>) (((Map) (((Map) map.get("result")).get("prodInfos"))).get("funProdInfos"));
		for(int i=0;i<l.size();i++)
		{
			//System.out.println(l.get(i).get("productName"));
			if(l.get(i).get("productName").toString().equals("达量断网"))
			{
				List<Map> l1=(List<Map>) (l.get(i).get("attrInfos"));
				for(int k=0;k<l1.size();k++)
				{
					if(l1.get(k).get("attrName").toString().equals("是否已达量断网"))
					{
						ret=l1.get(k).get("attrValue").toString();
					}
				}
			}
		}
		return ret;
	}
	
	
	public static boolean getRealNameCheck(String card,String id_num) throws HttpException, IOException, DocumentException//实名认证
	{
		boolean flag=false;
		String passWord=DesUtils.strEnc(password,key[0],key[1],key[2]);
		String[] temp1Sign={card,id_num,user,password,"realNameRecIot"};
		String temp2Sign=DesUtils.naturalOrdering(temp1Sign);
		String sign=DesUtils.strEnc(temp2Sign,key[0],key[1],key[2]);
		String certNumber=DesUtils.strEnc(id_num,key[0],key[1],key[2]);
		NameValuePair[] param =
		{
			new NameValuePair("method","realNameRecIot"),
			new NameValuePair("access_number",card),
			new NameValuePair("user_id",user),
			new NameValuePair("certNumber",certNumber),
			new NameValuePair("passWord",passWord),
			new NameValuePair("sign",sign),
		};
		String staStr="<SvcCont><Response><RspType>0</RspType><RspCode>0000</RspCode><RspDesc>成功接收消息</RspDesc></Response><GROUP_TRANSACTIONID>1000000252201704153418050608</GROUP_TRANSACTIONID></SvcCont>";//getReturn(param);
		System.out.println(staStr);
		//<SvcCont><Response><RspType>0</RspType><RspCode>0000</RspCode><RspDesc>成功接收消息</RspDesc></Response><GROUP_TRANSACTIONID>1000000252201704153418050608</GROUP_TRANSACTIONID></SvcCont>
		//<SvcCont><Response><RspCode>9000</RspCode><RspDesc>【实名制信息补录】::接口入参转换异常</RspDesc></Response><GROUP_TRANSACTIONID></GROUP_TRANSACTIONID></SvcCont>
		Document document   = DocumentHelper.parseText(staStr);
		Element root = document.getRootElement();
        
        System.out.println("Root: " + root.getName());
        // root.elements("hello");
        //List<Element> childList2 = root.elements("Response");
        //System.out.println("hello child: " + childList2.size());
        Iterator it = root.elementIterator();
        while (it.hasNext()) {
        	Element element = (Element) it.next();
        	System.out.println("title: " + element.elementText("RspCode"));
        }
		return flag;
	}
	
	public static String getReturn(NameValuePair[] param,String url) throws HttpException, IOException
	{
		String ret="";
		StringBuilder sb = new StringBuilder();   
        InputStream ins = null; 
		HttpClient client = new HttpClient();
		PostMethod method=new PostMethod(url);
		if(param!=null)
		{
			method.setRequestBody(param);
		}
        int statusCode = client.executeMethod(method);
        ins = method.getResponseBodyAsStream();   
        byte[] b = new byte[1024];   
        int r_len = 0;   
        while ((r_len = ins.read(b)) > 0) 
        {   
            sb.append(new String(b, 0, r_len, method.getResponseCharSet()));   
        }
        ret=sb.toString();
		return ret;
	}
	
	
	
	
	public static Map getSingleCardCut(String card,String type) throws HttpException, IOException
	{
		Map ret=new HashMap();
		String passWord=DesUtils.strEnc(password,key[0],key[1],key[2]);
		String[] temp1Sign={card,user,password,type,"singleCutNet"};
		String temp2Sign=DesUtils.naturalOrdering(temp1Sign);
		String sign=DesUtils.strEnc(temp2Sign,key[0],key[1],key[2]);
		NameValuePair[] param =
		{
			new NameValuePair("method","singleCutNet"),
			new NameValuePair("access_number",card),
			new NameValuePair("user_id",user),
			new NameValuePair("passWord",passWord),
			new NameValuePair("sign",sign),
			new NameValuePair("action",type),
		};
		String staStr=getReturn(param,dx_url);
		System.out.println(staStr);
		//String tt="{\"checkout\":\"成功接收消息\",\"GROUP_TRANSACTIONID\":\"1000000252201704223709685689\",\"messg\":\"\",\"info\":\"success\"}";
		//JSONObject json_test = JSONObject.fromObject(staStr);
		//System.out.println(json_test.get("info"));
		//if(json_test.get("info").equals("success"))
		//{
			//ret.put("status","1");
		//}
		//else
		//{
			//ret.put("status","0");
			//ret.put("msg",json_test.get("messg"));
		//}
		return ret;
	}
	
	
	
	
	
	
	public static void main(String args[])
	{
		try {
			double d=10;
			for(int i=0;i<100;i++)
			{
				d=d*10;
			}
			java.text.DecimalFormat df = new java.text.DecimalFormat("########.00");
			//System.out.println(df.format(d));
			//getCardStatus("8986031644202829635");
			//getJHCard("8986031644202829635");
			//{"checkout":"成功接收消息","GROUP_TRANSACTIONID":"1000000252201704223709685689","messg":"","info":"success"}
			//getCardMsg("8986031644202829635","20170401");
			//getCardDetail("8986031746202292501");
			//getProductStatus("1064937604604");
			//getUnsubscribe("8986031644202829635","2080");
			//getRechange("8986031644202829635","2060");
			//getSingleCardCut("1064944608104","ADD");
			//getSingleCardCut("1064937604605","DEL");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}
