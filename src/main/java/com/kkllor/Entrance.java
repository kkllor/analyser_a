package com.kkllor;

import com.kkllor.config.Config;
import com.kkllor.constants.ReportType;
import com.kkllor.download.impl.SHDownLoader;

public class Entrance {

    public static void main(String args[]) {
        Config.getInstance().parse();
        SHDownLoader.getInstance().downloadByCodes(ReportType.YEAR, new int[]{2015, 2016, 2017, 2018, 2019}, "600600");
        SHDownLoader.getInstance().exit();
    }
}