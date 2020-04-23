package com.kkllor.analysis.pdf.partition;

/**
 * @author kkllor
 * @date 2020/4/23 上午8:54
 */
public enum KeyAreaType {
    COMBINE_BALANCE("合并资产负债表"), PARENT_BALANCE("母公司资产负债表"), COMBINE_PROFIT("合并利润表"), PARENT_PROFIT("母公司利润表"), COMBINE_CASH("合并现金流量表"), PARENT_CASH("母公司现金流量表");
    private String value;
    KeyAreaType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
