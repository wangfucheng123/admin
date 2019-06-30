package com.dfans.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dfans.dao.SJNoticeMapper;
import com.dfans.dao.UserFeedBackMapper;
import com.dfans.dao.UserMapper;
import com.dfans.model.LogModel;
import com.dfans.model.SJNotice;
import com.dfans.model.User;
import com.dfans.model.UserFeedBack;
import com.dfans.utils.LogUtil;
import com.dfans.utils.SendEmail;
import com.dfans.utils.shortmsgutils.SingletonClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value = "/notice")
public class SJ_Notice {
	@Autowired
	private SJNoticeMapper notice;
	@Autowired
	private UserMapper userDao;
	@Autowired
	private UserFeedBackMapper feedBack;

	private static Gson gson = new GsonBuilder().create();
	private Log logger = LogFactory.getLog(this.getClass());  
	@RequestMapping({ "/getNotices" })
	public void getNotices(HttpServletResponse response, HttpServletRequest req,String flag, SJNotice model) {
		Map<String ,Object> map=new HashMap<String ,Object>();
		map.put("title",  model.getTitle());
		map.put("sort",  model.getSort()); 
		/**if (!StringUtils.isEmpty(model.getTitle())) {
			map.put(key, value)
			pSql.append(" and title like '%" + model.getTitle() + "%' ");
		}
		if (!StringUtils.isEmpty(model.getSort())) {
			pSql.append(" and sort like '%" + model.getSort() + "%' ");
		}pSql.append(" and createtime>'" + URLDecoder.decode(URLDecoder.decode(time, "utf-8"),"utf-8") + "' ");
		pSql.append(" order by createTime desc ");*/
		List<SJNotice> cts = notice.getNotices(map); 
		if (StringUtils.isEmpty(flag)) {
		 map.put("pg", "pg");
		 map.put("page", Integer.parseInt(req.getParameter("page")) * 10);
			//pSql.append(" order by createTime desc limit " + Integer.parseInt(req.getParameter("page")) * 10 + ",10 ");
		}else{
			 map.put("flag", "flag");//终端公告消息
		}
		List<SJNotice> list = notice.getNotices(map);
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("count", cts.size());
		rmap.put("content", list);
		retJosn(response, rmap); 
	}
	@RequestMapping({ "/logs" })
	public void logs(HttpServletResponse response, HttpServletRequest req, SJNotice model) { 
		List<LogModel> cts = notice.logs("");
		List<LogModel> list = notice.logs(" order by CreateTime desc limit " + Integer.parseInt(req.getParameter("page")) * 10 + ",10 ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", cts.size());
		map.put("content", list);
		retJosn(response, map);
		logger.info("");
	}
	/**
	 * 终端
	 * @param response
	 * @param req
	 * @param time
	 * @param sort
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping({ "/getNoticesByTime" })
	public void getNoticesByTime(HttpServletResponse response, HttpServletRequest req, String time,String sort) throws UnsupportedEncodingException {
		Map<String ,Object> map=new HashMap<String ,Object>();
		if (!StringUtils.isEmpty(time)) {
			map.put("createtime", time); 
		}  
		if (!StringUtils.isEmpty(sort)) {
			map.put("sort", sort); 
		} 
		map.put("ByTime", "ByTime"); 
		List<SJNotice> list = notice.getNotices(map); 
		retJosn(response, list);
	}
	@RequestMapping({ "/getNoticesBySort" })
	public void getNoticesBySort(HttpServletResponse response, HttpServletRequest req, String sort) {
		Map<String ,Object> map=new HashMap<String ,Object>();
		if (!StringUtils.isEmpty(sort)) {
			map.put("sort", sort);  
			map.put("ByTime", "ByTime");
		}  
		List<SJNotice> list = notice.getNotices(map); 
		retJosn(response, list);
	}
	@RequestMapping({ "/saveNotice" })
	public void saveNotice(HttpServletRequest request, HttpServletResponse response, SJNotice model) {
		model.setStatus("0");
		model.setContent(model.getContent());
		int res=notice.insert(model);
		String m="";
		String msg = "";
		List<User> list = userDao.getList("");
		if(res>0){
			String flag=model.getSort();
			if ("email".equals(flag)) {
				boolean result = false;
				for (User u : list) {
					if (!StringUtils.isEmpty(u.getEmail())) {
						 try {
							SendEmail.sendEmail(u.getEmail(), model.getContent(), model.getTitle());
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							msg = "已发送！";
					}
				}
				if (result) {
				
				}
				m="发送邮件";
			} else if ("message".equals(flag)) {
				StringBuffer phones=new StringBuffer();
				for (User u : list) {
					if (!StringUtils.isEmpty(u.getPhone())) {
						phones.append(u.getPhone()+",");
					}
				} 
				massTexting(phones.toString(),model.getContent());
				m="发送短信";
			}
			String respStr = "";
			if (StringUtils.isNotBlank(msg)) {
				respStr = "{\"flag\":\"true\",\"msg\":\"" + msg + "\"}";
			} else {
				respStr = "{\"flag\":\"false\",\"msg\":\"发送失败\"}";
			}  
			retJosn(response, respStr);
		}
		LogModel record = LogUtil.recordLog(request, Thread.currentThread().getStackTrace()[1].getClassName(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), "保存公告");
		notice.logRecord(record);
	}

	@RequestMapping({ "/getNotice" })
	public void getNotice(HttpServletResponse response, SJNotice model) {
		SJNotice n = notice.selectByPrimaryKey(model.getId());
		retJosn(response, n);
	}

	@RequestMapping({ "/delNotice" })
	public void removeCData(HttpServletRequest request, HttpServletResponse response, String id,String flag) {
		String[] ids = new String[] {};
		if (!StringUtils.isEmpty(id)) {
			ids = id.split(",");
		}
		for (String k : ids) {
			if (!StringUtils.isEmpty(k)) {
				if("c".equals(flag)){
					notice.cancelByPrimaryKey(Integer.parseInt(k));
				}else{
					notice.deleteByPrimaryKey(Integer.parseInt(k));
				}
			}
		}
		LogModel record=LogUtil.recordLog(request, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), "删除公告"); 
		notice.logRecord(record);
	} 
	 
	@RequestMapping({ "/getFeedBacks" })
	public void getFeedBacks(HttpServletResponse response, HttpServletRequest req, UserFeedBack model) {
		StringBuilder pSql = new StringBuilder();
		if (!StringUtils.isEmpty(model.getContent())) {
			pSql.append(" and content like '%" + model.getContent() + "%' ");
		}
		List<UserFeedBack> cts = feedBack.selectList(pSql.toString());
		pSql.append(" order by fb.create_date desc limit " + Integer.parseInt(req.getParameter("page")) * 10 + ",10 ");
		List<UserFeedBack> list = feedBack.selectList(pSql.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", cts.size());
		map.put("content", list);
		retJosn(response, map);
	}
	@RequestMapping({ "/fsdx" })
	@ResponseBody
	public static void fsdx(String content,String phones) { 
		MessageController.getNoticeMsg(phones,"【数金】"+content);
			//massTexting(phones,content);   
	}
	public static void retJosn(HttpServletResponse response, Object obj) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(gson.toJson(obj));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean sendEmail(String to, String title, String content) {
		boolean flag = false;
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.cssweb.com.cn");

		properties.put("mail.smtp.auth", "true");
		try {
			Session session = javax.mail.Session.getDefaultInstance(properties, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("shujin@cssweb.com.cn", "qsc!123ZXC");
				}
			});
			MimeMessage mailMessage = new MimeMessage(session);
			mailMessage.setFrom(new InternetAddress("shujin@cssweb.com.cn"));
			mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			mailMessage.setSubject(title, "UTF-8");
			mailMessage.setSentDate(new Date());
			mailMessage.setContent(content, "text/html; charset=utf-8");
			Transport transport = session.getTransport("smtp");
			transport.connect("shujin@cssweb.com.cn", "A11111");
			Transport.send(mailMessage, mailMessage.getAllRecipients());
			transport.close();
			flag = true;
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return flag;
	}
	/**
	 * 群发短信
	 */
	public static void massTexting(String phones,String text){
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(MSG_URL);  
		httpPost.setHeader("Content-Type","application/x-www-form-urlencoded"); 
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("phone",phones));  
	    params.add(new BasicNameValuePair("msg","【数金】"+text));
	    try{
	        httpPost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
	    }catch (Exception e) {}
	    try {
			HttpResponse httpRes=httpClient.execute(httpPost); 
			System.out.println(EntityUtils.toString(httpRes.getEntity()));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static final String MSG_URL="http://sj.dfans.cn/admin/view/shortMsg/getNoticeMsg";
	public static void main(String[] args) throws UnsupportedEncodingException, RemoteException { 
	
	}
}
