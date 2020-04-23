package com.kkllor.analysis.pdf.entity;

import com.kkllor.constants.ItemType;

import java.math.BigDecimal;

/**
 * @author kkllor
 * @date 2020/4/23 下午1:39
 */
public class Item {
    private ItemType itemType;
    private BigDecimal preValue, currentValue;

    public Item(ItemType itemType, BigDecimal preValue, BigDecimal currentValue) {
        this.itemType = itemType;
        this.preValue = preValue;
        this.currentValue = currentValue;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public BigDecimal getPreValue() {
        return preValue;
    }

    public void setPreValue(BigDecimal preValue) {
        this.preValue = preValue;
    }

    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemType=" + itemType.getName() +
                ", preValue=" + preValue +
                ", currentValue=" + currentValue +
                '}';
    }
}
