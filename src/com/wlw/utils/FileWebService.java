package com.wlw.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;


public class FileWebService {

	/**
	 * 表单域的信息
	 */
	private Map parameters = null;

	/**
	 * 文件域的详细信息
	 */
	private List fileInfoList = null;

	/**
	 * 允许上传的文件大小
	 */
	private long MAX_SIZE = 10 * 1024 * 1024;
	//private long MAX_SIZE = 10*512;

	/**
	 * 允许上传的文件类型
	 */
	//private String[] allowedExt = new String[] {"pdf","TXT","jpg","jpeg", "gif", "png","doc", "docx"};
	private String[] allowedExt = new String[] {"xlsx","xls"};
	public FileWebService() {
		parameters = new HashMap();
		fileInfoList = new ArrayList();
	}

	/**
	 * @param request
	 * @param response
	 * @param path
	 *            用户设置的保存路径 上传文件并获取表单域及文件域的详细信息
	 * @throws Exception
	 */
	public void upload(HttpServletRequest request,
			HttpServletResponse response, String path) throws Exception {
		/**
		 * 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload
		 */
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		diskFileItemFactory.setSizeThreshold(4096);// 设置上传文件时用于临时存放文件的内存大小,这里是4m.多于的部分将临时存在硬盘
		/**
		 * 采用系统临时文件目录作为上传的临时目录
		 */
		File tempfile = new File(System.getProperty("java.io.tmpdir"));
		diskFileItemFactory.setRepository(tempfile);

		/**
		 * 用以上工厂实例化上传组件 设置最大上传尺寸
		 */
		ServletFileUpload fileUpload = new ServletFileUpload(
				diskFileItemFactory);
		fileUpload.setFileSizeMax(1024*512);  
		fileUpload.setSizeMax(MAX_SIZE);

		/**
		 * 调用FileUpload.settingHeaderEncoding(”UTF-8″)，这项设置可以解决路径或者文件名为乱码的问题。
		 * 设置输出字符集
		 */
		fileUpload.setHeaderEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();
		/**
		 * 从request得到 所有 上传域的列表
		 */
		List fileList = null;
		String requestPath = null;
		try {
			fileList = fileUpload.parseRequest(request);
			// 取得回调路径
			for (Iterator itt = fileList.iterator(); itt.hasNext();) {
				FileItem fileItem = (FileItem) itt.next();
				if (fileItem.isFormField()) {
					if (fileItem.getFieldName().equals("requestPath")) {
						requestPath = fileItem.getString();
						break;
					}
				}
			}
		} catch (FileUploadException e) {
			if (e instanceof SizeLimitExceededException) {

				out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""
						+ request.getContextPath() + "/css/uploadStyle.css\">");
				out.println("<table width=\"400\" class='DetailTable' align=\"center\" valign=\"middle\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
				out.println("<tr >"
						+ "    <td bgcolor=\"red\" class='DetailTableData' align=\"center\" height=\"30\" style=\"font-size:9pt;\"><font color=\"white\"><h3>上传失败</h3></font></td>"
						+ "    </tr>"
						+ "    <tr >"
						+ "    <td  style='border: 1px solid #95D5FF;font-size: 12px;line-height: 20px;color: #000000;' class='DetailTableData'><H4>错误详细信息:</H4>"
						+ "       <font size='4'color=\"red\">文件尺寸超过规定大小:"
						+ MAX_SIZE
						+ "字节<p /></font><br> "
						+ "    </td>"
						+ "    </tr>"
						+ "    <tr>"
						+ "    <td colspan=2 align='center' class='DetailTableData'><input type='button' class='Button2'value='返回' onclick='javascript:history.go(-1);'/\"></td>"
						+ "    </tr>");
				out.println("</table>");
				return;
			}
			e.printStackTrace();
		}
		/**
		 * 没有上传文件
		 */
		if (fileList == null || fileList.size() == 0) {

			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""
					+ request.getContextPath() + "/css/uploadStyle.css\">");
			out.println("<table width=\"400\" class='DetailTable' align=\"center\" valign=\"middle\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr >"
					+ "    <td class='DetailTableData' bgcolor=\"red\" align=\"center\" height=\"30\" style=\"font-size:9pt;\"><font color=\"white\"><h3>上传失败</h3></font></td>"
					+ "    </tr>"
					+ "    <tr >"
					+ "    <td style='border: 1px solid #95D5FF;font-size: 12px;line-height: 20px;color: #000000;'	 class='DetailTableData'><H4>错误详细信息:</H4>"
					+ "       <font size='4'color=\"red\">请选择上传文件!!</font><br> "
					+ "    </td>"
					+ "    </tr>"
					+ "    <tr>"
					+ "    <td colspan=2 align='center' class='DetailTableData'><input type='button' class='Button2'value='返回' onclick='javascript:history.go(-1);'/\"></td>"
					+ "    </tr>");
			out.println("</table>");

			return;
		}
		/**
		 * 得到所有上传的文件 对文件域操作 并保存每个文件的详细信息
		 */
		ArrayList saveFiles = new ArrayList();
		Iterator fileItr = fileList.iterator();
		// Map fileInfo = null;
		while (fileItr.hasNext()) {
			FileItem fileItem = null;
			long size = 0;
			String userPath = null;
			String serverPath = null;
			String fileName = null;
			String fileExt = null;
			fileItem = (FileItem) fileItr.next();
			boolean saveFlag = true;
			/**
			 * 忽略简单form字段而不是上传域的文件域(<input type="text" />等)
			 */
			if (!fileItem.isFormField()) {

				/**
				 * 得到文件的详细信息 客户端完整路径：userPath 服务器端完整路径：serverPath 大小：size
				 * 文件名：fileName 扩展名：fileExt
				 * 
				 */
				userPath = fileItem.getName();
				size = fileItem.getSize();
				if ("".equals(userPath) || size == 0) {
					out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""
							+ request.getContextPath()
							+ "/css/uploadStyle.css\">");
					out.println("<table width=\"400\" align=\"center\"  class='DetailTable'  valign=\"middle\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
					out.println("<tr >"
							+ "    <td bgcolor=\"red\" class='DetailTableData' align=\"center\" height=\"30\" style=\"font-size:9pt;\"><font color=\"white\"><h3>上传失败</h3></font></td>"
							+ "    </tr>"
							+ "    <tr >"
							+ "    <td  class='DetailTableData' style='border: 1px solid #95D5FF;font-size: 12px;line-height: 20px;color: #000000;'><H4>错误详细信息:</H4>"
							+ "       <font size='4'color=\"red\">请选择上传文件!!</font><br> "
							+ "    </td>"
							+ "    </tr>"
							+ "    <tr>"
							+ "    <td colspan=2 align='center' class=\"DetailTableData\"><input type='button' class='Button2'value='返回' onclick='javascript:history.go(-1);'/\"></td>"
							+ "    </tr>");
					out.println("</table>");
					saveFlag = false;
					return;
				}
				fileName = userPath.substring(userPath.lastIndexOf("\\") + 1);
				if(fileName.toLowerCase().indexOf("jsp".toLowerCase())!=-1)
					return;
				fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);

				/**
				 * 文件类型是否合法
				 */

				System.out.println("fileExt.toLowerCase()="+fileExt.toLowerCase());
				
				int allowFlag = 0;
				int allowedExtCount = allowedExt.length;
				for (; allowFlag < allowedExtCount; allowFlag++) {
					if (allowedExt[allowFlag].toLowerCase().equals(
							fileExt.toLowerCase()))
						break;
				}
				System.out.println("allowFlag="+fileExt.toLowerCase());
				if (allowFlag == allowedExtCount) {

					out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""
							+ request.getContextPath()
							+ "/css/uploadStyle.css\">");
					out.println("<table width=\"400\"  class='DetailTable'  align=\"center\" valign=\"middle\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
					out.println("<tr >"
							+ "    <td bgcolor=\"red\" class='DetailTableData' align=\"center\" height=\"30\" style=\"font-size:9pt;\"><font color=\"white\"><h3>上传失败</h3></font></td>"
							+ "    </tr>"
							+ "    <tr >"
							+ "    <td class='DetailTableData' style='border: 1px solid #95D5FF;font-size: 12px;line-height: 20px;color: #000000;'><H4>错误详细信息:</H4>"
							+ "<span align='left'><font size='4'color=\"red\">上传失败，请上传以下类型的文件!!<p />");
					for (allowFlag = 0; allowFlag < allowedExtCount; allowFlag++)
						out.println("*." + allowedExt[allowFlag].toLowerCase()
								+ "&nbsp;&nbsp;&nbsp;");

					out.println("</font></span> "
							+ "    </td>"
							+ "    </tr>"
							+ "    <tr>"
							+ "    <td colspan=2 align='center' class='DetailTableData'><input type='button' class='Button2'value='返回' onclick='javascript:history.go(-1);'/\"></td>"
							+ "    </tr>");
					out.println("</table>");
					saveFlag = false;
					return;
				}
				if (saveFlag) {
					saveFiles.add(fileItem);
				}
			}
		}
