package com.kkllor.download.entity;

import com.kkllor.constants.Quarter;
import com.kkllor.constants.ReportType;
import com.kkllor.download.DownLoadUtils;
import org.apache.http.util.TextUtils;

import java.io.File;

/**
 * @author kkllor
 * @date 2020/4/21 上午9:29
 */
public class Report {
    private Company company;
    private int belongYear;
    private Quarter belongQuater;
    private ReportType reportType;
    private String url;
    private String localPath;


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getBelongYear() {
        return belongYear;
    }

    public void setBelongYear(int belongYear) {
        this.belongYear = belongYear;
    }

    public Quarter getBelongQuater() {
        return belongQuater;
    }

    public void setBelongQuater(Quarter belongQuater) {
        this.belongQuater = belongQuater;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocalPath() {
        if (TextUtils.isEmpty(localPath)) {
            localPath = DownLoadUtils.generateLocalPath(this);
        }
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public boolean isReportExists() {
        if (localPath == null) {
            this.localPath = DownLoadUtils.generateLocalPath(this);
        }
        File file = new File(localPath);
        return file.exists();
    }

    @Override
    public String toString() {
        return "Report{" +
                "company=" + company +
                ", belongYear=" + belongYear +
                ", belongQuater=" + belongQuater +
                ", reportType=" + reportType +
                ", url='" + url + '\'' +
                ", localPath='" + localPath + '\'' +
                '}';
    }
}
