package cn.emay.util.http;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

/**
 * 解析自定义响应的解析器
 * 
 * @author Frank
 *
 */
public class EmayHttpResponseBytesPraser implements EmayHttpResponsePraser<EmayHttpResponseBytes>{

	public EmayHttpResponseBytes prase(String charSet,EmayHttpResultCode resultCode,int httpCode, Map<String, String> headers, List<String> cookies, ByteArrayOutputStream outputStream) {
		return new EmayHttpResponseBytes(charSet, resultCode, httpCode, headers, cookies, outputStream == null ? null : outputStream.toByteArray());
	}

	
}
