package com.kkllor.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kkllor
 * @date 2020/4/23 下午12:45
 */
public enum FlowAssets implements ItemType{
    hbzj("货币资金")
    , jsbfj("结算备付金")
    , cczj("拆出资金")
    , ygyjzjlqjr("以公允价值计量且其变动计入当期损益的金融资产")
    , ysjrzc("衍生金融资产")
    , yspj("应收票据")
    , yszk("应收账款")
    , ysbf("应收保费")
    , ysfbzk("应收分保账款")
    , ysfbhtzbj("应收分保合同准备金")
    , yslx("应收利息")
    , mrfsjrzc("买入返售金融资产")
    , ch("存货")
    , hfwcydsdzc("划分为持有待售的资产")
    , ynndqdfldzc("一年内到期的非流动资产")
    , qtldzc("其他流动资产")
    , ldzchj("流动资产合计");

    private String name;

    private List<String> aliases = new ArrayList<>();

    @Override
    public String getName() {
        return name;
    }

    FlowAssets(String name) {
        this.name = name;
    }

    FlowAssets(String name, String... aliases) {
        this.name = name;
        for (String alias : aliases) {
            this.aliases.add(alias);
        }
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }
}
