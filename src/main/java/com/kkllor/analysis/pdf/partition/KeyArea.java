package com.kkllor.analysis.pdf.partition;

import com.kkllor.analysis.pdf.entity.PdfPage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kkllor
 * @date 2020/4/23 上午8:42
 */
public abstract class KeyArea {

    private List<PdfPage> pages = new ArrayList<>();
    private int startLine, endLine;

    public void addPage(PdfPage page) {
        pages.add(page);
    }
}
