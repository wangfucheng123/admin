package com.dfans.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.dfans.cache.UserMemoy;

public class IpUtils {

	
	public static String getIpAddr(HttpServletRequest request) throws Exception {    
        String ip = request.getHeader("x-forwarded-for");    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("Proxy-Client-IP");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("WL-Proxy-Client-IP");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("HTTP_CLIENT_IP");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getRemoteAddr();    
        }    
        return ip;    
    }    
	
	public static String getMACAddress(String ip) throws Exception {    
        String line = "";    
        String macAddress = "";    
        final String MAC_ADDRESS_PREFIX = "MAC Address = ";    
        final String LOOPBACK_ADDRESS = "127.0.0.1";    
        //如果为127.0.0.1,则获取本地MAC地址。    
        if (LOOPBACK_ADDRESS.equals(ip)) {    
            InetAddress inetAddress = InetAddress.getLocalHost();    
            //貌似此方法需要JDK1.6。    
            byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();    
            //下面代码是把mac地址拼装成String    
            StringBuilder sb = new StringBuilder();    
            for (int i = 0; i < mac.length; i++) {    
                if (i != 0) {    
                    sb.append("-");    
                }    
                //mac[i] & 0xFF 是为了把byte转化为正整数    
                String s = Integer.toHexString(mac[i] & 0xFF);    
                sb.append(s.length() == 1 ? 0 + s : s);    
            }    
            //把字符串所有小写字母改为大写成为正规的mac地址并返回    
            macAddress = sb.toString().trim().toUpperCase();    
            return macAddress;    
        }    
        //获取非本地IP的MAC地址    
        try {    
            Process p = Runtime.getRuntime().exec("ipconfig /all");// windows下的命令，显示信息中包含有mac地址信息
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            //String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
                index = line.toLowerCase().indexOf("physical address");// 寻找标示字符串[physical
                                                                        // address]
                if (index <  0) {
                    index = line.toLowerCase().indexOf("物理地址");// 寻找标示字符串[物理地址
                }
                if (index >= 0) {// 找到了
                    index = line.indexOf(":");// 寻找":"的位置
                    if (index >= 0) {
                    	macAddress = line.substring(index + 1).trim();// 取出mac地址并去除2边空格
                    }
                    break;
                }
            }
        } catch (Exception e) {    
            System.out.println(e);   
        }    
        return macAddress;    
    }    
	
	
	
	public static String callCmd(String[] cmd) { 
	     String result = ""; 
	     String line = ""; 
	     try { 
	       Process proc = Runtime.getRuntime().exec(cmd); 
	       InputStreamReader is = new InputStreamReader(proc.getInputStream()); 
	       BufferedReader br = new BufferedReader (is); 
	       while ((line = br.readLine ()) != null) { 
	       result += line; 
	       } 
	     } 
	     catch(Exception e) { 
	       e.printStackTrace(); 
	     } 
	     return result; 
	   }
	    
	   /** 
	   * 
	   * @param cmd 第一个命令 
	   * @param another 第二个命令 
	   * @return  第二个命令的执行结果 
	   */
	   public static String callCmd(String[] cmd,String[] another) { 
	     String result = ""; 
	     String line = ""; 
	     try { 
	       Runtime rt = Runtime.getRuntime(); 
	       Process proc = rt.exec(cmd); 
	       proc.waitFor(); //已经执行完第一个命令，准备执行第二个命令 
	       proc = rt.exec(another); 
	       InputStreamReader is = new InputStreamReader(proc.getInputStream()); 
	       BufferedReader br = new BufferedReader (is); 
	       while ((line = br.readLine ()) != null) { 
	         result += line; 
	       } 
	     } 
	     catch(Exception e) { 
	       e.printStackTrace(); 
	     } 
	     return result; 
	   }
	    
	    
	    
	   /** 
	   * 
	   * @param ip 目标ip,一般在局域网内 
	   * @param sourceString 命令处理的结果字符串 
	   * @param macSeparator mac分隔符号 
	   * @return mac地址，用上面的分隔符号表示 
	   */
	   public static String filterMacAddress(final String ip, final String sourceString,final String macSeparator) { 
	     String result = ""; 
	     String regExp = "((([0-9,A-F,a-f]{1,2}" + macSeparator + "){1,5})[0-9,A-F,a-f]{1,2})"; 
	     Pattern pattern = Pattern.compile(regExp); 
	     Matcher matcher = pattern.matcher(sourceString); 
	     while(matcher.find()){ 
	       result = matcher.group(1); 
	       if(sourceString.indexOf(ip) <= sourceString.lastIndexOf(matcher.group(1))) { 
	         break; //如果有多个IP,只匹配本IP对应的Mac. 
	       } 
	     }
	   
	     return result; 
	   }
	    
	    
	    
	   /** 
	   * 
	   * @param ip 目标ip 
	   * @return  Mac Address 
	   * 
	   */
	   public static String getMacInWindows(final String ip){ 
	     String result = ""; 
	     String[] cmd = { 
	         "cmd", 
	         "/c", 
	         "ping " + ip 
	         }; 
	     String[] another = { 
	         "cmd", 
	         "/c", 
	         "arp -a"
	         }; 
	   
	     String cmdResult = callCmd(cmd,another); 
	     result = filterMacAddress(ip,cmdResult,"-"); 
	   
	     return result; 
	   } 
	 
	   /** 
	   * @param ip 目标ip 
	   * @return  Mac Address 
	   * 
	   */
	   public static String getMacInLinux(final String ip){ 
	     String result = ""; 
	     String[] cmd = { 
	         "/bin/sh", 
	         "-c", 
	         "ping " + ip + " -c 2 && arp -a"
	         }; 
	     String cmdResult = callCmd(cmd); 
	     result = filterMacAddress(ip,cmdResult,":"); 
	   
	     return result; 
	   } 
	    
	   /**
	   * 获取MAC地址 
	   * @return 返回MAC地址
	   */
	   public static String getMacAddress(String ip){
	     String macAddress = "";
	     macAddress = getMacInWindows(ip).trim();
	     if(macAddress==null||"".equals(macAddress)){
	       macAddress = getMacInLinux(ip).trim();
	     }
	     return macAddress;
	   }
	
	public static boolean checkUserLogin(String userid,HttpServletRequest request) throws Exception
	{
		Map m=UserMemoy.userMap.get(userid);
		if(m==null)
		{
			return false;
		}
		if(!m.get("ip").toString().equals(getIpAddr(request)) || !m.get("mAddr").toString().equals(getMACAddress(getIpAddr(request))))
		{
			return false;
		}
		return true;
	}
}
