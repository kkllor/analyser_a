package com.kkllor.analysis.pdf.entity;

import org.apache.http.util.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kkllor
 * @date 2020/4/22 下午3:46
 */
public class PdfLine {
    private int LineNo;
    private long globalLineNo;
    private List<WordLocation> wordLocations = new ArrayList<>();


    private PdfLine pre, next;

    public int getLineNo() {
        return LineNo;
    }

    public void setLineNo(int lineNo) {
        LineNo = lineNo;
    }

    public PdfLine getPre() {
        return pre;
    }

    public void setPre(PdfLine pre) {
        this.pre = pre;
    }

    public PdfLine getNext() {
        return next;
    }

    public void setNext(PdfLine next) {
        this.next = next;
    }

    public PdfLine getRelativePage(int offset) {
        PdfLine targetLine = this;
        if (offset == 0) {
            return targetLine;
        }
        int point = 1;
        if (offset < 0) {
            offset = -offset;
            while (point <= offset) {
                if (targetLine != null) {
                    targetLine = targetLine.pre;
                    point++;
                }
            }
        } else {
            while (point <= offset) {
                if (targetLine != null) {
                    targetLine = targetLine.next;
                    point++;
                }
            }
        }
        return targetLine;
    }

    public void addWord(WordLocation wordLocation) {
        wordLocations.add(wordLocation);
    }

    public List<WordLocation> getWordLocations() {
        return wordLocations;
    }

    public long getGlobalLineNo() {
        return globalLineNo;
    }

    public void setGlobalLineNo(long globalLineNo) {
        this.globalLineNo = globalLineNo;
    }

    public boolean containWord(String word) {

        if (searchResult.get(word) == null) {
            if (TextUtils.isEmpty(word)) {
                return false;
            }
            if (wordLocations.isEmpty()) {
                return false;
            }
            for (WordLocation wordLocation : wordLocations) {
                if (word.equals(wordLocation.getValue())) {
                    searchResult.put(word, 1);
                    return true;
                }
            }
            searchResult.put(word, 2);
        } else {
            return searchResult.get(word) == 1;
        }
        return false;
    }

    public boolean containWordAnd(String... words) {
        boolean result = true;
        for (String word : words) {
            result &= containWord(word);
            if (!result) {
                return false;
            }
        }
        return result;
    }

    private Map<String, Integer> searchResult = new HashMap<>();


}
