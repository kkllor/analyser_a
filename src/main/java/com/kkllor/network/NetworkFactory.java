package com.kkllor.network;

import com.kkllor.network.impl.ApacheNetwork;

/**
 * @author kkllor
 * @date 2020/4/20 下午4:33
 */
public class NetworkFactory {
    public static INetwork getNetwork() {
        return new ApacheNetwork();
    }
}
