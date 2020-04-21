package com.kkllor.analysis;

import com.kkllor.config.Config;
import com.kkllor.constants.ReportType;
import com.kkllor.download.entity.Company;
import com.kkllor.download.entity.Report;

import java.io.File;
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
    }
}
