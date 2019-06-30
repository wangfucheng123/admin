package com.wlw.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class test {

	public static List<Map> excle=new ArrayList();
	public static void main(String args[]) throws DocumentException
	{
		
		int i=1;
		while(true)
		{
			//String content=readFileByUrl("http://www.tjconstruct.cn/Zbgs/Index/"+i);
			String content=readFileByUrl("http://www.tjconstruct.cn/Zbgs/Index/"+i+"?type=qtzb");
			if(content.indexOf("color:#0265ae;")>0)
			{
				content=content.substring(content.indexOf("color:#0265ae;"),content.length());
				content=content.substring(0,content.indexOf("</table>"));
			}
			
			
			while(content.indexOf("<a href=")>0)
			{
				//System.out.println(content.indexOf(".htm"));
				String url=content.substring(content.indexOf("<a href=")+9,content.indexOf(".htm")+4);
				System.out.println(url);
				content=content.substring(content.indexOf(".htm")+4,content.length());
				url=url.replaceAll(" ","");
				
				String msg=((readFileByUrl(url).trim()).replaceAll("&nbsp;","")).replaceAll(" ","");
				if(msg.equals(""))
				{
					continue;
				}
				createData(msg);
			}
			System.out.println(excle.toString());
			i++;
			if(i==25)//if(i==220)
			{
				break;
			}
		}
		createExcel();
		
		//conn=getConn();
//		 PreparedStatement pstmt;
//		 try {
//	            pstmt = getConn().prepareStatement("select user,table_name from all_tables");
//	            //建立一个结果集，用来保存查询出来的结果
//	            ResultSet rs = pstmt.executeQuery();
//	            while (rs.next()) {
//	                String name = rs.getString("table_name");
//	                System.out.println(name);
//	            }
//	            rs.close();
//	            pstmt.close();
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
	}
	
	public static void createExcel()
	{
		//第一步创建workbook  
        HSSFWorkbook wb = new HSSFWorkbook();  
          
        //第二步创建sheet  
        HSSFSheet sheet = wb.createSheet("数据");  
          
        //第三步创建行row:添加表头0行  
        HSSFRow row = sheet.createRow(0);  
        HSSFCellStyle  style = wb.createCellStyle();      
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  //居中  
          
          
        //第四步创建单元格  
        HSSFCell cell = row.createCell(0); //第一个单元格  
        cell.setCellValue("建设单位");  
        cell.setCellStyle(style);  
        
        
        
        cell = row.createCell(1);         //第二个单元格     
        cell.setCellValue("招标代理单位");  
        cell.setCellStyle(style);  
        
        cell = row.createCell(2);         //第二个单元格     
        cell.setCellValue("工程名称");  
        cell.setCellStyle(style);  
        
        cell = row.createCell(3);         //第二个单元格     
        cell.setCellValue("中标规模");  
        cell.setCellStyle(style); 
        
        cell = row.createCell(4);         //第二个单元格     
        cell.setCellValue("中标标价");  
        cell.setCellStyle(style); 
          
       
        for(int i=0;i<excle.size();i++)
        {
        	row = sheet.createRow(i+1);
        	row.createCell(0).setCellValue(excle.get(i).get("jsdw").toString());  
            row.createCell(1).setCellValue(excle.get(i).get("zbdldw").toString());
            try{
            row.createCell(2).setCellValue(excle.get(i).get("gcmc").toString()); 
            
            row.createCell(3).setCellValue(excle.get(i).get("zbgm").toString()); 
            row.createCell(4).setCellValue(excle.get(i).get("zbbj").toString()); }catch(Exception e){}
        }
        //第五步插入数据  
//       
//        for (int i = 0; i < 5; i++) {  
//            //创建行  
//            row = sheet.createRow(i+1);  
//            //创建单元格并且添加数据  
//            row.createCell(0).setCellValue("aa"+i);  
//            row.createCell(1).setCellValue(i);  
//            
//        }  
          
        //第六步将生成excel文件保存到指定路径下  
        try {  
            FileOutputStream fout = new FileOutputStream("F:\\pc\\a.xls");  
            wb.write(fout);  
            fout.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}
	
	public static void createData(String msg)
	{
		Map m=new HashMap();
		if(msg.indexOf("建设单位：")>0)
		{
			msg=msg.substring(msg.indexOf("建设单位："),msg.length());
			m.put("jsdw",msg.substring(msg.indexOf("建设单位：")+5,msg.indexOf("<br>")));
			msg=msg.replace(m.get("jsdw").toString()+"<br>","");
		}
		if(msg.indexOf("招标代理单位：")>0)
		{
			msg=msg.substring(msg.indexOf("招标代理单位："),msg.length());
			m.put("zbdldw",msg.substring(msg.indexOf("招标代理单位：")+7,msg.indexOf("<br>")));
			msg=msg.replace(m.get("zbdldw").toString()+"<br>","");
		}
		
		if(msg.indexOf("，共发布了")>0)
		{
			//msg=msg.substring(msg.indexOf("工程名称"),msg.length());
			String temp[]=msg.substring(0,msg.indexOf("，共发布了")).split("<u>");
			
			m.put("gcmc",(temp[temp.length-1]).replace("</u>\r\n，","").replace("</u>",""));
			//msg=msg.replace(m.get("jsdw").toString()+"<br>","");
		}
		
		if(msg.indexOf("通过公开招标，")>0)
		{
			//msg=msg.substring(msg.indexOf("工程名称"),msg.length());
			String temp[]=msg.substring(0,msg.indexOf("通过公开招标，")).split("<u>");
			
			m.put("gcmc",(temp[temp.length-1]).replace("</u>\r\n，",""));
			//msg=msg.replace(m.get("jsdw").toString()+"<br>","");
		}
		if(msg.indexOf("中标规模：")>0)
		{
			msg=msg.substring(msg.indexOf("中标规模："),msg.length());
			m.put("zbgm",msg.substring(msg.indexOf("中标规模：")+5,msg.indexOf("<br>")));
			msg=msg.replace(m.get("zbgm").toString()+"<br>","");
		}
		if(msg.indexOf("承包规模")>0)
		{
			msg=msg.substring(msg.indexOf("承包规模："),msg.length());
			m.put("zbgm",msg.substring(msg.indexOf("承包规模：")+5,msg.indexOf("<br>")));
			msg=msg.replace(m.get("zbgm").toString()+"<br>","");
		}
		if(msg.indexOf("中标标价：")>0)
		{
			msg=msg.substring(msg.indexOf("中标标价："),msg.length());
			m.put("zbbj",msg.substring(msg.indexOf("中标标价：")+5,msg.indexOf("<br>")));
			msg=msg.replace(m.get("zbbj").toString()+"<br>","");
		}
		if(msg.indexOf("承包标价")>0)
		{
			msg=msg.substring(msg.indexOf("承包标价："),msg.length());
			m.put("zbbj",msg.substring(msg.indexOf("承包标价：")+5,msg.indexOf("<br>")));
			msg=msg.replace(m.get("zbbj").toString()+"<br>","");
		}
		excle.add(m);
	}
	
	public static String readFileByUrl(String urlStr) {
        String res="";
        try {
            URL url = new URL(urlStr);  
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
            //设置超时间为3秒
            conn.setConnectTimeout(3*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
            InputStream inputStream = conn.getInputStream();  
            res = readInputStream(inputStream);
        } catch (Exception e) {
            System.out.println("通过url地址获取文本内容失败 Exception：" + e);
            //readFileByUrl(urlStr);
        }
        finally{
        	return res;
        }
    }
	
	
	/**
     * 从输入流中获取字符串
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String readInputStream(InputStream inputStream) throws IOException {  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        while((len = inputStream.read(buffer)) != -1) {  
            bos.write(buffer, 0, len);  
        }  
        bos.close();  
        //System.out.println(new String(bos.toByteArray(),"utf-8"));
        return new String(bos.toByteArray(),"gb2312");
    }
    
    
    
    
    private static Connection conn = null;
    
    private static String driver = "oracle.jdbc.driver.OracleDriver"; //驱动
     
    private static String url = "jdbc:oracle:thin:@//125.208.12.92:1521/ora11g"; //连接字符串
     
    private static String username = "juyuan"; //用户名
     
    private static String password = "juyuan"; //密码
     
     
    // 获得连接对象
    private static  Connection getConn(){
        if(conn == null){
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
     
    //执行查询语句
    public void query(String sql, boolean isSelect) throws SQLException{
        PreparedStatement pstmt;
         
        try {
            pstmt = getConn().prepareStatement(sql);
            //建立一个结果集，用来保存查询出来的结果
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                System.out.println(name);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
    public void query(String sql) throws SQLException{
        PreparedStatement pstmt;
        pstmt = getConn().prepareStatement(sql);
        pstmt.execute();
        pstmt.close();
    }
     
     
    //关闭连接
    public void close(){
        try {
            getConn().close();
             
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