//		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""
//				+ request.getContextPath() + "/css/uploadStyle.css\">");
//		out.println("<table width=\"500\" hight='30' align=\"center\" valign=\"middle\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\" class='DetailTable' >");
//		out.println("<tr >"
//				+ "    <td bgcolor=\"red\" align=\"center\" height=\"30\" class='DetailTableData' style=\"font-size:9pt;\"><font color=\"white\"><h3>"
//				+ saveFiles.size() + "个文件上传成功</h3></font></td>" + "    </tr>");
		// 保存文件
		for (Iterator itm = saveFiles.iterator(); itm.hasNext();) {
			FileItem itemm = (FileItem) itm.next();
			String userPath = itemm.getName();
			String fileName = userPath
					.substring(userPath.lastIndexOf("\\") + 1);
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
			for(int m=0;m<fileName.length();m++){
				int k=fileName.charAt(m);
				//文件名里面有中文的处理
				if(19968 <= k && k <40623){
					//fileName = String.valueOf(Math.random()*100000+k)+"."+fileExt;
					fileName = "."+fileExt;
					break;
				}
			}
			

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			String fileData = String.valueOf(calendar.get(Calendar.YEAR) + "-"
					+ (calendar.get(Calendar.MONTH) + 1));

			// 文件存放的目录
			File tempDirPath = new File(
					request.getSession()
					.getServletContext().getRealPath("/")
					+
					"/upload/"
					+ "/"
					+ fileData
					+ "/");
			if (!tempDirPath.exists()) {
				tempDirPath.mkdirs();
			}

			String saveName = new Date().getTime() + Math.random() * 10000
					+ fileName;
			BufferedInputStream bin = new BufferedInputStream(
					itemm.getInputStream());
			BufferedOutputStream bout = new BufferedOutputStream(
					new FileOutputStream(
							new File(tempDirPath + "/" + saveName)));
			Streams.copy(bin, bout, true);
			String serverPath = tempDirPath + "/" + saveName;
			String type = request.getParameter("type");
			String imptype = request.getParameter("imptype");
			
			out.println("fileName="+fileData+ "/"+saveName);
			

			// 封装信息返回给用户
			FileUploadInfo fileInfo = new FileUploadInfo();
			fileInfo.setFileExt(fileExt);
			fileInfo.setFileName(fileName);
			fileInfo.setFileSize(itemm.getSize());
			fileInfo.setLabelName(itemm.getFieldName());
			fileInfo.setServerPath(serverPath);
			fileInfo.setUserPath(userPath);
			fileInfo.setUploadTime(fileData);
			fileInfoList.add(fileInfo);

		}
