package com.kkllor.network;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * @author kkllor
 * @date 2020/4/20 下午4:18
 */
public interface INetwork {

    String post(String url, HashMap<String, String> params, HashMap<String, String> headers) throws IOException;

    boolean downLoad(String url, HashMap<String, Object> headers, String localPath);
}
