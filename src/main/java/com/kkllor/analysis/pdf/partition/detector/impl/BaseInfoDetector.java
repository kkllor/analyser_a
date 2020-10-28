package com.kkllor.analysis.pdf.partition.detector.impl;

import com.kkllor.analysis.pdf.entity.PdfLine;
import com.kkllor.analysis.pdf.entity.PdfPage;
import com.kkllor.analysis.pdf.partition.KeyAreaType;
import com.kkllor.analysis.pdf.partition.detector.IDetector;
import com.kkllor.analysis.pdf.partition.detector.entity.BaseInfoResult;

import java.util.List;

/**
 * @author kkllor
 * @date 2020/5/7 下午6:04
 */
public class BaseInfoDetector implements IDetector<BaseInfoResult> {
    private BaseInfoResult baseInfoResult;

    @Override
    public KeyAreaType getType() {
        return KeyAreaType.BASE_INFO;
    }

    @Override
    public void onPageStarted(PdfPage page) {

    }

    @Override
    public void onPageEnded(PdfPage page) {

    }

    @Override
    public void detect(PdfLine pdfLine) {
        if (pdfLine.getWordLocations().size() == 4 && pdfLine.containWordAnd("股票简称", "股票代码")) {
            baseInfoResult = new BaseInfoResult();
            baseInfoResult.setName(pdfLine.getWordLocations().get(1).getValue());
            baseInfoResult.setCode(pdfLine.getWordLocations().get(3).getValue());
        }
    }

    @Override
    public List<BaseInfoResult> collectResult() {
        return null;
    }

    @Override
    public BaseInfoResult result() {
        return baseInfoResult;
    }

    @Override
    public boolean isUnique() {
        return true;
    }

    @Override
    public boolean isFinished() {
        return baseInfoResult != null;
    }
}
