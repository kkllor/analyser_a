package com.kkllor.analysis.pdf.partition.detector.entity;

import com.kkllor.analysis.pdf.partition.detector.DetectorResult;
import org.apache.http.util.TextUtils;

/**
 * @author kkllor
 * @date 2020/5/7 下午6:06
 */
public class BaseInfoResult implements DetectorResult {
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean selfCheck() {
        return !TextUtils.isEmpty(name) && !TextUtils.isEmpty(code);
    }
}
