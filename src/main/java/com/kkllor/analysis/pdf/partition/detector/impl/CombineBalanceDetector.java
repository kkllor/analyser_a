package com.kkllor.analysis.pdf.partition.detector.impl;

import com.kkllor.analysis.pdf.entity.PdfLine;
import com.kkllor.analysis.pdf.entity.PdfPage;
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

    @Override
    public KeyAreaType getType() {
        return KeyAreaType.COMBINE_BALANCE;
    }

    @Override
    public void onPageStarted(PdfPage page) {
        currentPage = page;
        if (!isFindBegin) {
            logger.info("begin:   pageNo" + page.getPageNo() + ",isFindBegin = " + false);
        } else {
            logger.info("begin:   pageNo" + page.getPageNo() + ",isFindBegin = " + true + ", isFindEnd = " + isFindEnd);
        }

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
                if (pdfLine.getGlobalLineNo() > globalBeginLineNo+1) {
                    PdfLine prePage = pdfLine.getRelativePage(-1);
                    if (prePage != null && prePage.containWord("负债和所有者权益总计")) {
                        endPageNo = currentPage.getPageNo();
                        endLineNo = prePage.getLineNo();
                        globalEndLineNo = prePage.getGlobalLineNo();
                        isFindEnd = true;
                        logger.info("find end pageNo = " + endPageNo + ", lineNo = " + endLineNo + ", globalLineNo = " + globalEndLineNo);
                    }
                }
            }
        }

        if (isFindBegin && !isFindBegin
                && currentPage.getPageNo() >= beginPageNo
                && pdfLine.getLineNo() > beginLineNo) {
            extractData(pdfLine);
        }
    }


    private void extractData(PdfLine pdfLine) {

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
}
