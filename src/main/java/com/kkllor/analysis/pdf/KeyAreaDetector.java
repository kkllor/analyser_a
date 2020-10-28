package com.kkllor.analysis.pdf;

import com.kkllor.analysis.pdf.entity.PdfLine;
import com.kkllor.analysis.pdf.entity.PdfPage;
import com.kkllor.analysis.pdf.partition.KeyAreaType;
import com.kkllor.analysis.pdf.partition.detector.DetectorFactory;
import com.kkllor.analysis.pdf.partition.detector.DetectorResult;
import com.kkllor.analysis.pdf.partition.detector.IDetector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kkllor
 * @date 2020/4/23 上午8:45
 */
public class KeyAreaDetector {
    public HashMap<KeyAreaType, DetectorResult> keyAreaHashMap = new HashMap<>();
    private Map<KeyAreaType, IDetector> detectors;

    public KeyAreaDetector() {
        detectors = DetectorFactory.getSupportDetectors();
    }

    public void beginDetect(List<PdfPage> pages) {
        for (int index = 0; index < pages.size(); index++) {
            PdfPage page = pages.get(index);
            for (KeyAreaType keyAreaType : detectors.keySet()) {
                IDetector detector = detectors.get(keyAreaType);
                detector.onPageStarted(page);
                for (PdfLine pdfLine : page.getPdfLines()) {
                    if (!detector.isFinished()) {
                        detector.detect(pdfLine);
                    }
                }
                detector.onPageEnded(page);
            }
        }

        for (KeyAreaType keyAreaType : detectors.keySet()) {
            IDetector detector = detectors.get(keyAreaType);
            if (detector.isUnique()) {
                keyAreaHashMap.put(keyAreaType, detector.result());
            }
        }

        for (KeyAreaType keyAreaType : keyAreaHashMap.keySet()) {
            System.out.println(keyAreaType.getValue() + ":");
            DetectorResult result = keyAreaHashMap.get(keyAreaType);
            if (result != null) {
                System.out.println(result.toString());
                boolean checkResult = result.selfCheck();
                System.out.println("自检结果：" + checkResult);
            }

        }
    }
}
