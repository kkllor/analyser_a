package com.kkllor.analysis.pdf.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kkllor
 * @date 2020/4/22 下午3:46
 */
public class PdfLine {
    private int index;
    private List<WordLocation> wordLocations = new ArrayList<>();

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void addWord(WordLocation wordLocation) {
        wordLocations.add(wordLocation);
    }
}
