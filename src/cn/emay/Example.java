package cn.emay;

import java.util.HashMap;
import java.util.Map;

import cn.emay.eucp.inter.framework.dto.CustomSmsIdAndMobile;
import cn.emay.eucp.inter.framework.dto.CustomSmsIdAndMobileAndContent;
import cn.emay.eucp.inter.framework.dto.PersonalityParams;
import cn.emay.eucp.inter.http.v1.dto.request.BalanceRequest;
import cn.emay.eucp.inter.http.v1.dto.request.MoRequest;
import cn.emay.eucp.inter.http.v1.dto.request.ReportRequest;
import cn.emay.eucp.inter.http.v1.dto.request.SmsBatchOnlyRequest;
import cn.emay.eucp.inter.http.v1.dto.request.SmsBatchRequest;
import cn.emay.eucp.inter.http.v1.dto.request.SmsPersonalityAllRequest;
import cn.emay.eucp.inter.http.v1.dto.request.SmsPersonalityRequest;
import cn.emay.eucp.inter.http.v1.dto.request.SmsSingleRequest;
import cn.emay.eucp.inter.http.v1.dto.response.BalanceResponse;
import cn.emay.eucp.inter.http.v1.dto.response.MoResponse;
import cn.emay.eucp.inter.http.v1.dto.response.ReportResponse;
import cn.emay.eucp.inter.http.v1.dto.response.SmsResponse;
import cn.emay.util.AES;
import cn.emay.util.GZIPUtils;
import cn.emay.util.JsonHelper;
import cn.emay.util.http.EmayHttpClient;
import cn.emay.util.http.EmayHttpRequestBytes;
import cn.emay.util.http.EmayHttpResponseBytes;
import cn.emay.util.http.EmayHttpResponseBytesPraser;
import cn.emay.util.http.EmayHttpResultCode;

public class Example {

	private static String appId = "EUCP-EMY-SMS1-3C7R0";//请联系销售，或者在页面中 获取
	// 密钥
	private static String secretKey = "03B62C407C6E421A";//请联系销售，或者在页面中 获取
	// 接口地址
	private static String host = "bjmtn.b2m.cn/";//请联系销售获取
	// 加密算法
	private static String algorithm = "AES/ECB/PKCS5Padding";
	// 编码
	private static String encode = "UTF-8";
	// 是否压缩
	private static boolean isGizp = false;
	
	public static void main(String[] args) {
		// appId
		 
		
		// 获取余额
		//getBalance(appId,secretKey,host,algorithm,isGizp,encode);
		// 获取状态报告
		//getReport(appId,secretKey,host,algorithm,isGizp,encode);
		// 获取上行
		//getMo(appId,secretKey,host,algorithm,isGizp,encode);
		// 发送单条短信
		//setSingleSms(appId,secretKey,host,algorithm,"【数金】迎新春、送豪礼\n12.24-1.23 数金一体机特此推出新春优惠价\n原价：10000\n现价：200\n钜惠仅此一月\n抢购热线：15101051469，回复TD退订", null, null, "13552381403",isGizp,encode);
		
		//setSingleSms("【数金】迎新春、送豪礼\n12.24-1.23 数金一体机特此推出新春优惠价\n原价：10000\n现价：200\n钜惠仅此一月\n抢购热线：15101051469，回复TD退订", null, null, "13552381403");
		// 发送批次短信[有自定义SMSID]
		setBatchSms("【数金】迎新春、送豪礼\n12.24-1.23 数金一体机特此推出新春优惠价\n原价：10000\n现价：200\n钜惠仅此一月\n抢购热线：15101051469，详情访问官网：www.dfans.cn 回复TD退订", null, new CustomSmsIdAndMobile[]{new CustomSmsIdAndMobile("1", "15833764731"),new CustomSmsIdAndMobile("2", "18611828040"),new CustomSmsIdAndMobile("3", "15101051469"),new CustomSmsIdAndMobile("4", "13811654821")});
		// 发送批次短信[无自定义SMSID]
		//setBatchOnlySms(appId,secretKey,host,algorithm,"你好今天天气不错，挺风和日丽的", null, new String[]{"18001000000","18001000001"},isGizp,encode);
		// 发送个性短信
		//setPersonalitySms(appId,secretKey,host,algorithm, null, new CustomSmsIdAndMobileAndContent[]{new CustomSmsIdAndMobileAndContent("1", "18001000000","你好今天天气不错，挺风和日丽的"),new CustomSmsIdAndMobileAndContent("2", "18001000001","你好今天天气不错，挺风和日丽的啊")},isGizp,encode);
		// 发送全个性短信
		//setPersonalityAllSms(appId, secretKey, host, algorithm, new PersonalityParams[]{new PersonalityParams("101", "18001000000", "天气不错", "01", null),new PersonalityParams("102", "18001000001", "天气不错1", "02", "2017-12-01 11:00:00")}, isGizp, encode);
	}
	
