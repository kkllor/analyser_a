package com.kkllor.analysis.pdf.partition.detector.impl;

import com.kkllor.analysis.pdf.entity.Item;
import com.kkllor.analysis.pdf.partition.detector.DetectorResult;
import com.kkllor.constants.FixedAssets;
import com.kkllor.constants.FlowAssets;
import com.kkllor.constants.ItemType;

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
    private Map<FixedAssets, Item> fixAssets = new LinkedHashMap<>();

    public Map<FlowAssets, Item> getFlowAssets() {
        return flowAssets;
    }

    public Map<FixedAssets, Item> getFixAssets() {
        return fixAssets;
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


        stringBuilder.append("非流动资产：\n");
        for (Item item : fixAssets.values()) {
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
        if (flowAssets.get(FlowAssets.yspjjyszk) != null) {
            flowAssets.remove(FlowAssets.yspj);
            flowAssets.remove(FlowAssets.yszk);
        }
        boolean flowCheck = check(flowAssets, FlowAssets.ldzchj, "流动资产");
        System.out.println("流动资产检查结果：" + flowCheck);
        System.out.println();
        System.out.println();
        System.out.println();
        boolean fixCheck = check(fixAssets, FixedAssets.fldzchj, "非流动资产");
        System.out.println("非流动资产检查结果：" + fixCheck);
        return flowCheck && fixCheck;
    }

    private <T extends ItemType> boolean check(Map<T, Item> map, T readType, String name) {
        BigDecimal calculate_prevalue = BigDecimal.ZERO;
        BigDecimal calculate_currentvalue = BigDecimal.ZERO;
        Item readItem = map.get(readType);
        BigDecimal read_prevalue = readItem.getPreValue() == null ? BigDecimal.ZERO : readItem.getPreValue();
        BigDecimal read_currentvalue = readItem.getCurrentValue() == null ? BigDecimal.ZERO : readItem.getCurrentValue();
        for (Item item : map.values()) {
            if (item.getItemType() != readType) {
                calculate_prevalue = calculate_prevalue.add(item.getPreValue() == null ? BigDecimal.ZERO : item.getPreValue());
                calculate_currentvalue = calculate_currentvalue.add(item.getCurrentValue() == null ? BigDecimal.ZERO : item.getCurrentValue());
            }
        }

        boolean isPreValueEqual = calculate_prevalue.equals(read_prevalue);
        boolean isCurrentValueEqual = calculate_currentvalue.equals(read_currentvalue);
        StringBuilder stringBuilder = new StringBuilder("计算得知的");
        stringBuilder.append(name);
        stringBuilder.append("合计为：");
        stringBuilder.append("\n");
        stringBuilder.append("期初：");
        stringBuilder.append(decimalFormat.format(calculate_prevalue));
        stringBuilder.append(", 期末：");
        stringBuilder.append(decimalFormat.format(calculate_currentvalue));
        stringBuilder.append("\n");
        stringBuilder.append("读取数值为：");
        stringBuilder.append("\n");
        stringBuilder.append("期初：");
        stringBuilder.append(decimalFormat.format(read_prevalue));
        stringBuilder.append(", 期末：");
        stringBuilder.append(decimalFormat.format(read_currentvalue));

        System.out.println(stringBuilder.toString());
        return isPreValueEqual && isCurrentValueEqual;
    }
}
