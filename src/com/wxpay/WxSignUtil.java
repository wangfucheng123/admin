package com.wxpay;

import com.wxpay.config.WxPayConfig;
import com.wxpay.utils.MD5;
import com.wxpay.utils.XMLUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jdom.JDOMException;
import java.io.IOException;
import java.util.*;

public class WxSignUtil {
    /**
     * content 是调用微信unifiedorder接口返回的xml字符串串
     * 返回的结果是将调用微信支付的签名、时间戳添加到发送给客户端的xml中。
     * */
    public static Map mkSign(String content) throws JDOMException, IOException {
        Map<String,Object> map= XMLUtil.doXMLParse(content);
        List<NameValuePair> signParams = new LinkedList<NameValuePair>();
        signParams.add(new BasicNameValuePair("appid", WxPayConfig.app_id));
        signParams.add(new BasicNameValuePair("noncestr", (String) map.get("nonce_str")));
        signParams.add(new BasicNameValuePair("package", "prepay_id="+map.get("prepay_id")));
        signParams.add(new BasicNameValuePair("partnerid", WxPayConfig.partner));
        signParams.add(new BasicNameValuePair("prepayid", (String) map.get("prepay_id")));
        String signTimestamp=String.valueOf(System.currentTimeMillis() / 1000);
        signParams.add(new BasicNameValuePair("timestamp", signTimestamp));
        String sign = genAppSign(signParams);
        //
        map.put("mobile_sign",sign);
        map.put("sign_timestamp",signTimestamp);
        return map;
    }

    /**
     * 生成调用微信签名
     * */
    private static String genAppSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(WxPayConfig.partner_key);
        String appSign = MD5.getMessageDigest(sb.toString().getBytes());
        return appSign;
    }
}