	/**
	 * 获取余额
	 * @param isGzip 是否压缩
	 */
	private static void getBalance(String appId,String secretKey,String host,String algorithm,boolean isGzip,String encode) {
		System.out.println("=============begin getBalance==================");
		BalanceRequest pamars = new BalanceRequest();
		ResultModel result = request(appId,secretKey,algorithm,pamars, "http://" + host + "/inter/getBalance",isGzip,encode);
		System.out.println("result code :" + result.getCode());
		if("SUCCESS".equals(result.getCode())){
			BalanceResponse response = JsonHelper.fromJson(BalanceResponse.class, result.getResult());
			if (response != null) {
				System.out.println("result data : " + response.getBalance());
			}
		}
		System.out.println("=============end getBalance==================");
	}

	/**
	 * 获取状态报告
	 * @param isGzip 是否压缩
	 */
	private static void getReport(String appId,String secretKey,String host,String algorithm,boolean isGzip,String encode) {
		System.out.println("=============begin getReport==================");
		ReportRequest pamars = new ReportRequest();
		ResultModel result = request(appId,secretKey,algorithm,pamars, "http://" + host + "/inter/getReport",isGzip,encode);
		System.out.println("result code :" + result.getCode());
		if("SUCCESS".equals(result.getCode())){
			ReportResponse[] response = JsonHelper.fromJson(ReportResponse[].class, result.getResult());
			if (response != null) {
				for (ReportResponse d : response) {
					System.out.println("result data : " + d.getExtendedCode() + "," + d.getMobile() + "," + d.getCustomSmsId() + "," + d.getSmsId() + "," + d.getState() + "," + d.getDesc()
							+ "," + d.getSubmitTime() + "," + d.getReceiveTime());
				}
			}
		}
		System.out.println("=============end getReport==================");
	}
	
	/**
	 * 获取上行
	 * @param isGzip 是否压缩
	 */
	private static void getMo(String appId,String secretKey,String host,String algorithm,boolean isGzip,String encode) {
		System.out.println("=============begin getMo==================");
		MoRequest pamars = new MoRequest();
		ResultModel result = request(appId,secretKey,algorithm,pamars, "http://" + host + "/inter/getMo",isGzip,encode);
		System.out.println("result code :" + result.getCode());
		if("SUCCESS".equals(result.getCode())){
			MoResponse[] response = JsonHelper.fromJson(MoResponse[].class, result.getResult());
			if (response != null) {
				for (MoResponse d : response) {
					System.out.println("result data:" + d.getContent()+ "," + d.getExtendedCode() + "," + d.getMobile() + "," + d.getMoTime());
				}
			}
		}
		System.out.println("=============end getMo==================");
	}
	
