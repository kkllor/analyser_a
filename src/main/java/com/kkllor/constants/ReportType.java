package com.kkllor.constants;

public enum ReportType {
    YEAR("年报"), QUARTER("季报"), OTHER("其他");

    private String value;

    ReportType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ReportType{" +
                "value='" + value + '\'' +
                '}';
    }
}
