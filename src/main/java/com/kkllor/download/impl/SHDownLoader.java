package com.kkllor.download.impl;

import com.google.gson.Gson;
import com.kkllor.config.Config;
import com.kkllor.constants.Quarter;
import com.kkllor.constants.ReportType;
import com.kkllor.download.IDownloader;
import com.kkllor.download.entity.Company;
import com.kkllor.download.entity.Report;
import com.kkllor.download.impl.entity.SHEntity;
import com.kkllor.network.INetwork;
import com.kkllor.network.NetworkFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kkllor
 * @date 2020/4/20 下午4:03
 */
public class SHDownLoader implements IDownloader {

    static Logger logger = LogManager.getLogger(SHDownLoader.class.getSimpleName());
    public static final String URL = "http://query.sse.com.cn/security/stock/queryCompanyBulletin.do";
    private ExecutorService pools = new ThreadPoolExecutor(1, Config.getInstance().getMaxDownload(),
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());
    private INetwork network = NetworkFactory.getNetwork();

    private SHDownLoader() {

    }

    public static SHDownLoader getInstance() {
        return InnerClass.shDownLoader;
    }

    private static class InnerClass {
        private static SHDownLoader shDownLoader = new SHDownLoader();
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
        HashMap<String, String> params = new HashMap<>();
        HashMap<String, String> header = new HashMap<>();
        params.put("jsonCallBack", "jsonpCallback52715");
        params.put("isPagination", "true");
        params.put("productId", report.getCompany().getCode());
        params.put("keyWord", "");
        params.put("securityType", "0101");
        params.put("reportType2", "DQBG");
        params.put("reportType", report.getReportType() == ReportType.YEAR ? "YEARLY" : "");
        params.put("beginDate", (report.getBelongYear() + 1) + "-01-01");
        if (report.getBelongYear() + 2 > Calendar.getInstance().get(Calendar.YEAR)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            params.put("endDate", sdf.format(new Date()));
        } else {
            params.put("endDate", (report.getBelongYear() + 2) + "-01-01");
        }
        params.put("pageHelp.pageSize", "25");
        params.put("pageHelp.pageCount", "50");
        params.put("pageHelp.pageNo", "1");
        params.put("pageHelp.beginPage", "1");
        params.put("pageHelp.cacheSize", "1");
        params.put("pageHelp.endPage", "5");
        params.put("_", System.currentTimeMillis() + "");

        header.put("Connection", "keep-alive");
        header.put("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Mobile Safari/537.36");
        header.put("Accept", "*/*");
        header.put("Referer", "http://www.sse.com.cn/assortment/stock/list/info/announcement/index.shtml?productId=" + report.getCompany().getCode());
        header.put("Accept-Language", "zh-CN,zh;q=0.9,ar-EG;q=0.8,ar;q=0.7,en-US;q=0.6,en;q=0.5");
        header.put("Cookie", "yfx_c_g_u_id_10000042=_ck20042014000619037154169899358; yfx_mr_10000042=%3A%3Amarket_type_free_search%3A%3A%3A%3Abaidu%3A%3A%3A%3A%3A%3A%3A%3Awww.baidu.com%3A%3A%3A%3Apmf_from_free_search; yfx_mr_f_10000042=%3A%3Amarket_type_free_search%3A%3A%3A%3Abaidu%3A%3A%3A%3A%3A%3A%3A%3Awww.baidu.com%3A%3A%3A%3Apmf_from_free_search; yfx_key_10000042=; yfx_f_l_v_t_10000042=f_t_1587362406883__r_t_1587362406883__v_t_1587362545789__r_c_0; VISITED_MENU=%5B%229062%22%5D; VISITED_STOCK_CODE=%5B%22" + report.getCompany().getCode() + "%22%5D; VISITED_COMPANY_CODE=%5B%22" + report.getCompany().getCode() + "%22%5D; seecookie=%5B" + report.getCompany().getCode() + "%5D%3A%u9996%u65C5%u9152%u5E97");
        String rawResponse;
        try {
            rawResponse = network.post(URL, params, header);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return;
        }

        Pattern p = Pattern.compile("\\{\\S*\\}");
        Matcher m = p.matcher(rawResponse);
        if (m.find()) {
            rawResponse = m.group();
        } else {
            logger.error("解析结果失败,rawResponse = " + rawResponse);
            return;
        }

        Gson gson = new Gson();
        SHEntity shEntity = gson.fromJson(rawResponse, SHEntity.class);
        for (SHEntity.ResultBean resultBean : shEntity.getResult()) {
            if (report.getReportType() == ReportType.YEAR) {
                if ("年报".equals(resultBean.getBULLETIN_TYPE())) {
                    report.setUrl("http://static.sse.com.cn" + resultBean.getURL());
                }
            }
        }
    }

    public void exit() {
        pools.shutdown();
    }
}
