package com.kkllor.analysis;

import com.kkllor.analysis.pdf.KeyAreaDetector;
import com.kkllor.config.Config;
import com.kkllor.constants.ReportType;
import com.kkllor.download.entity.Company;
import com.kkllor.download.entity.Report;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kkllor
 * @date 2020/4/21 下午3:27
 */
public class Analyser {
    public void collectData(String code) {

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

        for (Report report : reports) {
            String path = report.getLocalPath();
            PDDocument pdd = null;
            try {
                pdd = PDDocument.load(new File(path));
                PDFParserTextStripper stripper = new PDFParserTextStripper();
                stripper.setSortByPosition(true);
                stripper.setStartPage(0);
                stripper.setEndPage(pdd.getNumberOfPages());
                Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
                stripper.writeText(pdd, dummy);
                stripper.detected();
                new KeyAreaDetector().beginDetect(stripper.getPages());
                System.out.println("############################################################################################################################################");
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
    }

    public void analyse(String code) {
    }
}