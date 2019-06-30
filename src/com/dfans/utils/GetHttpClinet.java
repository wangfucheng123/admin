package com.dfans.utils;

import org.apache.http.HttpVersion;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class GetHttpClinet
{
  public static DefaultHttpClient getHttpClient()
  {
    HttpParams params = new BasicHttpParams();
    HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
    HttpProtocolParams.setUseExpectContinue(params, true);

    int REQUEST_TIMEOUT = 30000;
    int SO_TIMEOUT = 30000;

    params.setParameter("http.connection.timeout", Integer.valueOf(REQUEST_TIMEOUT));
    params.setParameter("http.socket.timeout", Integer.valueOf(SO_TIMEOUT));

    SchemeRegistry schreg = new SchemeRegistry();
    schreg.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
    schreg.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));

    PoolingClientConnectionManager pccm = new PoolingClientConnectionManager(schreg);
    pccm.setDefaultMaxPerRoute(20);
    pccm.setMaxTotal(100);

    DefaultHttpClient httpClient = new DefaultHttpClient(pccm, params);
    return httpClient;
  }
}