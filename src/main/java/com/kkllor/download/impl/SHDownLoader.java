package com.kkllor.download.impl;

import com.kkllor.constants.ReportType;
import com.kkllor.download.IDownloader;
import com.kkllor.network.NetworkFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author kkllor
 * @date 2020/4/20 下午4:03
 */
public class SHDownLoader implements IDownloader {
    static Logger logger = LogManager.getLogger(SHDownLoader.class.getSimpleName());
    public static final String URL = "http://query.sse.com.cn/security/stock/queryCompanyBulletin.do";

    @Override
    public boolean downloadByCodes(ReportType reportType, String... codes) {

        for (String code : codes) {
            getPdfUrl(reportType, code);
        }
        return false;
    }


    private String getPdfUrl(ReportType reportType, String code) {
        HashMap<String, String> params = new HashMap<>();
        HashMap<String, String> header = new HashMap<>();
        params.put("jsonCallBack", "jsonpCallback52715");
        params.put("isPagination", "true");
        params.put("productId", code);
        params.put("keyWord", "");
        params.put("securityType", "0101");
        params.put("reportType2", "DQBG");
        params.put("reportType", reportType == ReportType.YEAR ? "YEARLY" : "");
        params.put("beginDate", "2015-01-01");
        params.put("endDate", "2016-01-01");
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
        header.put("Referer", "http://www.sse.com.cn/assortment/stock/list/info/announcement/index.shtml?productId=" + code);
        header.put("Accept-Language", "zh-CN,zh;q=0.9,ar-EG;q=0.8,ar;q=0.7,en-US;q=0.6,en;q=0.5");
        header.put("Cookie", "yfx_c_g_u_id_10000042=_ck20042014000619037154169899358; yfx_mr_10000042=%3A%3Amarket_type_free_search%3A%3A%3A%3Abaidu%3A%3A%3A%3A%3A%3A%3A%3Awww.baidu.com%3A%3A%3A%3Apmf_from_free_search; yfx_mr_f_10000042=%3A%3Amarket_type_free_search%3A%3A%3A%3Abaidu%3A%3A%3A%3A%3A%3A%3A%3Awww.baidu.com%3A%3A%3A%3Apmf_from_free_search; yfx_key_10000042=; yfx_f_l_v_t_10000042=f_t_1587362406883__r_t_1587362406883__v_t_1587362545789__r_c_0; VISITED_MENU=%5B%229062%22%5D; VISITED_STOCK_CODE=%5B%22" + code + "%22%5D; VISITED_COMPANY_CODE=%5B%22" + code + "%22%5D; seecookie=%5B" + code + "%5D%3A%u9996%u65C5%u9152%u5E97");
        String rawResponse;
        try {
            rawResponse = NetworkFactory.getNetwork().post(URL, params, header);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return "";
        }

        rawResponse.replaceAll("", "");

        return "";
    }
}