	/**
	 * 发送单条短信
	 * @param isGzip 是否压缩
	 */
	public static void setSingleSms(String content, String customSmsId, String extendCode, String mobile) {
		System.out.println("=============begin setSingleSms==================");
		SmsSingleRequest pamars = new SmsSingleRequest();
		pamars.setContent(content);
		pamars.setCustomSmsId(customSmsId);
		pamars.setExtendedCode(extendCode);
		pamars.setMobile(mobile);
		ResultModel result = request(appId,secretKey,algorithm,pamars, "http://" + host + "/inter/sendSingleSMS",isGizp,encode);
		System.out.println("result code :" + result.getCode());
		if("SUCCESS".equals(result.getCode())){
			SmsResponse response = JsonHelper.fromJson(SmsResponse.class, result.getResult());
			if (response != null) {
				System.out.println("data : " + response.getMobile() + "," + response.getSmsId() + "," + response.getCustomSmsId());
			}
		}
		System.out.println("=============end setSingleSms==================");
	}
	
	/**
	 * 发送批次短信
	 * @param isGzip 是否压缩
	 */
	private static void setBatchOnlySms(String appId,String secretKey,String host,String algorithm,String content, String extendCode, String[] mobiles,boolean isGzip,String encode) {
		System.out.println("=============begin setBatchOnlySms==================");
		SmsBatchOnlyRequest pamars = new SmsBatchOnlyRequest();
		pamars.setMobiles(mobiles);
		pamars.setExtendedCode(extendCode);
		pamars.setContent(content);
		ResultModel result = request(appId,secretKey,algorithm,pamars, "http://" + host + "/inter/sendBatchOnlySMS",isGzip,encode);
		System.out.println("result code :" + result.getCode());
		if("SUCCESS".equals(result.getCode())){
			SmsResponse[] response = JsonHelper.fromJson(SmsResponse[].class, result.getResult());
			if (response != null) {
				for (SmsResponse d : response) {
					System.out.println("data:" + d.getMobile() + "," + d.getSmsId() + "," + d.getCustomSmsId());
				}
			}
		}
		System.out.println("=============end setBatchOnlySms==================");
	}
	
	/**
	 * 发送批次短信
	 * @param isGzip 是否压缩
	 */
	public static void setBatchSms(String content, String extendCode, CustomSmsIdAndMobile[] customSmsIdAndMobiles) {
		System.out.println("=============begin setBatchSms==================");
		SmsBatchRequest pamars = new SmsBatchRequest();
		pamars.setSmses(customSmsIdAndMobiles);
		pamars.setExtendedCode(extendCode);
		pamars.setContent(content);
		ResultModel result = request(appId,secretKey,algorithm,pamars, "http://" + host + "/inter/sendBatchSMS",isGizp,encode);
		System.out.println("result code :" + result.getCode());
		if("SUCCESS".equals(result.getCode())){
			SmsResponse[] response = JsonHelper.fromJson(SmsResponse[].class, result.getResult());
			if (response != null) {
				for (SmsResponse d : response) {
					System.out.println("data:" + d.getMobile() + "," + d.getSmsId() + "," + d.getCustomSmsId());
				}
			}
		}
		System.out.println("=============end setBatchSms==================");
	}
	
	/**
	 * 发送个性短信
	 * @param isGzip 是否压缩
	 */
	private static void setPersonalitySms(String appId,String secretKey,String host,String algorithm,String extendCode, CustomSmsIdAndMobileAndContent[] customSmsIdAndMobileAndContents,boolean isGzip,String encode) {
		System.out.println("=============begin setPersonalitySms==================");
		SmsPersonalityRequest pamars = new SmsPersonalityRequest();
		pamars.setSmses(customSmsIdAndMobileAndContents);
		pamars.setExtendedCode(extendCode);
		ResultModel result = request(appId,secretKey,algorithm,pamars, "http://" + host + "/inter/sendPersonalitySMS",isGzip ,encode);
		System.out.println("result code :" + result.getCode());
		if("SUCCESS".equals(result.getCode())){
			SmsResponse[] response = JsonHelper.fromJson(SmsResponse[].class, result.getResult());
			if (response != null) {
				for (SmsResponse d : response) {
					System.out.println("data:" + d.getMobile() + "," + d.getSmsId() + "," + d.getCustomSmsId());
				}
			}
		}
		System.out.println("=============end setPersonalitySms==================");
	}
	
