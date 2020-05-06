package com.kkllor.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kkllor
 * @date 2020/5/6 下午3:25
 */
public enum FixDebt implements ItemType {

    fyzq("应付债券"),
    yxz("永续债"),
    cqyfk("长期应付款"),
    cqyfzgxc("长期应付职工薪酬"),
    cqjk("长期借款"),
    zxyfk("专项应付款"),
    yjfz("预计负债"),
    dysy("递延收益"),
    dysdsfz("递延所得税负债"),
    qtfldfz("其他非流动负债"),
    fldfzhj("非流动负债合计");


    private String name;

    private List<String> aliases = new ArrayList<>();

    @Override
    public String getName() {
        return name;
    }

    FixDebt(String name) {
        this.name = name;
    }

    FixDebt(String name, String... aliases) {
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
