package com.kkllor;

import com.kkllor.config.Config;
import com.kkllor.constants.ReportType;
import com.kkllor.download.impl.SHDownLoader;

public class Entrance {

    public static void main(String args[]) {
        Config.parse();
        new SHDownLoader().downloadByCodes(ReportType.YEAR, "600519");
    }
}