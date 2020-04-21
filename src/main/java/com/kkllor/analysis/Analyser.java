package com.kkllor.analysis;

import com.kkllor.config.Config;
import com.kkllor.constants.ReportType;
import com.kkllor.download.entity.Company;
import com.kkllor.download.entity.Report;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.interactive.action.PDAction;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionURI;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kkllor
 * @date 2020/4/21 下午3:27
 */
public class Analyser {
    public void analyse(String code) {
        Company company = new Company(code);
        List<Report> reports = new ArrayList<>();
        File dir = new File(Config.getInstance().getDownloadPath());
        File targetFile = null;
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File f : files) {
                if (f.getName().equals(code)) {
                    targetFile = f;
                }
            }
        }

        File[] types = targetFile.listFiles();
        for (File f : types) {
            if ("年报".equals(f.getName())) {
                File[] yearlyReports = f.listFiles();
                for (File yReport : yearlyReports) {
                    if (!yReport.getName().startsWith("20") && !yReport.getName().startsWith("19")) {
                        continue;
                    }
                    File[] fReport = yReport.listFiles();
                    for (File file : fReport) {
                        Report report = new Report();
                        report.setCompany(company);
                        report.setLocalPath(file.getAbsolutePath());
                        report.setReportType(ReportType.YEAR);
                        report.setBelongYear(Integer.parseInt(yReport.getName()));
                        reports.add(report);
                    }
                }
            } else if ("季报".equals(f.getName())) {

            }
        }

        System.out.println(reports);
        if (!reports.isEmpty()) {
            try {
                f4(reports.get(0).getLocalPath());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


//    public static void readPdf(String pdfPath) throws IOException {
//
//        try (PDDocument document = PDDocument.load(new File(pdfPath))) {
//            document.getClass();
//            if (!document.isEncrypted()) {
//                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
//                stripper.setSortByPosition(true);
//
//                PDFTextStripper tStripper = new PDFTextStripper();
//
//                String pdfFileInText = tStripper.getText(document);
//                //System.out.println("Text:" + st);
//
//                // split by whitespace
//                String lines[] = pdfFileInText.split("\\r?\\n");
//                for (String line : lines) {
//                    System.out.println(line);
//                }
//            }
//        }
//    }
//
//
//    public static void listFields(PDDocument doc) throws Exception {
//        PDDocumentCatalog catalog = doc.getDocumentCatalog();
//        PDAcroForm form = catalog.getAcroForm();
//        List<PDField> fields = form.getFields();
//
//        for (PDField field : fields) {
//            String value = field.getValueAsString();
//            String name = field.getFullyQualifiedName();
//            System.out.print(name);
//            System.out.print(" = ");
//            System.out.print(value);
//            System.out.println();
//        }
//    }
//
//    public static void readPdf2(String pdfPath) throws Exception {
//        File file = new File(pdfPath);
//        PDDocument doc = PDDocument.load(file);
//        listFields(doc);
//    }


    public static void f1(String pdfPath) throws IOException {

        PDFTextStripper tStripper = new PDFTextStripper();
        tStripper.setStartPage(31);
        tStripper.setEndPage(34);
        PDDocument document = PDDocument.load(new File(pdfPath));
        document.getClass();
        if (!document.isEncrypted()) {
            String pdfFileInText = tStripper.getText(document);
            String[] lines = pdfFileInText.split("\\r\\n\\r\\n");
            for (String line : lines) {
                System.out.println(line);
            }
        }
    }

    public static void f2(String pdfPath) throws IOException {
        PDDocument document = PDDocument.load(new File(pdfPath));
        document.getClass();
        System.out.println("pages:" + document.getPages().getCount());
        for (int i = 0; i < document.getPages().getCount(); i++) {
            PDPage pdfpage = document.getPage(i);
            List<PDAnnotation> annotations = pdfpage.getAnnotations();
            for (int j = 0; j < annotations.size(); j++) {
                PDAnnotation annot = annotations.get(j);
                if (annot instanceof PDAnnotationLink) {
                    PDAnnotationLink link = (PDAnnotationLink) annot;
                    PDAction action = link.getAction();
                    if (action instanceof PDActionURI) {
                        PDActionURI uri = (PDActionURI) action;
                        System.out.println(uri.getURI());
                    }
                }
            }
        }
    }

    public static void f3(String pdfPath) throws IOException {
        PDDocument document = PDDocument.load(new File(pdfPath));
        document.getClass();
        for (int i = 0; i < document.getPages().getCount(); i++) {
            PDPage pdfpage = document.getPage(i);
            List<PDAnnotation> annotations = pdfpage.getAnnotations();
            for (int j = 0; j < annotations.size(); j++) {
                PDAnnotation annot = annotations.get(j);
                if (annot instanceof PDAnnotationLink) {
                    PDAnnotationLink link = (PDAnnotationLink) annot;
                    PDAction action = link.getAction();
                    if (action instanceof PDActionURI) {
                        PDActionURI uri = (PDActionURI) action;
                        System.out.println(uri.getURI());
                    }
                }
            }
        }
    }


    public static void f4(String pdfPath) throws IOException {
        PDDocument document = PDDocument.load(new File(pdfPath));
        if (!document.isEncrypted()) {
//            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
//            stripper.setSortByPosition(true);
//            Rectangle rect = new Rectangle(0, 200, 2000, 10);    //(x坐标，y坐标，长，宽)
//            stripper.addRegion("class1", rect);

            PDPageTree pages = document.getDocumentCatalog().getPages();
            PDPage firstPage = pages.get(0);
            float width = firstPage.getTrimBox().getWidth();
            float height = firstPage.getTrimBox().getHeight();
            System.out.println("width = " + width + ", height = " + height);

            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);
            int H = 10;
            for (int y = H; y < height; y += H) {
                Rectangle rect = new Rectangle(0, y, (int) width, H);    //(x坐标，y坐标，长，宽)
                stripper.addRegion("class" + y, rect);
            }
            stripper.extractRegions(firstPage);
            for (String region : stripper.getRegions()) {
                System.out.println(stripper.getTextForRegion(region));
            }
        }

    }

}
