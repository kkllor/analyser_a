package com.kkllor.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kkllor
 * @date 2020/4/23 下午12:45
 */
public enum FixedAssets implements ItemType {

    fsdkhdk("发放贷款和垫款"),
    kgcsjrzc("可供出售金融资产"),
    cydqtz("持有至到期投资"),
    cqysk("长期应收款"),
    cqgqtz("长期股权投资"),
    tzxfdc("投资性房地产"),
    gdzc("固定资产"),
    zjgc("在建工程"),
    gcwz("工程物资"),
    gdzcql("固定资产清理"),
    scxswzc("生产性生物资产"),
    yqzc("油气资产"),
    wxzc("无形资产"),
    kfzc("开发支出"),
    sy("商誉"),
    cqdtfy("长期待摊费用"),
    dysdszc("递延所得税资产"),
    qtfldzc("其他非流动资产"),
    fldzchj("非流动资产合计");
    private String name;

    private List<String> aliases = new ArrayList<>();

    @Override
    public String getName() {
        return name;
    }

    FixedAssets(String name) {
        this.name = name;
    }

    FixedAssets(String name, String... aliases) {
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
