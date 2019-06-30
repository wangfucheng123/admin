package com.dfans.utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class UploadImgs {
	/**
	 * 
	 * @param request
	 * @param imgPath
	 * @return
	 * @throws IOException
	 */
	public static String uploadImgs(HttpServletRequest request,String imgPath,String imgName) throws IOException {
		String img = "";
		StringBuilder imgs = new StringBuilder();
		imgPath = Const.FILEPATHIMG + imgPath;
	
		File file = new File(Const.FILEPATH +imgPath);
		if(!file.isDirectory())
		{
			file.mkdirs();
		}
        
        MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
        //获取multiRequest 中所有的文件名
        Iterator<String> iter=multiRequest.getFileNames();
          
        while(iter.hasNext())
        {
            String name = iter.next().toString();
        	//一次遍历所有文件
             MultipartFile multipartFile=multiRequest.getFile(name);
             if(multipartFile!=null)
             {
                 String path = imgPath+imgName+"-"+DateUtils.getDays()+"-"+Tools.getRandomNum()+".png";
                 String path1 = Const.FILEPATH + path;
                 //上传
                 multipartFile.transferTo(new File(path1));
                 ImageUtils.thumbnailImage(path1, 100, 150);
                 
                 imgs.append(path).append(",");
             }
              
        }
        img = imgs.toString();
		if(!StringUtil.isEmpty(img)){
			img = img.substring(0, img.length()-1);
		}
        return img;
	} 
	
	/**
	 * 
	 * @param multipartFile
	 * @param imgPath
	 * @return
	 * @throws IOException
	 */
	public static String uploadImgs(MultipartFile multipartFile,String imgPath,String imgName) throws IOException {
		String img = "";
		StringBuilder imgs = new StringBuilder();
		imgPath = Const.FILEPATHIMG + imgPath;
		
		File file = new File(Const.FILEPATH + imgPath);
		if(!file.isDirectory())
		{
			file.mkdirs();
		}
		
		if(multipartFile!=null)
		{
			String path = imgPath+imgName+"-"+DateUtils.getDays()+"-"+Tools.getRandomNum()+".png";
			String path1 = Const.FILEPATH + path;
			//上传
			multipartFile.transferTo(new File(path1));
			ImageUtils.thumbnailImage(path1, 100, 150);
			
			imgs.append(path);
		}
		
		img = imgs.toString();
		
		return img;
	} 
	
	/**  
	 * 删除单个文件  
	 * @param   fileName    被删除文件的文件名  
	 * @return 单个文件删除成功返回true,否则返回false  
	 */  
	public static boolean deleteImg(String fileName) throws IOException{
		boolean success = true;
		System.out.println("删除单个文件  :"+fileName);
		if(!StringUtil.isEmpty(fileName)){
			fileName = Const.FILEPATH + fileName;
			try {
				File file = new File(fileName);
				
				if(file.exists() && file.isFile()) {
					file.delete();//"删除单个文件成功！"
				}
				
				String p = file.getPath();
				if(!StringUtil.isEmpty(p)){
					File tempFile = new File(p.substring(0,p.lastIndexOf(File.separator)) + File.separator + ImageUtils.DEFAULT_PREVFIX +file.getName());
					if(tempFile.exists() && tempFile.isFile()) {
						tempFile.delete();//"删除单个文件成功！"
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				success = false;
			}
		}
		return success;
	}
	
	/**
	 * 判断文件是不是图片
	 * @param file
	 * @return
	 */
	public static boolean isImage(File file)  
    {  
        boolean flag = true;   
        try{  
	        ImageInputStream is = ImageIO.createImageInputStream(file);  
	        if(null == is) {  
	        	flag = false;  
	        }  
	        is.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return flag;  
    }  
}