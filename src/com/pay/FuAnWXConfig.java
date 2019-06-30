package com.pay;
import com.github.wxpay.sdk.WXPayConfig;
import java.io.*;

public class FuAnWXConfig implements WXPayConfig {
	
	private byte[] certData;

    public FuAnWXConfig() throws Exception {
        String certPath = "apiclient_cert.p12";
        String filePath = FuAnWXConfig.class.getResource("/").toString() + certPath;
        if (filePath.contains("file:/opt")) {
        	filePath = filePath.substring(5);
        }
        System.out.println(filePath);
        if (filePath.indexOf("file:/") > -1) {
        	filePath = filePath.substring(6);
        }
        System.out.println(filePath);
        File file = new File(filePath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    public String getAppID() {
        return "wx4670ed767710c45f";
    }

    public String getMchID() {
        return "1484290852";
    }

    public String getKey() {
        return "Ma0eYT7hknKxz6L5Bu3kHznHbifEH7N9";
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

}
