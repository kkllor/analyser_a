package com.kkllor.analysis;

import com.kkllor.analysis.pdf.entity.PdfLine;
import com.kkllor.analysis.pdf.entity.PdfPage;
import com.kkllor.analysis.pdf.entity.WordLocation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class PDFParserTextStripper extends PDFTextStripper {

        private List<WordLocation> wordLocations = new ArrayList<>();
        private List<PdfPage> pages = new ArrayList<>();
        private PdfPage currentPage;
        private int wordLocationIndex;

        public PDFParserTextStripper() throws IOException {
            super();
        }

        @Override
        protected void writeString(String string, List<TextPosition> textPositions) throws IOException {
            String[] words = string.split("\\s+");
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


//            for (TextPosition text : textPositions) {
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append("x = ");
//                stringBuilder.append(text.getX());
//                stringBuilder.append(",");
//                stringBuilder.append("y = ");
//                stringBuilder.append(text.getY());
//                stringBuilder.append(",");
//
//                stringBuilder.append("XDirAdj = ");
//                stringBuilder.append(text.getXDirAdj());
//                stringBuilder.append(",");
//
//                stringBuilder.append("YDirAdj = ");
//                stringBuilder.append(text.getYDirAdj());
//                stringBuilder.append(",");
//
//
//                stringBuilder.append("FontSize = ");
//                stringBuilder.append(text.getFontSize());
//                stringBuilder.append(",");
//
//                stringBuilder.append("FontSizeInPt = ");
//                stringBuilder.append(text.getFontSizeInPt());
//                stringBuilder.append(",");
//
//
//                stringBuilder.append("xscale = ");
//                stringBuilder.append(text.getXScale());
//                stringBuilder.append(",");
//
//                stringBuilder.append("yscale = ");
//                stringBuilder.append(text.getYScale());
//                stringBuilder.append(",");
//
//                stringBuilder.append("endx = ");
//                stringBuilder.append(text.getEndX());
//                stringBuilder.append(",");
//
//                stringBuilder.append("endy = ");
//                stringBuilder.append(text.getEndY());
//                stringBuilder.append(",");
//
//
//                stringBuilder.append("width = ");
//                stringBuilder.append(text.getWidth());
//                stringBuilder.append(",");
//
//                stringBuilder.append("widthDirAdj = ");
//                stringBuilder.append(text.getWidthDirAdj());
//                stringBuilder.append(",");
//
//
//                stringBuilder.append("height = ");
//                stringBuilder.append(text.getHeight());
//                stringBuilder.append(",");
//
//                stringBuilder.append("heightDir = ");
//                stringBuilder.append(text.getHeightDir());
//                stringBuilder.append(",");
//
//                stringBuilder.append("pageWidth = ");
//                stringBuilder.append(text.getPageWidth());
//                stringBuilder.append(",");
//
//
//                stringBuilder.append("pageHeight = ");
//                stringBuilder.append(text.getPageHeight());
//                stringBuilder.append(",");
//
//                System.out.println(stringBuilder);
//            }
//            System.out.println("~~~~~~~~~~~~~~~");

        }

        private long beginLineOffset;

        @Override
        protected void startPage(PDPage page) throws IOException {
            super.startPage(page);
            PdfPage pdfPage = new PdfPage();
            pdfPage.setLineOffset(beginLineOffset);
            pdfPage.setPageNo(getCurrentPageNo());
            currentPage = pdfPage;
            pages.add(pdfPage);
        }

        @Override
        protected void endPage(PDPage page) throws IOException {
            super.endPage(page);
            detected();
            beginLineOffset = currentPage.getLineOffset() + currentPage.getPdfLines().size();
            wordLocations.clear();
        }


        public void detected() {
            double tmpY = -1;
            PdfLine tmpLine = null;
            int pageNo = 0;
            for (int i = 0; i < wordLocations.size(); i++) {
                WordLocation wordLocation = wordLocations.get(i);
                if (tmpY == -1 || Math.abs(wordLocation.getY() - tmpY) > 2) {
                    tmpLine = new PdfLine();
                    tmpLine.setLineNo(pageNo);
                    tmpLine.setGlobalLineNo(currentPage.getLineOffset() + pageNo);
                    pageNo++;
                    currentPage.addLine(tmpLine);
                    int size = currentPage.getPdfLines().size();
                    if (size - 2 >= 0) {
                        PdfLine preLine = currentPage.getPdfLines().get(size - 2);
                        tmpLine.setPre(preLine);
                        preLine.setNext(tmpLine);
                    }
                }
                tmpLine.addWord(wordLocation);
                tmpY = wordLocation.getY();
            }
        }


        public List<PdfPage> getPages() {
            return pages;
        }

        public void printLocations() {
            for (WordLocation location : wordLocations) {
                System.out.println(location);
            }
        }
    }
