package com.kkllor.analysis.pdf.partition.detector;

import com.kkllor.analysis.pdf.partition.KeyAreaType;
import com.kkllor.analysis.pdf.partition.detector.impl.BaseInfoDetector;
import com.kkllor.analysis.pdf.partition.detector.impl.CombineBalanceDetector;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kkllor
 * @date 2020/4/23 上午8:57
 */
public class DetectorFactory {

    public static IDetector getDetector(KeyAreaType type) {
        switch (type) {
            case COMBINE_BALANCE:
                return new CombineBalanceDetector();
            case BASE_INFO:
                return new BaseInfoDetector();
        }
        return null;
    }

    public static Map<KeyAreaType, IDetector> getSupportDetectors() {
        Map<KeyAreaType, IDetector> hashMap = new HashMap<>();
        hashMap.put(KeyAreaType.COMBINE_BALANCE, getDetector(KeyAreaType.COMBINE_BALANCE));
        hashMap.put(KeyAreaType.BASE_INFO, getDetector(KeyAreaType.BASE_INFO));
        return hashMap;
    }
}
