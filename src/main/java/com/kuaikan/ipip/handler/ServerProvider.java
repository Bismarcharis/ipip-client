package com.kuaikan.ipip.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * @author liguochao.
 */
@Slf4j
class ServerProvider {

    private static String sFile;
    private static int sInterval;

    static {
        initialFilePath();
        initialReloadInterval();
    }

    static String getFilePath() {
        return sFile;
    }

    static int getReloadInterval() {
        return sInterval;
    }


    /**
     * 默认的地址库加载路径
     */
    private static final String DEFAULT_FILE_PATH = "/data/17monipdb/ipip/gaoji.dat";
    /**
     * 默认的自动reload地址库时间间隔一小时
     */
    private static final int DEFAULT_RELOAD_INTERVAL = 3600;


    private static void initialFilePath() {
        if (!StringUtils.isEmpty(sFile)) {
            return;
        }

        sFile = System.getProperty("ipip.file");
        if (!StringUtils.isEmpty(sFile)) {
            log.info("ipip file path set by JVM property:" + sFile);
            return;
        }

        File file = new File(DEFAULT_FILE_PATH);
        if (file.exists() && file.canRead()) {
            log.info("use ipip defaule file path:" + DEFAULT_FILE_PATH);
            sFile = DEFAULT_FILE_PATH;
        }

        if (StringUtils.isEmpty(sFile)) {
            log.error("ipip file path not initialized,please make sure the gaoji.dat is under /data/17monipdb/ipip directory or you can set the file path to JVM property");
        }
    }

    private static void initialReloadInterval() {
        if (sInterval > 0) {
            return;
        }
        String interval = System.getProperty("ipip.interval");
        if (!StringUtils.isEmpty(interval)) {
            try {
                sInterval = Integer.valueOf(interval);
                log.info("ipip reload interval set by JVM property:" + sInterval);
                return;
            } catch (NumberFormatException e) {
                log.error("ipip interval set by JVM property illegal,please check!!!");
            }
        }

        sInterval = DEFAULT_RELOAD_INTERVAL;
    }
}
