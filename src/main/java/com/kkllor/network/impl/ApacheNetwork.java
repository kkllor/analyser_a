package com.kkllor.network.impl;

import com.kkllor.download.entity.Report;
import com.kkllor.network.INetwork;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author kkllor
 * @date 2020/4/20 下午4:21
 */
public class ApacheNetwork implements INetwork {
    static Logger logger = LogManager.getLogManager().getLogger(ApacheNetwork.class.getSimpleName());

    @Override
    public String post(String url, HashMap<String, String> params, HashMap<String, String> headers) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (String key : params.keySet()) {
            nvps.add(new BasicNameValuePair(key, params.get(key).toString()));
        }
        for (String key : headers.keySet()) {
            httpPost.addHeader(key, headers.get(key));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response2 = httpclient.execute(httpPost);
        BufferedReader rd = null;
        try {
            HttpEntity entity2 = response2.getEntity();
            String responseString = EntityUtils.toString(entity2, "UTF-8");
            EntityUtils.consume(entity2);
            return responseString;
        } finally {
            response2.close();
            if (rd != null) {
                rd.close();
            }
        }
    }

    @Override
    public String postString(String url, String params, HashMap<String, String> headers) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        for (String key : headers.keySet()) {
            httpPost.addHeader(key, headers.get(key));
        }
        httpPost.setEntity(new StringEntity(params, ContentType.APPLICATION_FORM_URLENCODED));
        CloseableHttpResponse response2 = httpclient.execute(httpPost);
        BufferedReader rd = null;
        try {
            HttpEntity entity2 = response2.getEntity();
            String responseString = EntityUtils.toString(entity2, "UTF-8");
            EntityUtils.consume(entity2);
            return responseString;
        } finally {
            response2.close();
            if (rd != null) {
                rd.close();
            }
        }
    }

    @Override
    public boolean downLoad(Report report) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(report.getUrl());
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        BufferedInputStream bis = new BufferedInputStream(entity.getContent());
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(report.getLocalPath()));
        byte[] bytes = new byte[2048];
        int readBytes;
        while ((readBytes = bis.read(bytes, 0, 2048)) != -1) {
            bos.write(bytes, 0, readBytes);
        }
        bos.flush();
        bis.close();
        bos.close();
        client.close();
        return true;
    }
}
