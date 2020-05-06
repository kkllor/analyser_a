package com.kkllor.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kkllor
 * @date 2020/5/6 下午3:24
 */
public enum FlowDebt implements ItemType {
    //15:22:40.817 [main] INFO  CombineBalanceDetector - 向中央银行借款 ----- 未被识别
//15:22:40.817 [main] INFO  CombineBalanceDetector - 吸收存款及同业存放 ----- 未被识别
//15:22:40.817 [main] INFO  CombineBalanceDetector - 拆入资金 ----- 未被识别
//15:22:40.817 [main] INFO  CombineBalanceDetector - 以公允价值计量且其变动计入当期 ----- 未被识别
//15:22:40.817 [main] INFO  CombineBalanceDetector - 损益的金融负债 ----- 未被识别
//15:22:40.817 [main] INFO  CombineBalanceDetector - 衍生金融负债 ----- 未被识别
//15:22:40.817 [main] INFO  CombineBalanceDetector - 应付票据 ----- 未被识别
//15:22:40.817 [main] INFO  CombineBalanceDetector - 应付账款 ----- 未被识别
//15:22:40.817 [main] INFO  CombineBalanceDetector - 预收款项 ----- 未被识别
//15:22:40.817 [main] INFO  CombineBalanceDetector - 卖出回购金融资产款 ----- 未被识别
//15:22:40.817 [main] INFO  CombineBalanceDetector - 应付手续费及佣金 ----- 未被识别
//15:22:40.817 [main] INFO  CombineBalanceDetector - 应付职工薪酬 ----- 未被识别
//15:22:40.817 [main] INFO  CombineBalanceDetector - 应交税费 ----- 未被识别
//15:22:40.817 [main] INFO  CombineBalanceDetector - 应付利息 ----- 未被识别
//15:22:40.817 [main] INFO  CombineBalanceDetector - 应付股利 ----- 未被识别
//15:22:40.818 [main] INFO  CombineBalanceDetector - 其他应付款 ----- 未被识别
//15:22:40.818 [main] INFO  CombineBalanceDetector - 应付分保账款 ----- 未被识别
//15:22:40.818 [main] INFO  CombineBalanceDetector - 保险合同准备金 ----- 未被识别
//15:22:40.818 [main] INFO  CombineBalanceDetector - 代理买卖证券款 ----- 未被识别
//15:22:40.818 [main] INFO  CombineBalanceDetector - 代理承销证券款 ----- 未被识别
//15:22:40.818 [main] INFO  CombineBalanceDetector - 划分为持有待售的负债 ----- 未被识别
//15:22:40.818 [main] INFO  CombineBalanceDetector - 一年内到期的非流动负债 ----- 未被识别
//15:22:40.818 [main] INFO  CombineBalanceDetector - 其他流动负债 ----- 未被识别
//15:22:40.818 [main] INFO  CombineBalanceDetector - 流动负债合计 ----- 未被识别
    xzzyhjk("向中央银行借款"),
    xsckjtycf("吸收存款及同业存放"),
    crzj("拆入资金"),
    ygyjzjlqqbdjrdq("以公允价值计量且其变动计入当期损益的金融负债"),
    dqjk("短期借款"),
    ysjrfz("衍生金融负债"),
    yfpj("应付票据"),
    yfzk("应付账款"),
    yfpjjyfzk("应付票据及应付账款"),
    cydsfz("持有待售负债"),
    yskx("预收款项"),
    mchgjrzck("卖出回购金融资产款"),
    yfsyfjyj("应付手续费及佣金"),
    yfzgxc("应付职工薪酬"),
    yjsf("应交税费"),
    yflx("应付利息"),
    yfgl("应付股利"),
    qtyfk("其他应付款"),
    yffbzk("应付分保账款"),
    bxhtzbj("保险合同准备金"),
    dlmmzqk("代理买卖证券款"),
    dlcxzqk("代理承销证券款"),
    hfwcydsdfz("划分为持有待售的负债"),
    ynndqdfldfz("一年内到期的非流动负债"),
    qtldfz("其他流动负债"),
    ldfzhj("流动负债合计");

    private String name;

    private List<String> aliases = new ArrayList<>();

    @Override
    public String getName() {
        return name;
    }

    FlowDebt(String name) {
        this.name = name;
    }

    FlowDebt(String name, String... aliases) {
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
