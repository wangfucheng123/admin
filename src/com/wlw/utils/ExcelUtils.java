package com.wlw.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {

	public static String getExcelValue(Sheet sheet,int i,int k)
	{
		Row r=sheet.getRow(i);
        Cell c=r.getCell(k);
        String ret="";
        if(c!=null && !c.equals(""))
        {
	        switch (c.getCellType()) {   //根据cell中的类型来输出数据  
	        case HSSFCell.CELL_TYPE_NUMERIC:  
	        	ret=""+c.getNumericCellValue();  
	            break;  
	        case HSSFCell.CELL_TYPE_STRING:  
	        	ret=c.getStringCellValue().trim();  
	            break;  
	        case HSSFCell.CELL_TYPE_BOOLEAN:  
	        	ret=""+c.getBooleanCellValue();  
	            break;  
	        case HSSFCell.CELL_TYPE_FORMULA:  
	        	ret=""+c.getCellFormula();  
	            break;
	        default:  
	            System.out.println("unsuported sell type");  
	        break;  
	        }  
        }
		return ret;
	}
	public static Workbook getExcelWB(String file)
	{
		Workbook wb  = null;  
		boolean isE2007 = false;
        if(file.endsWith("xlsx"))
        {
            isE2007 = true;
        }
        try
		{
			FileInputStream fis=new FileInputStream(file);   
            //根据文件格式(2003或者2007)来初始化  
            if(isE2007)
            {
                wb = new XSSFWorkbook(fis); 
            }
            else
            {
                wb = new HSSFWorkbook(fis);
            }
		}
        catch(Exception e)
        {
        	System.out.println(e);
        	return null;
        }
		return wb;
	}
	
	public static boolean saveFile(String fileUrl,String file,String fileName)
	{
		try
		{
			URL url = new URL(fileUrl);
			HttpURLConnection connection=(HttpURLConnection) url.openConnection();
			DataInputStream in=new DataInputStream(connection.getInputStream());
			DataOutputStream out=new DataOutputStream(new FileOutputStream(file+fileName));
			byte[] buffer=new byte[4096];
			int count=0;
			while((count = in.read(buffer))>0)
			{
				out.write(buffer,0,count);
			}
			out.close();
			in.close();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
    }
	
	

	public static String changeType(String value){
		if(isNumeric(value))
		{
			BigDecimal db = new BigDecimal(value);
			String ii = db.toPlainString();
			return ii;
		}
		else
		{
			return value;
		}
	}
	
	public static String deleteLastTwo(String value){
		value = value.substring(0,value.length()-2); 
		return value;
	}
	
	public static void main(String[] args) {
		String sstring = deleteLastTwo("411001.0");
		System.out.println(sstring);
	}
	
	public static boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}
}
