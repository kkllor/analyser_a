package com.kkllor.analysis.pdf.partition.detector.impl;

import com.kkllor.analysis.pdf.entity.Item;
import com.kkllor.analysis.pdf.partition.detector.DetectorResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kkllor
 * @date 2020/4/23 上午8:59
 */
public class BalanceResult implements DetectorResult {
    private List<Item> flowAssets = new ArrayList<>();

    public List<Item> getFlowAssets() {
        return flowAssets;
    }

    public void setFlowAssets(List<Item> flowAssets) {
        this.flowAssets = flowAssets;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("流动资产：\n");
        for (Item item : flowAssets) {
            stringBuilder.append(item.getItemType().getName());
            stringBuilder.append("   ");
            stringBuilder.append("期末余额：");
            stringBuilder.append(item.getCurrentValue());
            stringBuilder.append("   ");
            stringBuilder.append("期初余额：");
            stringBuilder.append(item.getPreValue() + "\n");
        }
        return stringBuilder.toString();
    }
}
