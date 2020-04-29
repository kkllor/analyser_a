package com.kkllor.network;

import com.kkllor.download.entity.Report;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * @author kkllor
 * @date 2020/4/20 下午4:18
 */
public interface INetwork {

    String post(String url, HashMap<String, String> params, HashMap<String, String> headers) throws IOException;

    String postString(String url, String params, HashMap<String, String> headers) throws IOException;

    boolean downLoad(Report report) throws IOException;
}
