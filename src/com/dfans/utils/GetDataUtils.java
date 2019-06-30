package com.dfans.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.dfans.dao.SearchMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class GetDataUtils 
{

	private HttpClient httpClient = HttpClient.getInstance();
	
	private HttpClientEntity httpClientEntity = HttpClientEntity.getInstance();
	
	public static String sendGet(String url) 
	{
	   BufferedReader in = null;
	   String result = "";
	   try {
	       URL realUrl = new URL(url);
	       HttpURLConnection conn = null;
	       conn = (HttpURLConnection) realUrl.openConnection();
	       conn.setDoOutput(true);
	       conn.setDoInput(true);
	       conn.setRequestMethod("GET"); 
	       
	       in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
	       String line;
	       while ((line = in.readLine()) != null) {
	           result += line;
	       }
	   } catch (Exception e) {
	       System.out.println("发送 Get 请求出现异常！"+e);
	       e.printStackTrace();
	   }
	   //使用finally块来关闭输出流、输入流
	   finally{
	       try{
	           if(in!=null){
	               in.close();
	           }
	       }
	       catch(IOException ex){
	           ex.printStackTrace();
	       }
	   }
	   return result;
	}    
	
	
	
	 public long getTimestamp(){
			return new Date().getTime();
		}
	
	public JSONObject getQuoteSHStockDetails(String id,SearchMapper searchDao) throws Exception{
		JSONObject jsonParam = new JSONObject();
		if(id.substring(0,1).equals("6"))
		{
			id=id+"1";
		}
		else
		{
			id=id+"2";
		}
		//http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C.BK04591&sty=FDCS&st=C&sr=-1&p=1&ps=5&lvl=&cb=&js=var%20jspy=[(x)];&token=beb0a0047196124721f56b0f0ff5a27c&v=0.08788383667490474
		//http://nuff.eastmoney.com/EM_Finance2015TradeInterface/JS.ashx?id=6031601&token=beb0a0047196124721f56b0f0ff5a27c&cb=callback017465270942223687&callback=callback017465270942223687&_=1476681854493
		//http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&sty=FDTH&st=z&js=((x))&token=beb0a0047196124721f56b0f0ff5a27c&cmd=018185
		//http://hqdigi2.eastmoney.com/EM_Quote2010NumericApplication/Index.aspx?reference=rtj&type=z&jsName=quote_hs&ids=6031601&dt=1476064044836
		String stockDetailsTemp1 = "";
		String stockDetailsTemp2 = "";
		List<Object> stockDetailsTemp3 = new ArrayList<Object>();
		try {
			/**
			 * 沪详情
			 */
			JSONArray stockDetailsArray = new JSONArray();
			JSONArray stockFinanceDetailsArray = new JSONArray();
//			JSONArray stockNoticeDetailsArray = new JSONArray();
//			JSONArray stockNewsDetailsArray = new JSONArray();
//			JSONArray stockAnalyseReportDetailsArray = new JSONArray();
			//if(!StringUtil.isEmpty(pageData.get("id").toString())){
				
				httpClientEntity.setUrl("http://hqdigi2.eastmoney.com/EM_Quote2010NumericApplication/Index.aspx");
				httpClientEntity.setData("reference=rtj&type=z&jsName=quote_hs&ids="+id+"&dt="+getTimestamp());
				stockDetailsTemp1 = httpClient.httpGetUrl(httpClientEntity);
				
				httpClientEntity.setUrl("http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx");
				httpClientEntity.setData("type=CT&sty=FDTH&st=z&js=%7Bquotation:(x),pages:(pc)%7D&token=beb0a0047196124721f56b0f0ff5a27c&cmd="+id);
				stockDetailsTemp2 = httpClient.httpGetUrl(httpClientEntity);
				
				stockDetailsArray = getQuoteSHStockDetails(stockDetailsTemp1,stockDetailsTemp2,searchDao);
			//}
			jsonParam.put("stock_details", stockDetailsArray);
			//jsonParam.put("stock_finance", stockFinanceDetailsArray);
//			jsonParam.put("stock_notice", stockNoticeDetailsArray);
//			jsonParam.put("stock_news", stockNewsDetailsArray);
//			jsonParam.put("stock_analyseReport", stockAnalyseReportDetailsArray);
			//System.out.println("getQuoteSHStockDetails:id--"+pageData.getString("id"));
			//System.out.println("getQuoteSHStockDetails:stockDetailsTemp1结果--"+stockDetailsTemp1);
			//System.out.println("getQuoteSHStockDetails:stockDetailsTemp2结果--"+stockDetailsTemp2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			System.out.println(e);
			throw e;
		}
		return jsonParam;
	}
	
	public JSONArray getQuoteSHStockDetails(String stock1,String stock2,SearchMapper searchDao) throws Exception{
		JSONArray jsonArray = new JSONArray();
		if(!StringUtil.isEmpty(stock1)&&!StringUtil.isEmpty(stock2)){
			int start = stock1.indexOf("{");
			
			if(start>0){
				stock1 = stock1.substring(start);
			}
		
			JSONObject result1 = JSONObject.fromObject(stock1);
			JSONObject result2 = JSONObject.fromObject(stock2);
			
			Object[] quotation = result1.getJSONArray("quotation").toArray();
			
			for (int i = 0; i < quotation.length; i++) {
				String str = (String) quotation[i];
				
				JSONObject json = new JSONObject();
				
				if(!StringUtil.isEmpty(str)){
					String[] strs1 = str.split(",");
					json = stockDetailsRedOrGreen(json,strs1[3],strs1[4],strs1[6],strs1[7]);
					json.put("stock_id", strs1[0]);  //id
					json.put("stock_code", strs1[1]);  //代码
					json.put("stock_name", strs1[2]);  //名称
					json.put("stock_current_price", strs1[5]);  //当前指数
					json.put("stock_current", strs1[5]);  //当前指数
					//json.put("stock_rise_and_fall_price", strs1[10]);  //涨跌额
					//json.put("stock_rise_and_fall_percent", strs1[11]);  //涨跌幅
					json.put("stock_date", formatDate(strs1[28]));  //时间
					
					json.put("stock_open", formatEmpty(strs1[4]));  //今开
					json.put("stock_take", formatEmpty(strs1[3]));  //昨收
					json.put("stock_high", formatEmpty(strs1[6]));  //最高
					json.put("stock_low", formatEmpty(strs1[7]));  //最低
					json.put("stock_amplitude_percent", formatEmpty(strs1[13]));  //振幅
					json.put("stock_deal_count", formatDigital(strs1[9]));  //成交量
					json.put("stock_deal_price", formatDigitalW(strs1[8]));  //成交额
					if("-".equals(strs1[11].substring(0, 1))){
						json.put("stock_red_or_green", "green");
						json.put("stock_rise_and_fall_price", strs1[10]);  //涨跌额
						json.put("stock_rise_and_fall_percent", strs1[11]);  //涨跌幅
					}else{
						json.put("stock_red_or_green", "red");
						json.put("stock_rise_and_fall_price", "+"+strs1[10]);  //涨跌额
						json.put("stock_rise_and_fall_percent", "+"+strs1[11]);  //涨跌幅
					}
					json.put("stock_average_price", formatEmpty(strs1[12]));  //均价
					json.put("stock_totalShareCapital", formatDigital(searchDao.totalShareCapital(strs1[1])));  //总股本
					json.put("stock_out", formatDigital(strs1[17]));  //外盘
					json.put("stock_in", formatDigital(strs1[18]));  //内盘
//					for (int j = 0; j < strs1.length; j++) {
//						System.out.println("strs1:"+j+":"+strs1[j]);
//					}
				}
				if(!StringUtil.isEmpty(stock2)){
					String[] strs2 = result2.getString("quotation").split(",");
					
					json.put("stock_hand_percent", formatEmpty(strs2[34]));  //换手率
					json.put("stock_earning_percent", formatEmpty(strs2[38]));  //市盈率
					json.put("stock_volume_percent", formatEmpty(strs2[35]));  //量比
					json.put("stock_committee_percent", formatEmpty(strs2[42]));  //委比
					json.put("stock_market_value", formatDigital(strs2[40]));  //市值
					
//					for (int j = 0; j < strs2.length; j++) {
//						System.out.println("strs2:"+j+":"+strs2[j]);
//					}
				}
				
				json.put("stock_area", "SH");
				
				
				json.put("stock_rise", formatEmpty(""));  //上涨
				json.put("stock_flat", formatEmpty(""));  //平盘
				json.put("stock_fall", formatEmpty(""));  //下跌
				
				jsonArray.add(json);
				/**
				 * 
				for (int j = 0; j < strs1.length; j++) {
					System.out.println("strs1:"+j+":"+strs1[j]);
				}
				for (int j = 0; j < strs2.length; j++) {
					System.out.println("strs2:"+j+":"+strs2[j]);
				}
				*/
			}
		}
		
		System.out.println("沪股票详情:"+jsonArray);
		
		return jsonArray;
	}
	
	/**
     * 获取随机数
     * @return
     */
    public double getRandom(){
    	return Math.random();
    } 
	
	public JSONObject stockDetailsRedOrGreen(JSONObject json,String stockTake,String stockOpen,String stockHigh,String stockLow){
		if(isRealNumber(stockTake)){
			if(isRealNumber(stockOpen)){
				if(new BigDecimal(stockOpen).subtract(new BigDecimal(stockTake)).compareTo(new BigDecimal(0))>0){
					json.put("stock_open_red_or_green", "red");  
				}else if(new BigDecimal(stockOpen).subtract(new BigDecimal(stockTake)).compareTo(new BigDecimal(0))<0){
					json.put("stock_open_red_or_green", "green");  
				}else{
					json.put("stock_open_red_or_green", "black");  
				}
			}else{
				json.put("stock_open_red_or_green", "");  
			}
			if(isRealNumber(stockHigh)){
				if(new BigDecimal(stockHigh).subtract(new BigDecimal(stockTake)).compareTo(new BigDecimal(0))>0){
					json.put("stock_high_red_or_green", "red");  
				}else if(new BigDecimal(stockHigh).subtract(new BigDecimal(stockTake)).compareTo(new BigDecimal(0))<0){
					json.put("stock_high_red_or_green", "green");  
				}else{
					json.put("stock_high_red_or_green", "black");  
				}
			}else{
				json.put("stock_high_red_or_green", "");  
			}
			if(isRealNumber(stockLow)){
				if(new BigDecimal(stockLow).subtract(new BigDecimal(stockTake)).compareTo(new BigDecimal(0))>0){
					json.put("stock_low_red_or_green", "red");  
				}else if(new BigDecimal(stockLow).subtract(new BigDecimal(stockTake)).compareTo(new BigDecimal(0))<0){
					json.put("stock_low_red_or_green", "green");  
				}else{
					json.put("stock_low_red_or_green", "black");  
				}
			}else{
				json.put("stock_low_red_or_green", ""); 
			}
		}else{
			json.put("stock_open_red_or_green", "");  
			json.put("stock_high_red_or_green", "");  
			json.put("stock_low_red_or_green", ""); 
		}
		
		return json;
	}
	
	public String formatDate(String date){
		date = date.substring(5);
		return date;
	}
	
	
	public String formatEmpty(String str){
		if(StringUtil.isEmpty(str)||"-".equals(str)){
			str = "------";
		}
		return str;
	}
	
	public String formatDigital(String digital){
		if(!StringUtil.isEmpty(digital)){
			if(digital.length()>12){ //千亿
				digital = new BigDecimal(digital).divide(new BigDecimal(100000000),3,BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(1000),3,BigDecimal.ROUND_HALF_UP).toString()+"千亿";
			}else if(digital.length()>8){ //亿
				digital = new BigDecimal(digital).divide(new BigDecimal(100000000),3,BigDecimal.ROUND_HALF_UP).toString()+"亿";
			}else if (digital.length()>5){  //万
				digital = new BigDecimal(digital).divide(new BigDecimal(10000),3,BigDecimal.ROUND_HALF_UP).toString()+"万";
			}
		}
		digital = formatEmpty(digital);
		return digital;
	}
	
	public String formatDigitalW(String digital){
		if(!StringUtil.isEmpty(digital)){
			if(digital.length()>7){ //千亿
				digital = new BigDecimal(digital).divide(new BigDecimal(1000000),3,BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(1000),3,BigDecimal.ROUND_HALF_UP).toString()+"千亿";
			}else if(digital.length()>4){ //亿
				digital = new BigDecimal(digital).divide(new BigDecimal(1000),3,BigDecimal.ROUND_HALF_UP).toString()+"亿";
			}else{  //万
				digital = digital+"万";
			}
		}
		digital = formatEmpty(digital);
		return digital;
	}
	
	private static boolean isMatch(String regex, String orginal){  
        if (orginal == null || orginal.trim().equals("")) {  
            return false;  
        }  
        Pattern pattern = Pattern.compile(regex);  
        Matcher isNum = pattern.matcher(orginal);  
        return isNum.matches();  
    }  
    
    public static boolean isPositiveInteger(String orginal) {  
        return isMatch("^\\+{0,1}[1-9]\\d*", orginal);  
    }  
  
    public static boolean isNegativeInteger(String orginal) {  
        return isMatch("^-[1-9]\\d*", orginal);  
    }  
  
    public static boolean isWholeNumber(String orginal) {  
        return isMatch("[+-]{0,1}0", orginal) || isPositiveInteger(orginal) || isNegativeInteger(orginal);  
    }  
      
    public static boolean isPositiveDecimal(String orginal){  
        return isMatch("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*", orginal);  
    }  
      
    public static boolean isNegativeDecimal(String orginal){  
        return isMatch("^-[0]\\.[1-9]*|^-[1-9]\\d*\\.\\d*", orginal);  
    }  
      
    public static boolean isDecimal(String orginal){  
        return isMatch("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+", orginal);  
    }  
      
    public static boolean isRealNumber(String orginal){  
        return isWholeNumber(orginal) || isDecimal(orginal);  
    }  
    
    /**
	 * 沪深指数
	 * @return
	 */
	public JSONObject getQuoteHSTopIndex() throws Exception{
		JSONObject jsonParam = new JSONObject();
		
		//http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=0000011,3990012,3990052,3990062,hsi5,djia7&sty=MPNSBAS&st=&sr=1&p=1&ps=1000&token=44c9d251add88e27b65ed86506f6e5da&cb=callback01907369359627724&callback=callback01907369359627724&_=1476521261607
		//http://nufm2.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=0000011,3990012,3990062&sty=DFPIU&st=z&sr=&p=&ps=&cb=&js=var%20C1Cache={quotation:[(x)]}&token=44c9d251add88e27b65ed86506f6e5da&0.4917439564170336
		//http://hqdigi2.eastmoney.com/EM_Quote2010NumericApplication/Index.aspx?type=z&sortType=C&sortRule=-1&jsSort=1&jsName=quote_zy&ids=0000011,3990012,0003001,3990052,3990062,0000031,0000021,3991062,3990022,3990032,0000161,3995502&dt=1476527474019
		//http://hqdigi2.eastmoney.com/EM_Quote2010NumericApplication/Index.aspx?type=z&sortType=C&sortRule=-1&jsSort=1&jsName=quote_zy&ids=0000011,3990012,3990062&dt=1476527474019
		String stockTopIndexTemp = "";
		try {
			/**
			 * 上证指数
			 * 深证成指
			 * 创业板指
			 * http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=0000011,3990012,0003001,3990052,3990062,0000031,0000021,3991062,3990022,3990032,0000161,3995502&sty=FCOIATA&sortType=C&sortRule=-1&page=1&pageSize=20&js=var quote_zy={rank:[(x)],pages:(pc)}&token=44c9d251add88e27b65ed86506f6e5da&jsName=quote_zy&dt=1501738280988
			 */
			JSONArray stockTopIndexArray = new JSONArray();
//			httpClientEntity.setUrl("http://hqdigi2.eastmoney.com/EM_Quote2010NumericApplication/Index.aspx");
//			httpClientEntity.setData("type=z&sortType=C&sortRule=-1&jsSort=1&jsName=quote_zy&ids=0000011,3990012,3990062&dt"+getTimestamp());
			httpClientEntity.setUrl("http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx");
			httpClientEntity.setData("type=CT&cmd=0000011,3990012,0003001,3990052,3990062,0000031,0000021,3991062,3990022,3990032,0000161,3995502&sty=FCOIATA&sortType=C&sortRule=-1&page=1&pageSize=20&js=%7brank:%5b(x)%5d,pages:(pc)%7d&token=44c9d251add88e27b65ed86506f6e5da&jsName=quote_zy&dt"+getTimestamp());
			stockTopIndexTemp = httpClient.httpGetUrl(httpClientEntity);
			stockTopIndexArray = getQuoteHSStockIndex(stockTopIndexTemp);
			
			jsonParam.put("stock_index", stockTopIndexArray);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("getQuoteHSTopIndex");
			System.out.println("getQuoteHSTopIndex:stockTopIndexTemp结果--"+stockTopIndexTemp);
			System.out.println(e);
			throw e;
		}
		return jsonParam;
	}
	
	/**
	 * 上证指数
	 * 深证成指
	 * 创业板指
	 * @param stockTop
	 * @return
	 */
	public JSONArray getQuoteHSStockTopIndex(String stockTop) throws Exception{
		JSONArray jsonArray = new  JSONArray();
		if(!StringUtil.isEmpty(stockTop)){
			int start = stockTop.indexOf("{");
			
			if(start>0){
				stockTop = stockTop.substring(start);
			}
			JSONObject result = JSONObject.fromObject(stockTop);
			
			Object[] quotation = result.getJSONArray("quotation").toArray();
			
			for (int i = 0; i < quotation.length; i++) {
				String str = (String) quotation[i];
				String[] strs = str.split(",");
				String stockArea = "";
				JSONObject json = new JSONObject();
				json.put("stock_id", strs[0]);  //id
				json.put("stock_code", strs[1]);  //代码
				json.put("stock_name", strs[2]);  //名称
				json.put("stock_current_price", strs[5]);
				
				if("-".equals(strs[11].substring(0, 1))){
					json.put("stock_red_or_green", "green");
					json.put("stock_rise_and_fall_price", strs[10]);
					json.put("stock_rise_and_fall_percent", strs[11]);
				}else{
					json.put("stock_red_or_green", "red");
					json.put("stock_rise_and_fall_price", "+"+strs[10]);
					json.put("stock_rise_and_fall_percent", "+"+strs[11]);
				}
				
				json.put("stock_deal_count", formatDigital(strs[9]));  //成交量
				json.put("stock_deal_price", formatDigital(strs[8]));  //成交额
				//0000011,3990012,3990062
				if("0000011".equals(strs[0])){
					stockArea = "SH";
				}else if("3990012".equals(strs[0])){
					stockArea = "SZ";
				}else if("3990062".equals(strs[0])){
					stockArea = "SZ";
				}
				json.put("stock_area", stockArea);
				
				jsonArray.add(json);
				
			}
		}
		
		System.out.println("沪深指数:"+jsonArray);
		
		return jsonArray;
	}
	
	/**
	 * 沪深指数
	 * @param stock
	 * @return
	 */
	public JSONArray getQuoteHSStockIndex(String stock) throws Exception{
		JSONArray jsonArray = new  JSONArray();
		if(!StringUtil.isEmpty(stock)){
			
			JSONObject result = JSONObject.fromObject(stock);
			
			Object[] quotation = result.getJSONArray("rank").toArray();
			
			for (int i = 0; i < quotation.length; i++) {
				String str = (String) quotation[i];
				String[] strs = str.split(",");
//				String stockArea = "";
				JSONObject json = new JSONObject();
				json.put("stock_id", strs[0]);  //id
				json.put("stock_code", strs[1]);  //代码
				json.put("stock_name", strs[2]);  //名称
				json.put("stock_current_price", strs[3]);
				
				if("-".equals(strs[5].substring(0, 1))){
					json.put("stock_red_or_green", "green");
					json.put("stock_rise_and_fall_price", strs[4]);
					json.put("stock_rise_and_fall_percent", strs[5]);
				}else{
					json.put("stock_red_or_green", "red");
					json.put("stock_rise_and_fall_price", "+"+strs[4]);
					json.put("stock_rise_and_fall_percent", "+"+strs[5]);
				}
				
				json.put("stock_deal_count", formatDigital(strs[7]));  //成交量
				json.put("stock_deal_price", formatDigital(strs[8]));  //成交额
//				0000011,3990012,3990062
//				if("0000011".equals(strs[0])){
//					stockArea = "SH";
//				}else if("3990012".equals(strs[0])){
//					stockArea = "SZ";
//				}else if("3990062".equals(strs[0])){
//					stockArea = "SZ";
//				}
//				json.put("stock_area", stockArea);
				
				jsonArray.add(json);
				for (int j = 0; j < strs.length; j++) {
					System.out.println("strs:"+j+":"+strs[j]);
				}
			}
		}
		
		System.out.println("沪深指数:"+jsonArray);
		
		return jsonArray;
	}
	
    /**
	 * 沪深涨幅榜
	 * @return
	 */
	public JSONObject getSHSZStockRiseJSON(Map pageData) throws Exception{
		JSONObject SHSZStockRiseJSON = new JSONObject();
		String SHSZStockRiseTemp = "";
		
		if(!pageData.containsKey("pageSize")){
			pageData.put("pageSize", "15");
		}
		
		if(!pageData.containsKey("page")){
			pageData.put("page", "1");
		}
		
		pageData.put("cmd", "C._AB");
		pageData.put("title", "沪深股票行情");
		
		try {
			/**
			 * 沪深涨幅榜 C._AB
			 */
			httpClientEntity.setUrl("http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx");
			httpClientEntity.setData("type=CT&cmd="+pageData.get("cmd")+"&sty=FCOIATA&sortType=C&sortRule=-1&page="+pageData.get("page")+"&pageSize="+pageData.get("pageSize")+"&js=%7Brank:[(x)],pages:(pc)%7D&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote&_g="+getRandom());
			SHSZStockRiseTemp = httpClient.httpGetUrl(httpClientEntity);
			SHSZStockRiseJSON = getQuoteSHSZStockRise(SHSZStockRiseTemp,pageData.get("title").toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return SHSZStockRiseJSON;
	}
    
	/**
	 * 沪深涨幅榜
	 * @param stockRise
	 * @return
	 */
	public JSONObject getQuoteSHSZStockRise(String stockRise,String title) throws Exception{
		JSONArray jsonArray = new JSONArray();
		String pages = "";
		if(!StringUtil.isEmpty(stockRise)){
			JSONObject result = JSONObject.fromObject(stockRise);
			Object[] rank = result.getJSONArray("rank").toArray();
			pages = result.getString("pages");
			for (int i = 0; i < rank.length; i++) {
				String str = (String) rank[i];
				String[] strs = str.split(",");
				JSONObject json = new JSONObject();
				json.put("stock_id", strs[1]+strs[0]);  //id
				json.put("stock_code", strs[1]);  //代码
				json.put("stock_name", strs[2]);  //名称
				json.put("stock_current_price", formatEmpty(strs[3]));  //最新价
				json.put("stock_amplitude_percent", formatEmpty(strs[6]));  //振幅
				json.put("stock_deal_count", formatDigital(strs[7]));  //成交量(手)
				json.put("stock_deal_price", formatDigital(strs[8]));  //成交额
				json.put("stock_take", formatEmpty(strs[9]));  //昨收
				json.put("stock_open", formatEmpty(strs[10]));  //今开
				json.put("stock_high", formatEmpty(strs[11]));  //最高
				json.put("stock_low", formatEmpty(strs[12]));  //最低
				
				if("-".equals(strs[5].substring(0, 1))){
					json.put("stock_red_or_green", "green");
					json.put("stock_rise_and_fall_price", formatEmpty(strs[4]));  //涨跌额
					json.put("stock_rise_and_fall_percent", formatEmpty(strs[5]));  //涨跌幅
				}else{
					json.put("stock_red_or_green", "red");
					json.put("stock_rise_and_fall_price", "+"+formatEmpty(strs[4]));  //涨跌额
					json.put("stock_rise_and_fall_percent", "+"+formatEmpty(strs[5]));  //涨跌幅
				}
				
				if(isRealNumber(strs[9])&&isRealNumber(strs[10])){
					if(new BigDecimal(strs[10]).subtract(new BigDecimal(strs[9])).compareTo(new BigDecimal(0))>0){
						json.put("stock_open_red_or_green", "red");  
					}else if(new BigDecimal(strs[10]).subtract(new BigDecimal(strs[9])).compareTo(new BigDecimal(0))<0){
						json.put("stock_open_red_or_green", "green");  
					}else{
						json.put("stock_open_red_or_green", "black");  
					}
				}else{
					json.put("stock_open_red_or_green", "");  
				}
				
				if("1".equals(strs[0])){
					json.put("stock_area", "SH");
				}else{
					json.put("stock_area", "SZ");
				}
				
				jsonArray.add(json);
//				for (int j = 0; j < strs.length; j++) {
//					logger.info(title+j+":"+strs[j]);
//				}
			}
		}
		
		JSONObject DataJSON = new JSONObject();
		DataJSON.put("title", title);
		DataJSON.put("data", jsonArray);
		DataJSON.put("pages", pages);
		return DataJSON;
	}
	
    public Map<Object, Object> getHghy(Map<String, String> query) throws Exception{
    	Map<Object, Object> map = new HashMap<Object, Object>();
		String result = "";
		String data = "";
		
		if(query.containsKey("keyWord") && !StringUtil.isEmpty((String)query.get("keyWord"))){
			data += "q=IndicatorName%3A%22"+query.get("keyWord")+"%22+or+IndicatorName%3A"+query.get("keyWord");
		}else{
			data += "q=*%3A*";
		}
		
		if(query.containsKey("start")){
			data += "&start="+query.get("start");
		}
		
		if(query.containsKey("rows")){
			data += "&rows="+query.get("rows");
		}
		
		data += "&wt=json&indent=true";
		
		try {
			httpClientEntity.setUrl("http://sj.dfans.cn/searchCenter/zrData/select");
			httpClientEntity.setData(data);
			result = httpClient.httpGetUrl(httpClientEntity);
			if(!StringUtil.isEmpty(result)){
				JSONObject jsonObject = JSONObject.fromObject(result);
				JSONObject responseHeader = JSONObject.fromObject(jsonObject.get("responseHeader"));
				JSONObject response = JSONObject.fromObject(jsonObject.get("response"));
				JSONArray docs = JSONArray.fromObject(response.get("docs"));
				String numFound = response.getString("numFound");
				map.put("numFound", numFound);
				if("0".equals(responseHeader.getString("status"))){
					List<Map> list = (List<Map>) docs;
					map.put("list", list);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			throw e;
		}
		return map;
	}
    
    public static void main(String[] a) throws Exception{
    	GetDataUtils g = new GetDataUtils();
    	g.getQuoteSHStockDetails("000005",null);
    }
    
}