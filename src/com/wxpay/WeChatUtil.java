package com.wxpay;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.wxpay.config.WxPayConfig;
import com.wxpay.utils.MD5Util;
import com.wxpay.utils.TenpayUtil;

public class WeChatUtil {
    
    private static final Logger logger = Logger.getLogger(WeChatUtil.class);
    
    /**
     * 统一下单
     * 获得PrePayId
     * @param body   商品或支付单简要描述
     * @param out_trade_no 商户系统内部的订单号,32个字符内、可包含字母
     * @param total_fee  订单总金额，单位为分
     * @param IP    APP和网页支付提交用户端ip
     * @param notify_url 接收微信支付异步通知回调地址
     * @param openid 用户openId
     * @throws IOException
     */
    public static String unifiedorder(String body,String out_trade_no,Integer total_fee,String ip,String notify_url,String openId)throws IOException {
        /**
         * 设置访问路径
         */
        HttpPost httppost = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");
        String nonce_str = getNonceStr();//随机数据
        SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
        /**
         * 组装请求参数
         * 按照ASCII排序
         */
        parameters.put("appid",WxPayConfig.app_id );
        parameters.put("body", body);
        parameters.put("mch_id", WxPayConfig.partner );
        parameters.put("nonce_str", nonce_str);
        parameters.put("notify_url", notify_url);
        parameters.put("openid", openId);
        parameters.put("out_trade_no", out_trade_no);        
        parameters.put("spbill_create_ip", ip);
        parameters.put("total_fee",total_fee.toString() );
        parameters.put("trade_type",WxPayConfig.grant_type );        
        String sign = WxSign.createSign(parameters, WxPayConfig.partner_key);

        /**
         * 组装XML
         */
        StringBuilder sb = new StringBuilder("");
        sb.append("<xml>");
        setXmlKV(sb,"appid",WxPayConfig.app_id);
        setXmlKV(sb,"body",body);
        setXmlKV(sb,"mch_id",WxPayConfig.partner);
        setXmlKV(sb,"nonce_str",nonce_str);
        setXmlKV(sb,"notify_url",notify_url);
        setXmlKV(sb,"openid",openId);
        setXmlKV(sb,"out_trade_no",out_trade_no);
        setXmlKV(sb,"spbill_create_ip",ip);
        setXmlKV(sb,"total_fee",total_fee.toString());
        setXmlKV(sb,"trade_type",WxPayConfig.grant_type);
        setXmlKV(sb,"sign",sign);
        sb.append("</xml>");

        //StringEntity reqEntity = new StringEntity(new String (sb.toString().getBytes("UTF-8"),"ISO8859-1"));//这个处理是为了防止传中文的时候出现签名错误
        StringEntity reqEntity = new StringEntity(sb.toString(),"UTF-8");//这个处理是为了防止传中文的时候出现签名错误
        
        httppost.setEntity(reqEntity);
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(httppost);
        String strResult = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
        return strResult;

    }


    //获得随机字符串
    public static String getNonceStr(){
         Random random = new Random();
         return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
    }

    //插入XML标签
    public static StringBuilder setXmlKV(StringBuilder sb,String Key,String value){
        sb.append("<");
        sb.append(Key);
        sb.append(">");

        sb.append(value);

        sb.append("</");
        sb.append(Key);
        sb.append(">");

        return sb;
    }

    //解析XML  获得 PrePayId
    public static String getPrePayId(String xml){
        int start = xml.indexOf("<prepay_id>");
        int end = xml.indexOf("</prepay_id>");
        if(start < 0 && end < 0){
            return null;
        }
        return xml.substring(start + "<prepay_id>".length(),end).replace("<![CDATA[","").replace("]]>","");
    }
    
    //商户订单号
    public static String getOut_trade_no(){
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return df.format(new Date()) + TenpayUtil.buildRandom(7);
    }
    
    //时间戳
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
    
    //随机4位数字
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }
    
    public static String inputStream2String(InputStream inStream, String encoding){
         String result = null;
         try {
         if(inStream != null){
          ByteArrayOutputStream outStream = new ByteArrayOutputStream();
          byte[] tempBytes = new byte[1024];
          int count = -1;
          while((count = inStream.read(tempBytes, 0, 1024)) != -1){
            outStream.write(tempBytes, 0, count);
          }
          tempBytes = null;
          outStream.flush();
          result = new String(outStream.toByteArray(), encoding);
         }
         } catch (Exception e) {
         result = null;
         }
         return result;
        }
    
    public static void main(String[] args) {
    	try {
    	StringEntity reqEntity = new StringEntity(new String("<xml><appid>wxeca53549df2ac6f3</appid><body>xf20170502121616258</body><mch_id>1369286902</mch_id><nonce_str>1b388c8b7c863fde3f559142fdc123b0</nonce_str><notify_url>http://xtrh.bjlhxt.com/wlwInter/wlw/wx/notifyUrl</notify_url><openid>ota8Av0W2nWSirS4qPm1K_f2ojCg</openid><out_trade_no>xf20170502121616258</out_trade_no><spbill_create_ip>218.241.251.228</spbill_create_ip><total_fee>100</total_fee><trade_type>JSAPI</trade_type><sign>35D5B040B75175410BA6B9E3EB89274D</sign></xml>".getBytes("UTF-8"),"ISO8859-1"));//这个处理是为了防止传中文的时候出现签名错误
    	HttpPost httppost = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");        
    	httppost.setEntity(reqEntity);
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(httppost);
        String strResult = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
        try {
    	 unifiedorder("test",getOut_trade_no(),100,"192.168.0.1","http://xtrh.bjlhxt.com/wlwInter/wlw/wx/notifyUrl","ota8Av0W2nWSirS4qPm1K_f2ojCg");
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
}
