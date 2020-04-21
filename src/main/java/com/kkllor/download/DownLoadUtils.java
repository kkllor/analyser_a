package com.kkllor.download;

import com.kkllor.config.Config;
import com.kkllor.download.entity.Report;

import java.io.File;

/**
 * @author kkllor
 * @date 2020/4/21 上午9:16
 */
public class DownLoadUtils {


    public static String generateLocalPath(Report report) {
        String parentDir;
        File directory = new File(Config.getInstance().getDownloadPath());
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File companyFile = new File(directory.getAbsolutePath() + File.separator + report.getCompany().getCode());
        if (!companyFile.exists()) {
            companyFile.mkdir();
        }

        File typeFile = new File(companyFile.getAbsolutePath() + File.separator + report.getReportType().getValue());
        if (!typeFile.exists()) {
            typeFile.mkdir();
        }

        if (report.getBelongQuater() != null) {
            File qFile = new File(typeFile.getAbsolutePath() + File.separator + report.getBelongQuater().getValue());
            if (!qFile.exists()) {
                qFile.mkdir();
            }
            parentDir = qFile.getAbsolutePath();
        } else {
            parentDir = typeFile.getAbsolutePath();
        }
        String url = report.getUrl();
        int position = url.lastIndexOf("/");
        String name = url.substring(position, url.length());

        return parentDir + File.separator + name;

    }
}
