package com.kkllor.analysis.pdf.partition.detector;

import com.kkllor.analysis.pdf.entity.PdfLine;
import com.kkllor.analysis.pdf.entity.PdfPage;
import com.kkllor.analysis.pdf.partition.KeyArea;
import com.kkllor.analysis.pdf.partition.KeyAreaType;

import java.util.List;

/**
 * @author kkllor
 * @date 2020/4/23 上午8:52
 */
public interface IDetector<T extends DetectorResult> {
    KeyAreaType getType();

    void onPageStarted(PdfPage page);

    void onPageEnded(PdfPage page);

    void detect(PdfLine pdfLine);

    List<T> collectResult();

    T result();

    boolean isUnique();

    boolean isFinished();
}
