package com.wxpay;

public class WxPayReturnData {  
    //业务结果  
    private String return_code;  
    //消息  
    private String return_msg;  
            //公众号ID  
            private String appid;  
            //商户ID  
            private String mch_id;  
            //随机支付串  
            private String nonce_str;  
            //签名  
            private String sign;  
            //业务结果  
            private String result_code;  
            //预支付ID  
            private String prepay_id;  
            //交易类型  
            private String trade_type;  
      
            public String getReturn_code() {  
                    return return_code;  
            }  
            public void setReturn_code(String return_code) {  
                    this.return_code = return_code;  
            }  
            public String getReturn_msg() {  
                    return return_msg;  
            }  
            public void setReturn_msg(String return_msg) {  
                    this.return_msg = return_msg;  
            }  
            public String getAppid() {  
                    return appid;  
            }  
            public void setAppid(String appid) {  
                    this.appid = appid;  
            }  
            public String getMch_id() {  
                    return mch_id;  
            }  
            public void setMch_id(String mch_id) {  
                    this.mch_id = mch_id;  
            }  
            public String getNonce_str() {  
                    return nonce_str;  
            }  
            public void setNonce_str(String nonce_str) {  
                    this.nonce_str = nonce_str;  
            }  
            public String getSign() {  
                    return sign;  
            }  
            public void setSign(String sign) {  
                    this.sign = sign;  
            }  
            public String getResult_code() {  
                    return result_code;  
            }  
            public void setResult_code(String result_code) {  
                    this.result_code = result_code;  
            }  
            public String getPrepay_id() {  
                    return prepay_id;  
            }  
            public void setPrepay_id(String prepay_id) {  
                    this.prepay_id = prepay_id;  
            }  
            public String getTrade_type() {  
                    return trade_type;  
            }  
            public void setTrade_type(String trade_type) {  
                    this.trade_type = trade_type;  
            }                     
}  