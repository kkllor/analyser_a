package com.kkllor.analysis.pdf.partition;

import com.kkllor.constants.FixedAssets;
import com.kkllor.constants.FlowAssets;
import com.kkllor.constants.ItemType;

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
    }


    public static ItemType getItemTypeByName(String name) {
        return typeMaps.get(name);
    }
}
