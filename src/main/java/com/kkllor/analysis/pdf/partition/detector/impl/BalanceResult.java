package com.kkllor.analysis.pdf.partition.detector.impl;

import com.kkllor.analysis.pdf.entity.Item;
import com.kkllor.analysis.pdf.partition.detector.DetectorResult;
import com.kkllor.constants.FlowAssets;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author kkllor
 * @date 2020/4/23 上午8:59
 */
public class BalanceResult implements DetectorResult {
    private static final DecimalFormat decimalFormat = new DecimalFormat("###,###.00");
    private Map<FlowAssets, Item> flowAssets = new LinkedHashMap<>();

    public Map<FlowAssets, Item> getFlowAssets() {
        return flowAssets;
    }

    public void setFlowAssets(Map<FlowAssets, Item> flowAssets) {
        this.flowAssets = flowAssets;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("流动资产：\n");
        for (Item item : flowAssets.values()) {
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

    @Override
    public boolean selfCheck() {
        BigDecimal total_prevalue = BigDecimal.ZERO;
        BigDecimal total_currentvalue = BigDecimal.ZERO;
        Item ldzchjItem = flowAssets.get(FlowAssets.ldzchj);
        BigDecimal ldzczh_prevalue = ldzchjItem.getPreValue() == null ? BigDecimal.ZERO : ldzchjItem.getPreValue();
        BigDecimal ldzczh_currentvalue = ldzchjItem.getCurrentValue() == null ? BigDecimal.ZERO : ldzchjItem.getCurrentValue();
        for (Item item : flowAssets.values()) {
            if (item.getItemType() != FlowAssets.ldzchj) {
                total_prevalue = total_prevalue.add(item.getPreValue() == null ? BigDecimal.ZERO : item.getPreValue());
                total_currentvalue = total_currentvalue.add(item.getCurrentValue() == null ? BigDecimal.ZERO : item.getCurrentValue());
            }
        }

        boolean isPreValueEqual = total_prevalue.equals(ldzczh_prevalue);
        boolean isCurrentValueEqual = total_currentvalue.equals(ldzczh_currentvalue);
        System.out.println("计算得知的流动资产合计为：\n 期初" + decimalFormat.format(total_prevalue) + ", 期末" + decimalFormat.format(total_currentvalue)
                + "\n 读取数值为：\n 期初：" + decimalFormat.format(ldzczh_prevalue) + ", 期末：" + decimalFormat.format(ldzczh_currentvalue));
        return isPreValueEqual && isCurrentValueEqual;
    }
}
