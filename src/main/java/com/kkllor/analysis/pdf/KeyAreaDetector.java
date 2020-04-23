package com.kkllor.analysis.pdf;

import com.kkllor.analysis.pdf.entity.PdfLine;
import com.kkllor.analysis.pdf.entity.PdfPage;
import com.kkllor.analysis.pdf.partition.KeyArea;
import com.kkllor.analysis.pdf.partition.detector.DetectorFactory;
import com.kkllor.analysis.pdf.partition.detector.IDetector;

import java.util.HashMap;
import java.util.List;

/**
 * @author kkllor
 * @date 2020/4/23 上午8:45
 */
public class KeyAreaDetector {
    public HashMap<String, KeyArea> keyAreaHashMap = new HashMap<>();
    private List<IDetector> detectors;

    public KeyAreaDetector() {
        detectors = DetectorFactory.getSupportDetectors();
    }

    public void beginDetect(List<PdfPage> pages) {
        for (int index = 0; index < pages.size(); index++) {
            PdfPage page = pages.get(index);
            for (int i = 0; i < detectors.size(); i++) {
                IDetector detector = detectors.get(i);
                detector.onPageStarted(page);
                for (PdfLine pdfLine : page.getPdfLines()) {
                    detector.detect(pdfLine);
                }
                detector.onPageEnded(page);
            }
        }
    }
}
