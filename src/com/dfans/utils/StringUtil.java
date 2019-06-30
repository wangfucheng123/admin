package com.dfans.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;


/**
 * 字符串相关方法
 *
 */
public class StringUtil {

	private final static String[] agent = { "Android", "iPhone", "iPod","iPad", "Windows Phone", "MQQBrowser" };
	
	public static boolean isEmpty(String string){
  	  boolean flag= true;
  	  if(!"".equals(string)&&null!=string){
  		  flag=false;
  	  }
  	  return flag;
    }
	
	public static String getUserId(HttpServletRequest request)
	{
		// 手机模块无固定cookie.
		String user_id = request.getParameter("userId");
		if(StringUtils.isNotEmpty(user_id)){
			return user_id;
		}
		
		// 浏览器测试默认ID
		String userId="68";
		// 客户端环境写入浏览器的cookie.
		Cookie [] cookies = request.getCookies();  
		for(int i=0;cookies!=null && i<cookies.length;i++){  
            String cname = cookies[i].getName();  
            String cvalue = cookies[i].getValue(); 
            if(cname.equals("userId")){
            	userId=cvalue;
            }
        }   
		return userId;
	}
	public static String[] StrList(String valStr){
	    int i = 0;
	    String TempStr = valStr;
	    String[] returnStr = new String[valStr.length() + 1 - TempStr.replace(",", "").length()];
	    valStr = valStr + ",";
	    while (valStr.indexOf(',') > 0)
	    {
	        returnStr[i] = valStr.substring(0, valStr.indexOf(','));
	        valStr = valStr.substring(valStr.indexOf(',')+1 , valStr.length());
	        
	        i++;
	    }
	    return returnStr;
	}
	
    public static String GBK2Unicode(String str) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char chr1 = (char) str.charAt(i);
  
            if (!isNeedConvert(chr1)) {
                result.append(chr1);
                continue;
            }
  
            result.append("\\u" + Integer.toHexString((int) chr1));
        }
  
        return result.toString();
    }
    public static String GBK2UTF8(String str) {  	
    	
    	try {
    		return new String(str.getBytes("UTF-8"), "UTF-8");            
    	} catch(Exception e) {
    		
    	}
    	return str;
    }
    
	public static boolean isNeedConvert(char para) {
		return ((para & (0x00FF)) != para);
	}
	
	 public static String delHTMLTag(String htmlStr){ 
	        String regEx_html="<[^>]+>";      
	        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
	        Matcher m_html=p_html.matcher(htmlStr); 
	        htmlStr=m_html.replaceAll(""); 
	        return htmlStr.trim(); 
	    }
	 public static String getDate(String word)
	 {
		 String ret="";
		 Pattern p = Pattern.compile("(\\d+)");
		 Matcher m  = p.matcher(word);
		 m.matches();
		 List temp=new ArrayList();
		 while(m.find())
		 {
			 temp.add(m.group(1));
		 }
		 for(int i=0;i<temp.size();i++)
		 {
			 
		 }
		 return ret;
	  }
	 
	 public static String fullWidth2halfWidth(String fullWidthStr) {
	        if (null == fullWidthStr || fullWidthStr.length() <= 0) {
	            return "";
	        }
	        char[] charArray = fullWidthStr.toCharArray();
	        //对全角字符转换的char数组遍历
	        for (int i = 0; i < charArray.length; ++i) {
	            int charIntValue = (int) charArray[i];
	            //如果符合转换关系,将对应下标之间减掉偏移量65248;如果是空格的话,直接做转换
	            if (charIntValue >= 65281 && charIntValue <= 65374) {
	                charArray[i] = (char) (charIntValue - 65248);
	            } else if (charIntValue == 12288) {
	                charArray[i] = (char) 32;
	            }
	        }
	        return new String(charArray);
	    }
	 
	 /**
	 * 判断User-Agent 是不是来自于手机
	 * @param ua
	 * @return
	 */
	 public static boolean checkAgentIsMobile(String ua) {
		 boolean flag = false;
		 if (!ua.contains("Windows NT") || (ua.contains("Windows NT") && ua.contains("compatible; MSIE 9.0;"))) {
			 // 排除 苹果桌面系统
			 if (!ua.contains("Windows NT") && !ua.contains("Macintosh")) {
				 for (String item : agent) {
					 if (ua.contains(item)) {
						 flag = true;
						 break;
					 }
				 }
			 }
		 }
		return flag;
	 }
		  public static void main(String[] args) {
			 
		  }
}
