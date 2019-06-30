package cn.emay.util.http;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 解析自定义响应的解析器
 * 
 * @author Frank
 *
 */
public class EmayHttpResponseStringPraser implements EmayHttpResponsePraser<EmayHttpResponseString>{

	public EmayHttpResponseString prase(String charSet,EmayHttpResultCode resultCode,int httpCode, Map<String, String> headers, List<String> cookies, ByteArrayOutputStream outputStream) {
		String st = null;
		try {
			if(outputStream != null){
				byte[] resultBytes = outputStream.toByteArray();
				st = new String(resultBytes, charSet);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new EmayHttpResponseString(charSet, resultCode, httpCode, headers, cookies, st);
	}

	
}