	/**
	 * 发送个性短信
	 * @param isGzip 是否压缩
	 */
	private static void setPersonalityAllSms(String appId,String secretKey,String host,String algorithm,PersonalityParams[] customSmsIdAndMobileAndContents,boolean isGzip,String encode) {
		System.out.println("=============begin setPersonalityAllSms==================");
		SmsPersonalityAllRequest pamars = new SmsPersonalityAllRequest();
		pamars.setSmses(customSmsIdAndMobileAndContents);
		ResultModel result = request(appId,secretKey,algorithm,pamars, "http://" + host + "/inter/sendPersonalityAllSMS",isGzip ,encode);
		System.out.println("result code :" + result.getCode());
		if("SUCCESS".equals(result.getCode())){
			SmsResponse[] response = JsonHelper.fromJson(SmsResponse[].class, result.getResult());
			if (response != null) {
				for (SmsResponse d : response) {
					System.out.println("data:" + d.getMobile() + "," + d.getSmsId() + "," + d.getCustomSmsId());
				}
			}
		}
		System.out.println("=============end setPersonalityAllSms==================");
	}
	
	/**
	 * 公共请求方法
	 */
	public static ResultModel request(String appId,String secretKey,String algorithm,Object content, String url,final boolean isGzip,String encode) {
		Map<String, String> headers = new HashMap<String, String>();
		EmayHttpRequestBytes request = null;
		try {
			headers.put("appId", appId);
			headers.put("encode", encode);
			String requestJson = JsonHelper.toJsonString(content);
			System.out.println("result json: " + requestJson);
			byte[] bytes = requestJson.getBytes(encode);
			System.out.println("request data size : " + bytes.length);
			if (isGzip) {
				headers.put("gzip", "on");
				bytes = GZIPUtils.compress(bytes);
				System.out.println("request data size [com]: " + bytes.length);
			}
			byte[] parambytes = AES.encrypt(bytes, secretKey.getBytes(), algorithm);
			System.out.println("request data size [en] : " + parambytes.length);
			request = new EmayHttpRequestBytes(url, encode, "POST", headers, null, parambytes);
		} catch (Exception e) {
			System.out.println("加密异常");
			e.printStackTrace();
		}
		EmayHttpClient client = new EmayHttpClient();
		String code = null;
		String result = null;
		try {
			EmayHttpResponseBytes res = client.service(request, new EmayHttpResponseBytesPraser());
			if(res == null){
				System.out.println("请求接口异常");
				return new ResultModel(code, result);
			}
			if (res.getResultCode().equals(EmayHttpResultCode.SUCCESS)) {
				if (res.getHttpCode() == 200) {
					code = res.getHeaders().get("result");
					if (code.equals("SUCCESS")) {
						byte[] data = res.getResultBytes();
						System.out.println("response data size [en and com] : " + data.length);
						data = AES.decrypt(data, secretKey.getBytes(), algorithm);
						if (isGzip) {
							data = GZIPUtils.decompress(data);
						}
						System.out.println("response data size : " + data.length);
						result = new String(data, encode);
						System.out.println("response json: " + result);
					}
				} else {
					System.out.println("请求接口异常,请求码:" + res.getHttpCode());
				}
			} else {
				System.out.println("请求接口网络异常:" + res.getResultCode().getCode());
			}
		} catch (Exception e) {
			System.out.println("解析失败");
			e.printStackTrace();
		}
		ResultModel re = new ResultModel(code, result);
		return re;
	}

}

