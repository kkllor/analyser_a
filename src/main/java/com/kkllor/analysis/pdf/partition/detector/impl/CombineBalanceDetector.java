package com.kkllor.analysis.pdf.partition.detector.impl;

import com.kkllor.analysis.pdf.entity.Item;
import com.kkllor.analysis.pdf.entity.PdfLine;
import com.kkllor.analysis.pdf.entity.PdfPage;
import com.kkllor.analysis.pdf.entity.WordLocation;
import com.kkllor.analysis.pdf.partition.ItemClassifier;
import com.kkllor.analysis.pdf.partition.KeyAreaType;
import com.kkllor.analysis.pdf.partition.detector.IDetector;
import com.kkllor.constants.*;
import org.apache.http.util.TextUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kkllor
 * @date 2020/4/23 上午8:57
 */
public class CombineBalanceDetector implements IDetector<BalanceResult> {

    private static final int DETECT_LINE = 10;
    private Logger logger = LogManager.getLogger(CombineBalanceDetector.class.getSimpleName());
    private BalanceResult combineBalanceResult = new BalanceResult();

    boolean isFindBegin;

    boolean isFindEnd;

    private PdfPage currentPage;

    private int beginPageNo, beginLineNo, endPageNo, endLineNo;
    private long globalBeginLineNo, globalEndLineNo;

    private Section xm, fz, qmye, qcye;

    private List<Long> ignoreLines = new ArrayList<>();

    private static final String ygyjzbdjr = "以公允价值计量";

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
        if (ignoreLines.contains(pdfLine.getGlobalLineNo())) {
            return;
        }
        if (!isFindBegin) {
            if (pdfLine.containWord("合并资产负债表")) {
                int nextLine = 1;
                while (nextLine <= DETECT_LINE) {
                    PdfLine tmp = pdfLine.getRelativePage(nextLine);
                    if (tmp != null && tmp.containWordAnd("项目",/* "附注",*/ "期末余额", "期初余额")) {
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

            if (fzLocation == null) {
                xm = new Section(0, qmyeocation.getX());
                qmye = new Section(xmLocation.getX() + xmLocation.getWidth(), qcyeLocation.getX());
                qcye = new Section(qmyeocation.getX() + qmyeocation.getWidth(), 2048);
            } else {
                xm = new Section(0, fzLocation.getX());
                fz = new Section(xmLocation.getX() + xmLocation.getWidth(), qmyeocation.getX());
                qmye = new Section(fzLocation.getX() + fzLocation.getWidth(), qcyeLocation.getX());
                qcye = new Section(qmyeocation.getX() + qmyeocation.getWidth(), 2048);
            }
        }

        ItemType itemType = null;
        BigDecimal preValue = null, currentValue = null;
        for (WordLocation wordLocation : pdfLine.getWordLocations()) {
            Section section = new Section(wordLocation.getX(), wordLocation.getX() + wordLocation.getWidth());
            String value = wordLocation.getValue().trim();
            if (xm.isInclude(section)) {
                itemType = ItemClassifier.getItemTypeByName(wordLocation.getValue().trim());
                if (itemType == null) {
                    List<Long> tmpIgnoreLines = new ArrayList<>();
                    if (value.startsWith(ygyjzbdjr)) {
                        StringBuilder sb = new StringBuilder(value);
                        PdfLine tmpLine;
                        int maxTryLines = 3;
                        do {
                            tmpLine = pdfLine.getNext();
                            if (tmpLine != null && tmpLine.getWordLocations().size() == 1) {
                                tmpIgnoreLines.add(tmpLine.getGlobalLineNo());
                                sb.append(tmpLine.getWordLocations().get(0).getValue().trim());
                                itemType = ItemClassifier.getItemTypeByName(sb.toString());
                            }
                            maxTryLines--;
                        } while (itemType == null && tmpLine != null && maxTryLines > 0);
                    }
                    if (itemType == null) {
                        logger.info(wordLocation.getValue() + " ----- 未被识别");
                    } else {
                        ignoreLines.addAll(tmpIgnoreLines);
                    }
                }

            }/* else if (fz.isInclude(section)) {
            } */ else if (qmye.isInclude(section) && itemType != null) {
                currentValue = covert(wordLocation.getValue());
            } else if (qcye.isInclude(section) && itemType != null) {
                preValue = covert(wordLocation.getValue());
            }
        }
        if (itemType != null) {
            if (itemType instanceof FlowAssets) {
                Item item = new Item(itemType, preValue, currentValue);
                combineBalanceResult.getFlowAssets().put((FlowAssets) itemType, item);
            } else if (itemType instanceof FixedAssets) {
                Item item = new Item(itemType, preValue, currentValue);
                combineBalanceResult.getFixAssets().put((FixedAssets) itemType, item);
            } else if (itemType instanceof FlowDebt) {
                Item item = new Item(itemType, preValue, currentValue);
                combineBalanceResult.getFlowDebts().put((FlowDebt) itemType, item);
            } else if (itemType instanceof FixDebt) {
                Item item = new Item(itemType, preValue, currentValue);
                combineBalanceResult.getFixDebts().put((FixDebt) itemType, item);
            }
        }
    }

    private BigDecimal covert(String s) {
        if (TextUtils.isEmpty(s)) {
            return BigDecimal.ZERO;
        }
        s = s.trim().replaceAll(",", "");
        return new BigDecimal(s);
    }

    @Override
    public List<BalanceResult> collectResult() {
        return new ArrayList<>();
    }

    @Override
    public BalanceResult result() {
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
