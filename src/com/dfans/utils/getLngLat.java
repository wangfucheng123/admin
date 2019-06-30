package com.dfans.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.dao.DataAccessException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class getLngLat {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
//		String str ="showLocation&&showLocation({'status':0,'result':{'location':{'lng':123.51067544660896,'lat':43.52386291613512},'precise':1,'confidence':80,'level':'金融'}})";

		Connection conn = getConnection("shujin", "dfl12345"); 
		String sql = "SELECT name,bgdz FROM `broker_yyb` where 1=1 and lng is not null LIMIT 0,5000 ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = null;
        try {  
        	rs = pstmt.executeQuery();
        	System.out.println(rs);
		} catch(DataAccessException e) {

		} 
        while (rs.next()) {
        	String name =rs.getString("name");
        	String bgdz =rs.getString("bgdz");
        	System.out.println(name);
        	String url ="http://api.map.baidu.com/geocoder/v2/?address="+bgdz+"&output=json&ak=LVBnyrLpgZmSrsaUo37bkEo4pVUk9Dg8&callback=showLocation&qq-pf-to=pcqq.c2c";
        	String lngLat = httpPost(url, null);
        	
        	insertLngLat(conn,lngLat,name);
        }
	}
	
	
	public static String httpPost(String url, Map<String, String> params) {

        URL u = null;
        HttpURLConnection con = null;
        //构建请求参数
        StringBuffer sb = new StringBuffer();
    
        if(params!=null){
    
            for (Entry<String, String> e : params.entrySet()) {
                sb.append(e.getKey());
                sb.append("=");
                sb.append(e.getValue());
                sb.append("&");
            }
            sb.substring(0, sb.length() - 1);
        }
//    
//        System.out.println("send_url:"+url);
//        System.out.println("send_data:"+sb.toString());
    
        //尝试发送请求
        try {
            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            //setConnectTimeout：设置连接主机超时（单位：毫秒）
            //setReadTimeout：设置从主机读取数据超时（单位：毫秒）
            con.setConnectTimeout(30000);//默认30秒
            con.setReadTimeout(30000);//默认30秒
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "GBK");
            osw.write(sb.toString());
            osw.flush();
            osw.close();
    
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
    
            if (con != null) {
              con.disconnect();
            }
        }
    
        //读取返回内容
        StringBuffer buffer = new StringBuffer();
        try {
    
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String temp;
            while ((temp = br.readLine()) != null) {
        
              buffer.append(temp);
              buffer.append("\n");
            }
        }catch (Exception e) {
    
          e.printStackTrace();
        }
        System.out.println(buffer.toString());
        return buffer.toString();
    }
	
	
	 //数据库连接
	public static Connection getConnection(String user, String pass) {
	    System.out.println("=============================");
		Connection conn = null;//声明连接对象
	    String driver = "com.mysql.jdbc.Driver";// 驱动程序类名
	    String url = "jdbc:mysql://db.dfans.cn:3306/zr_data?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";// 防止乱码
	    try {
	    	Class.forName(driver);// 注册(加载)驱动程序
	        conn = DriverManager.getConnection(url, user, pass);// 获取数据库连接
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	public static void updateByPrimaryKeySelective(Connection conn,Integer id,double lng,double lat) throws Exception {
		String sql= "update broker_yyb  set lng = ?,lat =? where id =?";
		PreparedStatement pstmt2 = conn.prepareStatement(sql);
		pstmt2.setObject(1, lng);
		pstmt2.setObject(2, lat);
		pstmt2.setObject(3, id);
		try {  
			pstmt2.executeUpdate();
		} catch(Exception e) {

		} 
	}
	
    public static String selectIdByName(Connection conn ,String name) throws Exception {  
    	
        String sql = "select id from broker_yyb where name=?";
        
        String id = "";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setObject(1, name);

        ResultSet rs = null;
        try {  
        	rs = pstmt.executeQuery();
		} catch(DataAccessException e) {

		} 
        while (rs.next()) {
        	id=rs.getString(1);
		}
        return id;
    } 
    
    public static void insertLngLat(Connection conn,String lngLat,String name){
		String sssString = lngLat.replace("showLocation&&showLocation(", "").replace(")", "");
		String json = "["+sssString+"]";
		
		try {
	         // 把字符串转换为JSONArray对象
	         JSONArray jsonArray = JSONArray.fromObject(json);
	         if(jsonArray.size() > 0){
	         // 遍历 jsonarray 数组，把每一个对象转成 json 对象
        		 JSONObject jsonObject = jsonArray.getJSONObject(0);
        		 String str2 = "["+jsonObject.get("result")+"]";
        		 JSONArray jsonArray2 = JSONArray.fromObject(str2);
        		 if(jsonArray2.size() > 0){
        			 JSONObject jsonObject2 = jsonArray2.getJSONObject(0);
        			 
        			 String str3 = "["+jsonObject2.get("location")+"]";
        			 JSONArray jsonArray3 = JSONArray.fromObject(str3);
        			 
        			 if(jsonArray3.size() > 0){
            			 JSONObject jsonObject3 = jsonArray3.getJSONObject(0);
            			 
            			 double lng = jsonObject3.getDouble("lng");
            			 double lat = jsonObject3.getDouble("lat");
            			 int id =(int) Float.parseFloat(selectIdByName(conn,name));
            			 
            			 updateByPrimaryKeySelective(conn,id,lng,lat);
            			 
            			 System.out.println("经度："+jsonObject3.get("lng"));
            			 System.out.println("纬度："+jsonObject3.get("lat"));
            		 }
        		 }
	         }
	    } catch (Exception e) {
	    }
    }
}
