package com.kkllor.download;

import com.kkllor.constants.Quarter;
import com.kkllor.constants.ReportType;

public interface IDownloader {
    boolean downloadByCodes(ReportType reportType, int year, Quarter quarter, String... codes);

    boolean downloadByCodes(ReportType reportType, int year, String... codes);

}
