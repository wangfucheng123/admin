package com.dfans.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.dfans.dao.SJPagetotalMapper;
import com.dfans.model.SJPagetotal;

public class ParseNginxLog {
	@Autowired
	private SJPagetotalMapper pagetotalMapper;
	static String logPath = "cd /opt/resin/log";

	public String turnDate(String dateStr) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss Z", Locale.ENGLISH);
		Date date = formatter.parse(dateStr);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	/**
	 * 解析url 参数
	 * 
	 * @param str
	 * @param name
	 * @return
	 */
	public static String getParameterRex(String str, String name) {
		String rex = "(?:^|\\?|&)" + name + "=([^&]*)(?:&|$)";
		Pattern p = Pattern.compile(rex);
		Matcher m = p.matcher(str);
		String param = "";
		while (m.find()) {
			param = m.group();
			if (StringUtils.isNotBlank(param)) {
				try {
					param = param.substring(param.indexOf("=") + 1, param.lastIndexOf("&"));
				} catch (Exception e) {
					e.printStackTrace();
					param = "";
				}
			}
		}
		return param;
	}

	public void parseLog() { 
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		ProcessBuilder pb = new ProcessBuilder("./" + "catlog.sh", formatter.format(new Date()),
				format.format(new Date()));
		pb.directory(new File("/opt/nginx_logs"));
		int runningStatus = 0; 
		try {
			Process p = pb.start();
			try {
				runningStatus = p.waitFor();
			} catch (InterruptedException e) {
			}

		} catch (IOException e) {
		}
		if (runningStatus != 0) {
		}
	}

	public void getLogs() throws FileNotFoundException, IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		RandomAccessFile raf = new RandomAccessFile(
				"/opt/nginx_logs" + System.getProperty("file.separator") + format.format(new Date()) + ".log", "rw");
		Integer row = 0;
		raf.seek(row);// 移动文件指针位置
		String line = null;
		int i = row;
		Map<String, SJPagetotal> map = new HashMap<String, SJPagetotal>();
		while ((line = raf.readLine()) != null) {
			Pattern p = Pattern.compile("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)");
			Matcher m = p.matcher(line);
			String ipv4 = "";
			if (m.find()) {
				ipv4 = m.group();
			}
			String timeStr = "";
			timeStr = line.substring(line.indexOf("[") + 1, line.lastIndexOf("]"));
			String time = "";
			Pattern pt = Pattern.compile("(\\d{2})/([^&]*)/\\d{4}:\\d{2}:\\d{2}:\\d{2}");
			Matcher mt = pt.matcher(timeStr);
			while (mt.find()) {
				// 找到时间串
				time = mt.group();
				SimpleDateFormat sdf = new SimpleDateFormat("d/MMM/yyyy:K:m:s", Locale.ENGLISH);
				SimpleDateFormat sdfnew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date d2 = null;
				try {
					d2 = sdf.parse(time.trim());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				time = sdfnew.format(d2);
			}
			i = (int) raf.getFilePointer();// 记录当前读取位置
			// 获取参数
			String pid = getParameterRex(line, "code");
			SJPagetotal model = new SJPagetotal();
			if (StringUtils.isNotBlank(pid.trim())) {
				model.setPid(pid);
				model.setcDate(time);
				model.setIp(ipv4);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = null;
				try {
					date = sdf.parse(time);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pagetotalMapper.saveData(model);
			}
		}
		raf.close();
	}

}
