package com.kkllor.constants;

/**
 * @author kkllor
 * @date 2020/4/21 上午9:31
 */
public enum Quarter {
    I("第一季度"), II("第二季度"), III("第三季度"), IV("第四季度");

    private String value;

    Quarter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
