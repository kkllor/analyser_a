package com.kkllor.network.impl;

import com.kkllor.network.INetwork;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
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
            System.out.println(response2.getStatusLine());
            HttpEntity entity2 = response2.getEntity();
            String responseString = EntityUtils.toString(entity2, "UTF-8");
            System.out.println(responseString);
            // and ensure it is fully consumed
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
    public boolean downLoad(String url, HashMap<String, Object> headers, String localPath) {
        return false;
    }
}
