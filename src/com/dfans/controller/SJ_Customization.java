package com.dfans.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/customization")
public class SJ_Customization {
	@RequestMapping({ "/downLoad" })
	public void downLoad(String fileName,HttpServletResponse response) {
		try {
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="+fileName+".xls");
			response.setContentType("application/msexcel");
			OutputStream output = response.getOutputStream();
			downLoadFile(fileName,output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void downLoadFile(String fileName,OutputStream output){
		FileInputStream fis;
		try {
			fis = new FileInputStream(FILE_PATH+fileName+".xls");
			byte [] buffer=new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			output.write(buffer);
			output.flush();
			output.close();
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	private  static final String FILE_PATH ="/opt/";
}
