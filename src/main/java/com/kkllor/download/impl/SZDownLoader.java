package com.kkllor.download.impl;

import com.google.gson.Gson;
import com.kkllor.config.Config;
import com.kkllor.constants.Quarter;
import com.kkllor.constants.ReportType;
import com.kkllor.download.IDownloader;
import com.kkllor.download.entity.Company;
import com.kkllor.download.entity.Report;
import com.kkllor.download.impl.entity.SZEntity;
import com.kkllor.network.INetwork;
import com.kkllor.network.NetworkFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author kkllor
 * @date 2020/4/20 下午4:03
 */
public class SZDownLoader implements IDownloader {

    static Logger logger = LogManager.getLogger(SZDownLoader.class.getSimpleName());
    public static final String URL = "http://www.szse.cn/api/disc/announcement/annList";
    private ExecutorService pools = new ThreadPoolExecutor(1, Config.getInstance().getMaxDownload(),
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());
    private INetwork network = NetworkFactory.getNetwork();

    private SZDownLoader() {

    }

    public static SZDownLoader getInstance() {
        return InnerClass.shDownLoader;
    }

    private static class InnerClass {
        private static SZDownLoader shDownLoader = new SZDownLoader();
    }

    @Override
    public boolean downloadByCodes(ReportType reportType, int year, Quarter quarter, String... codes) {

        if (reportType != ReportType.YEAR) {
            throw new RuntimeException("only year support!");
        }
        CountDownLatch countDownLatch = new CountDownLatch(codes.length);
        for (String code : codes) {
            Company company = new Company(code);
            Report report = new Report();
            report.setCompany(company);
            report.setReportType(reportType);
            report.setBelongYear(year);
            report.setBelongQuater(quarter);
            getReportUrl(report);
            if (report.getUrl() == null) {
                logger.error("fetch report url failed " + report.getUrl());
                countDownLatch.countDown();
            } else if (report.isReportExists()) {
                logger.info(report.toString() + " report is exist, ignore download");
                countDownLatch.countDown();
            } else {
                pools.submit(() -> {
                    try {
                        network.downLoad(report);
                        logger.info(report + " download succeed!");
                    } catch (IOException e) {
                        e.printStackTrace();
                        logger.info(report + " download failed!");
                    } finally {
                        countDownLatch.countDown();
                    }
                });
            }
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean downloadByCodes(ReportType reportType, int year, String... codes) {
        downloadByCodes(reportType, year, null, codes);
        return false;
    }

    @Override
    public boolean downloadByCodes(ReportType reportType, int[] years, String... codes) {
        if (years.length == 0) {
            throw new RuntimeException("year must not empty!");
        }
        for (int y : years) {
            downloadByCodes(reportType, y, codes);
        }
        return false;
    }


    private void getReportUrl(Report report) {
//        HashMap<String, String> params = new HashMap<>();
        HashMap<String, String> header = new HashMap<>();
//        '{"seDate":["",""],"stock":["002007"],"channelCode":["listedNotice_disc"],"pageSize":30,"pageNum":1}'
        Params params = new Params();
        params.random = 0.40143946649245854 + (new Random().nextInt(100) / 10000.0);
        params.stock.add(report.getCompany().getCode());
        params.channelCode.add("listedNotice_disc");
        params.bigCategoryId.add("010301");
        params.setDate.add("");
        params.setDate.add("");
        params.pageSize = 30;
        params.pageNum = 1;

        header.put("Connection", "keep-alive");
        header.put("Accept", "application/json, text/javascript, */*; q=0.01");
        header.put("X-Request-Type", "ajax");
        header.put("X-Requested-With", "XMLHttpRequest");
        header.put("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Mobile Safari/537.36");
        header.put("Content-Type", "application/json");
        header.put("Origin", "http://www.szse.cn");
        header.put("Referer", "http://www.szse.cn/disclosure/listed/notice/index.html?name=%E5%8D%8E%E5%85%B0%E7%94%9F%E7%89%A9&stock=002007&r=1588123388155");
        header.put("Accept-Language", "zh-CN,zh;q=0.9,ar-EG;q=0.8,ar;q=0.7,en-US;q=0.6,en;q=0.5");
        String rawResponse;
        try {
            rawResponse = network.postString(URL, params.toParams(), header);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return;
        }
        logger.info(rawResponse);
        Gson gson = new Gson();
        SZEntity szEntity = gson.fromJson(rawResponse, SZEntity.class);
        for (SZEntity.DataBean dataBean : szEntity.getData()) {
            if (report.getReportType() == ReportType.YEAR) {
                if (dataBean.getTitle().contains(report.getBelongYear() + "")) {
                    report.setUrl("http://disc.static.szse.cn/download" + dataBean.getAttachPath());
                }
            }
        }
    }

    public void exit() {
        pools.shutdown();
    }

    private static class Params {
        private double random;
        private List<String> setDate = new ArrayList<>();
        private List<String> stock = new ArrayList<>();
        private List<String> channelCode = new ArrayList<>();
        private List<String> bigCategoryId = new ArrayList<>();
        private int pageSize = 30;
        private int pageNum = 1;

        public double getRandom() {
            return random;
        }

        public void setRandom(double random) {
            this.random = random;
        }

        public List<String> getSetDate() {
            return setDate;
        }

        public void setSetDate(List<String> setDate) {
            this.setDate = setDate;
        }

        public List<String> getStock() {
            return stock;
        }

        public void setStock(List<String> stock) {
            this.stock = stock;
        }

        public List<String> getChannelCode() {
            return channelCode;
        }

        public void setChannelCode(List<String> channelCode) {
            this.channelCode = channelCode;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public List<String> getBigCategoryId() {
            return bigCategoryId;
        }

        public void setBigCategoryId(List<String> bigCategoryId) {
            this.bigCategoryId = bigCategoryId;
        }

        public String toParams() {
            Gson gson = new Gson();
            return gson.toJson(this);
        }
    }
}
