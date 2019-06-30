package com.wlw.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUpLoad extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public FileUpLoad()
	{}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		try
		{
			FileWebService fws = new FileWebService();
			//String[] allowedExt = new String[]{"RAR","CHM","TXT","jpg","jpeg", "gif", "png","txt","xls","doc", "docx", "mp3", "wma", "m4a"};
			//String[] allowedExt = new String[]{"pdf","TXT","jpg","jpeg", "gif", "png","doc", "docx"};
			String[] allowedExt = new String[] {"xls","xlsx","jpeg","gif","png"};
			String requestPath = (String) request.getAttribute("requestPath");
			Map map=request.getParameterMap();
			fws.setAllowedExt(allowedExt);
			
			fws.upload(request, response, "d:/dddd/");
			request.setAttribute("fileInfoList", fws.getFileInfoList());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
}


	
	public void init() throws ServletException
	{
		String uploadPath = getServletContext().getRealPath("/") + "ImagesUploaded\\";
		File f = new File(uploadPath);
		if(!f.exists())
		{
			f.mkdir();
		}
}
	
	
	
	
	
	

}
