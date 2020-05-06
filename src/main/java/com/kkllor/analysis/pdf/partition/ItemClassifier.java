package com.kkllor.analysis.pdf.partition;

import com.kkllor.constants.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kkllor
 * @date 2020/4/23 下午1:50
 */
public class ItemClassifier {

    private static Map<String, ItemType> typeMaps = new HashMap<>();

    static {
        for (FlowAssets flowAssets : FlowAssets.values()) {
            typeMaps.put(flowAssets.getName(), flowAssets);
            if (flowAssets.getAliases() != null && !flowAssets.getAliases().isEmpty()) {
                for (String alias : flowAssets.getAliases()) {
                    typeMaps.put(alias, flowAssets);
                }
            }
        }

        for (FixedAssets fixedAssets : FixedAssets.values()) {
            typeMaps.put(fixedAssets.getName(), fixedAssets);
            if (fixedAssets.getAliases() != null && fixedAssets.getAliases().isEmpty()) {
                for (String alias : fixedAssets.getAliases()) {
                    typeMaps.put(alias, fixedAssets);
                }
            }
        }

        for (FlowDebt flowDebt : FlowDebt.values()) {
            typeMaps.put(flowDebt.getName(), flowDebt);
            if (flowDebt.getAliases() != null && flowDebt.getAliases().isEmpty()) {
                for (String alias : flowDebt.getAliases()) {
                    typeMaps.put(alias, flowDebt);
                }
            }
        }

        for (FixDebt fixDebt : FixDebt.values()) {
            typeMaps.put(fixDebt.getName(), fixDebt);
            if (fixDebt.getAliases() != null && fixDebt.getAliases().isEmpty()) {
                for (String alias : fixDebt.getAliases()) {
                    typeMaps.put(alias, fixDebt);
                }
            }
        }
    }


    public static ItemType getItemTypeByName(String name) {
        return typeMaps.get(name);
    }
}
