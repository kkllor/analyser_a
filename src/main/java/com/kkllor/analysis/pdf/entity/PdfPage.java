package com.kkllor.analysis.pdf.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kkllor
 * @date 2020/4/22 下午3:48
 */
public class PdfPage {
    private int pageNo;
    private List<PdfLine> pdfLines = new ArrayList<>();

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<PdfLine> getPdfLines() {
        return pdfLines;
    }

    public void setPdfLines(List<PdfLine> pdfLines) {
        this.pdfLines = pdfLines;
    }

    public void addLine(PdfLine pdfLine) {
        pdfLines.add(pdfLine);
    }
}