//		out.println("    <tr>"
//				+ "    <td colspan=2 align='center' class='DetailTableData'><input type='button' class='Button2'value='返回' onclick='javascript:history.go(-1);'/\"></td>"
//				+ "    </tr>");
//		out.println("</table>");

	}

	/**
	 * 文件名不能超过17个汉字 而且在IE6下存在bug
	 */
	public void downloadI18N(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;

		String filePath = request.getParameter("filePath");
		String fileName = request.getParameter("fileName");
		System.out.println(fileName);
		try {
			long fileLength = new File(filePath).length();

			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			response.setHeader("Content-Length", String.valueOf(fileLength));

			bis = new BufferedInputStream(new FileInputStream(filePath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}

	/**
	 * 支持中文,文件名长度无限制
	 * 
	 */
	public void download(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;

		String filePath = request.getParameter("filePath");
		String fileName = request.getParameter("fileName");
		System.out.println(fileName);
		try {
			long fileLength = new File(filePath).length();

			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GBK"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));

			bis = new BufferedInputStream(new FileInputStream(filePath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}

	public List getFileInfoList() {
		return fileInfoList;
	}

	public void setFileInfoList(List fileInfoList) {
		this.fileInfoList = fileInfoList;
	}

	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}

	public String[] getAllowedExt() {
		return allowedExt;
	}

	public void setAllowedExt(String[] allowedExt) {
		this.allowedExt = allowedExt;
	}

	public long getMAX_SIZE() {
		return MAX_SIZE;
	}

	public void setMAX_SIZE(long max_size) {
		MAX_SIZE = max_size;
	}

}
