package com.doubantop.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtils {
    private PoolingHttpClientConnectionManager cm;
    public HttpUtils() {
        this.cm = new PoolingHttpClientConnectionManager();
        //设置最大连接数
        this.cm.setMaxTotal(100);
        //设置每个主机的最大连接数
        this.cm.setDefaultMaxPerRoute(10);
    }
    public String doGetHtml(String url){
        //获取httpclient对象
        CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(this.cm).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent","Mozilla/5.0(Windows NT 6.1;Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
        //设置请求信息
        httpGet.setConfig(this.getConfig());
        CloseableHttpResponse response =null;
        try {
            response = httpclient.execute(httpGet);
            if(response.getStatusLine().getStatusCode()==200){
                //判断响应体是否为空
                if(response.getEntity()!=null){
                    String content = EntityUtils.toString(response.getEntity(), "utf8");
                    return content;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭response
            if(response!=null){
                try {
                    response.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
    private RequestConfig getConfig() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(1000)
                .setConnectionRequestTimeout(500)
                .setSocketTimeout(10000)
                .build();
        return config;
    }
}
