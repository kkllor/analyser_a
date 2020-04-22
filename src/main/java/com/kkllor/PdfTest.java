package com.kkllor;

/**
 * @author kkllor
 * @date 2020/4/22 上午9:37
 */

import com.kkllor.analysis.pdf.entity.PdfLine;
import com.kkllor.analysis.pdf.entity.PdfPage;
import com.kkllor.analysis.pdf.entity.WordLocation;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PdfTest extends PDFTextStripper {

    public PdfTest()
            throws IOException {
        super.setSortByPosition(true);
    }

    public static void main(String[] args)
            throws Exception {
        PDDocument pdd = null;
        try {
            pdd = PDDocument.load(new File("/Users/zhanggaokai/analyser/reports/600300/年报/2015/600300_2015_n.pdf"));
            PDFParserTextStripper stripper = new PDFParserTextStripper();
            stripper.setSortByPosition(true);
            stripper.setStartPage(32);
            stripper.setEndPage(32);
            Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
            stripper.writeText(pdd, dummy);

            stripper.detected();
        } catch (IOException e) {
            // throw error
            e.printStackTrace();
        } finally {
            if (pdd != null) {
                try {
                    pdd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class PDFParserTextStripper extends PDFTextStripper {

        private List<WordLocation> wordLocations = new ArrayList<>();
        private List<PdfPage> pages = new ArrayList<>();
        private PdfPage currentPage;
        private int wordLocationIndex;

        public PDFParserTextStripper() throws IOException {
            super();
        }

        @Override
        protected void writeString(String string, List<TextPosition> textPositions) throws IOException {
            System.out.println("~~~~~~~~~~~~~~~");
            System.out.println(string);
            String[] words = string.split("\\s+");
            System.out.println(Arrays.toString(words));

            int index = 0;
            int length = textPositions.size();
            for (String word : words) {
                WordLocation wordLocation = new WordLocation();
                wordLocation.setValue(word);
                wordLocation.setIndex(wordLocationIndex);
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char c = chars[i];
                    while (index < length) {
                        TextPosition textPosition = textPositions.get(index);
                        index++;
                        if (c == textPosition.getUnicode().toCharArray()[0]) {
                            if (i == 0) {
                                wordLocation.setX(textPosition.getXDirAdj());
                                wordLocation.setY(textPosition.getYDirAdj());
                                wordLocation.setHeight(textPosition.getHeightDir());
                            } else if (i == chars.length - 1) {
                                wordLocation.setWidth(textPosition.getEndX() - wordLocation.getX());
                            }
                            break;
                        }
                    }
                }
                wordLocationIndex++;
                wordLocations.add(wordLocation);
            }


            for (TextPosition text : textPositions) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("x = ");
                stringBuilder.append(text.getX());
                stringBuilder.append(",");
                stringBuilder.append("y = ");
                stringBuilder.append(text.getY());
                stringBuilder.append(",");

                stringBuilder.append("XDirAdj = ");
                stringBuilder.append(text.getXDirAdj());
                stringBuilder.append(",");

                stringBuilder.append("YDirAdj = ");
                stringBuilder.append(text.getYDirAdj());
                stringBuilder.append(",");


                stringBuilder.append("FontSize = ");
                stringBuilder.append(text.getFontSize());
                stringBuilder.append(",");

                stringBuilder.append("FontSizeInPt = ");
                stringBuilder.append(text.getFontSizeInPt());
                stringBuilder.append(",");


                stringBuilder.append("xscale = ");
                stringBuilder.append(text.getXScale());
                stringBuilder.append(",");

                stringBuilder.append("yscale = ");
                stringBuilder.append(text.getYScale());
                stringBuilder.append(",");

                stringBuilder.append("endx = ");
                stringBuilder.append(text.getEndX());
                stringBuilder.append(",");

                stringBuilder.append("endy = ");
                stringBuilder.append(text.getEndY());
                stringBuilder.append(",");


                stringBuilder.append("width = ");
                stringBuilder.append(text.getWidth());
                stringBuilder.append(",");

                stringBuilder.append("widthDirAdj = ");
                stringBuilder.append(text.getWidthDirAdj());
                stringBuilder.append(",");


                stringBuilder.append("height = ");
                stringBuilder.append(text.getHeight());
                stringBuilder.append(",");

                stringBuilder.append("heightDir = ");
                stringBuilder.append(text.getHeightDir());
                stringBuilder.append(",");

                stringBuilder.append("pageWidth = ");
                stringBuilder.append(text.getPageWidth());
                stringBuilder.append(",");


                stringBuilder.append("pageHeight = ");
                stringBuilder.append(text.getPageHeight());
                stringBuilder.append(",");

                System.out.println(stringBuilder);
            }
            System.out.println("~~~~~~~~~~~~~~~");

        }

        @Override
        protected void startPage(PDPage page) throws IOException {
            super.startPage(page);
            PdfPage pdfPage = new PdfPage();
            pdfPage.setPageNo(getCurrentPageNo());
            currentPage = pdfPage;
            pages.add(pdfPage);
        }

        @Override
        protected void endPage(PDPage page) throws IOException {
            super.endPage(page);
            detected();
        }


        private void detected() {
            double tmpY = -1;
            PdfLine tmpLine = null;
            for (int i = 0; i < wordLocations.size(); i++) {
                WordLocation wordLocation = wordLocations.get(i);
                if (tmpY == -1 || wordLocation.getY() != tmpY) {
                    tmpLine = new PdfLine();
                    currentPage.addLine(tmpLine);
                }
                tmpLine.addWord(wordLocation);
                tmpY = wordLocation.getY();
            }
        }


        public void printLocations() {
            for (WordLocation location : wordLocations) {
                System.out.println(location);
            }
        }
    }
}