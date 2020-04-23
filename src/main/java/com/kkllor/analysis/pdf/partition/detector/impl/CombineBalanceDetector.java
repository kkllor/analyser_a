package com.kkllor.analysis.pdf.partition.detector.impl;

import com.kkllor.analysis.pdf.entity.PdfLine;
import com.kkllor.analysis.pdf.entity.PdfPage;
import com.kkllor.analysis.pdf.entity.WordLocation;
import com.kkllor.analysis.pdf.partition.KeyAreaType;
import com.kkllor.analysis.pdf.partition.detector.IDetector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kkllor
 * @date 2020/4/23 上午8:57
 */
public class CombineBalanceDetector implements IDetector<CombineBalanceResult> {


    private Logger logger = LogManager.getLogger(CombineBalanceDetector.class.getSimpleName());
    private CombineBalanceResult combineBalanceResult;

    boolean isFindBegin;

    boolean isFindEnd;

    private PdfPage currentPage;

    private int beginPageNo, beginLineNo, endPageNo, endLineNo;
    private long globalBeginLineNo, globalEndLineNo;

    private Section xm, fz, qmye, qcye;


    @Override
    public KeyAreaType getType() {
        return KeyAreaType.COMBINE_BALANCE;
    }

    @Override
    public void onPageStarted(PdfPage page) {
        currentPage = page;
    }

    @Override
    public void onPageEnded(PdfPage page) {

    }

    @Override
    public void detect(PdfLine pdfLine) {
        if (!isFindBegin) {
            if (pdfLine.containWord("合并资产负债表")) {
                int nextLine = 1;
                while (nextLine <= 5) {
                    PdfLine tmp = pdfLine.getRelativePage(nextLine);
                    if (tmp != null && tmp.containWordAnd("项目", "附注", "期末余额", "期初余额")) {
                        isFindBegin = true;
                        beginPageNo = currentPage.getPageNo();
                        beginLineNo = tmp.getLineNo();
                        globalBeginLineNo = tmp.getGlobalLineNo();
                        logger.info("find begin pageNo = " + beginPageNo + ", lineNo = " + beginLineNo + ", globalLineNo = " + globalBeginLineNo);
                        break;
                    }
                    nextLine++;
                }
            }
        } else {
            if (!isFindEnd) {
                if (pdfLine.getGlobalLineNo() > globalBeginLineNo + 1) {
                    if (pdfLine != null && pdfLine.containWord("法定代表人：")) {
                        endPageNo = currentPage.getPageNo();
                        endLineNo = pdfLine.getLineNo() - 1;
                        globalEndLineNo = pdfLine.getGlobalLineNo() - 1;
                        isFindEnd = true;
                        logger.info("find end pageNo = " + endPageNo + ", lineNo = " + endLineNo + ", globalLineNo = " + globalEndLineNo);
                    }
                }
            }
        }

        if (isFindBegin && !isFindEnd
                && pdfLine.getGlobalLineNo() >= globalBeginLineNo) {
            extractData(pdfLine);
        }
    }


    private void extractData(PdfLine pdfLine) {
        //计算表格
        if (pdfLine.getGlobalLineNo() == globalBeginLineNo) {
            WordLocation xmLocation = null;
            WordLocation fzLocation = null;
            WordLocation qmyeocation = null;
            WordLocation qcyeLocation = null;

            for (WordLocation location : pdfLine.getWordLocations()) {
                if ("项目".equals(location.getValue())) {
                    xmLocation = location;
                } else if ("附注".equals(location.getValue())) {
                    fzLocation = location;
                } else if ("期末余额".equals(location.getValue())) {
                    qmyeocation = location;
                } else if ("期初余额".equals(location.getValue())) {
                    qcyeLocation = location;
                }
            }

            xm = new Section(0, fzLocation.getX());
            fz = new Section(xmLocation.getX() + xmLocation.getWidth(), qmyeocation.getX());
            qmye = new Section(fzLocation.getX() + fzLocation.getWidth(), qcyeLocation.getX());
            qcye = new Section(qmyeocation.getX() + qmyeocation.getWidth(), 2048);
        }


        StringBuilder stringBuilder = new StringBuilder();
        for (WordLocation wordLocation : pdfLine.getWordLocations()) {
            Section section = new Section(wordLocation.getX(), wordLocation.getX() + wordLocation.getWidth());
            if (xm.isInclude(section)) {
                stringBuilder.append("项目");
                stringBuilder.append(":");
                stringBuilder.append(wordLocation.getValue());
                stringBuilder.append("  ");
            } else if (fz.isInclude(section)) {
                stringBuilder.append("附注");
                stringBuilder.append(":");
                stringBuilder.append(wordLocation.getValue());
                stringBuilder.append("  ");
            } else if (qmye.isInclude(section)) {
                stringBuilder.append("期末余额");
                stringBuilder.append(":");
                stringBuilder.append(wordLocation.getValue());
                stringBuilder.append("  ");
            } else if (qcye.isInclude(section)) {
                stringBuilder.append("期初余额");
                stringBuilder.append(":");
                stringBuilder.append(wordLocation.getValue());
                stringBuilder.append("  ");
            }

        }
        System.out.println(stringBuilder);
    }

    @Override
    public List<CombineBalanceResult> collectResult() {
        return new ArrayList<>();
    }

    @Override
    public CombineBalanceResult result() {
        return combineBalanceResult;
    }

    @Override
    public boolean isUnique() {
        return true;
    }

    public static class Section {
        private final double start, end;

        public Section(double start, double end) {
            this.start = start;
            this.end = end;
        }

        public boolean isInclude(Section section) {
            return section.start >= start && section.end <= end;
        }
    }
}
