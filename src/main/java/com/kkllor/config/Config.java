package com.kkllor.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    final static Logger logger = LogManager.getLogger(Config.class.getSimpleName());
    private String downloadPath;
    private int maxDownload = 2;

    private Config() {

    }

    public static Config getInstance() {
        return InnerClass.config;
    }

    private static class InnerClass {
        static Config config = new Config();
    }

    public void parse() {
        InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("config.properties");
        Properties prop = new Properties();

        if (inputStream == null) {
            logger.warn("unable to find config.properties, using current configs");
            return;
        }

        try {
            prop.load(inputStream);
            downloadPath = prop.getProperty("download_folder");
            if (downloadPath.startsWith("~")) {
                downloadPath = downloadPath.replaceFirst("~", System.getProperty("user.home"));
            }
            maxDownload = Integer.parseInt(prop.getProperty("max_download_simultaneously"));
        } catch (IOException e) {
            e.printStackTrace();
            logger.warn("exception occurred when parse config.properties!");
        }
        logger.debug("config success!");
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public int getMaxDownload() {
        return maxDownload;
    }
}
