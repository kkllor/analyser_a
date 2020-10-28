package com.kkllor.analysis.pdf.partition.detector.entity;

import com.kkllor.analysis.pdf.entity.Item;
import com.kkllor.analysis.pdf.partition.detector.DetectorResult;
import com.kkllor.constants.*;

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

    private Map<FlowDebt, Item> flowDebts = new LinkedHashMap<>();
    private Map<FixDebt, Item> fixDebts = new LinkedHashMap<>();

    public Map<FlowAssets, Item> getFlowAssets() {
        return flowAssets;
    }

    public Map<FixedAssets, Item> getFixAssets() {
        return fixAssets;
    }

    public Map<FlowDebt, Item> getFlowDebts() {
        return flowDebts;
    }

    public Map<FixDebt, Item> getFixDebts() {
        return fixDebts;
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


        stringBuilder.append("流动负债：\n");
        for (Item item : flowDebts.values()) {
            stringBuilder.append(item.getItemType().getName());
            stringBuilder.append("   ");
            stringBuilder.append("期末余额：");
            stringBuilder.append(item.getCurrentValue());
            stringBuilder.append("   ");
            stringBuilder.append("期初余额：");
            stringBuilder.append(item.getPreValue() + "\n");
        }

        stringBuilder.append("非流动负债：\n");
        for (Item item : fixDebts.values()) {
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
        if (flowDebts.get(FlowDebt.yfpjjyfzk) != null) {
            flowDebts.remove(FlowDebt.yfpj);
            flowDebts.remove(FlowDebt.yfzk);
        }

        boolean flowAssetCheck = check(flowAssets, FlowAssets.ldzchj, "流动资产");
        boolean fixAssetCheck = check(fixAssets, FixedAssets.fldzchj, "非流动资产");
        boolean flowDebtCheck = check(flowDebts, FlowDebt.ldfzhj, "流动负债");
        boolean fixDebtCheck = check(fixDebts, FixDebt.fldfzhj, "非流动负债");
        return flowAssetCheck && fixAssetCheck && flowDebtCheck && fixDebtCheck;
    }

    private <T extends ItemType> boolean check(Map<T, Item> map, T readType, String name) {
        BigDecimal calculate_pre = BigDecimal.ZERO;
        BigDecimal calculate_current = BigDecimal.ZERO;
        Item readItem = map.get(readType);
        BigDecimal read_pre = readItem.getPreValue() == null ? BigDecimal.ZERO : readItem.getPreValue();
        BigDecimal read_current = readItem.getCurrentValue() == null ? BigDecimal.ZERO : readItem.getCurrentValue();
        for (Item item : map.values()) {
            if (item.getItemType() != readType) {
                calculate_pre = calculate_pre.add(item.getPreValue() == null ? BigDecimal.ZERO : item.getPreValue());
                calculate_current = calculate_current.add(item.getCurrentValue() == null ? BigDecimal.ZERO : item.getCurrentValue());
            }
        }

        boolean isPreValueEqual = calculate_pre.equals(read_pre);
        boolean isCurrentValueEqual = calculate_current.equals(read_current);
        StringBuilder stringBuilder = new StringBuilder("计算得知的");
        stringBuilder.append(name);
        stringBuilder.append("合计为：");
        stringBuilder.append("\n");
        stringBuilder.append("期初：");
        stringBuilder.append(decimalFormat.format(calculate_pre));
        stringBuilder.append(", 期末：");
        stringBuilder.append(decimalFormat.format(calculate_current));
        stringBuilder.append("\n");
        stringBuilder.append("读取数值为：");
        stringBuilder.append("\n");
        stringBuilder.append("期初：");
        stringBuilder.append(decimalFormat.format(read_pre));
        stringBuilder.append(", 期末：");
        stringBuilder.append(decimalFormat.format(read_current));
        stringBuilder.append("\n");
        stringBuilder.append(name);
        stringBuilder.append("检查结果：");
        stringBuilder.append(isPreValueEqual && isCurrentValueEqual);
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        System.out.println(stringBuilder.toString());

        return isPreValueEqual && isCurrentValueEqual;
    }
}
