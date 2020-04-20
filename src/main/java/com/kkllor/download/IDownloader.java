package com.kkllor.download;

import com.kkllor.constants.ReportType;

public interface IDownloader {
    boolean downloadByCodes(ReportType reportType, String... code);
}
