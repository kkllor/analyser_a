package com.kkllor.download.entity;

import com.kkllor.constants.Market;

/**
 * @author kkllor
 * @date 2020/4/21 上午9:25
 */
public class Company {
    private String code;
    private Market market;

    public Company(String code) {
        this.code = code;
        if (code.startsWith("60")) {
            market = Market.SH;
        } else if (code.startsWith("00")) {
            market = Market.SZ;
        }
    }

    public String getCode() {
        return code;
    }

    public Market getMarket() {
        return market;
    }
}
